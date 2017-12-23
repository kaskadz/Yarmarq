package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.CurrencyCodeTypeConverter;
import com.yarmarq.deserializable.Rate;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.exception.WrongDatePeriodException;
import com.yarmarq.module.DatePeriod;
import com.yarmarq.module.NBPApiFacade;
import javafx.util.Pair;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

@Command(
        name = "minmax",
        description = "For a given currency in a table A prints info, when it's exchange rate was the lowest and the highest."
)
public class MinmaxSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "1",
            paramLabel = "CODE",
            description = "Currency code.",
            converter = CurrencyCodeTypeConverter.class
    )
    private String code;

    @Override
    public void run() {
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            Rate rates = facade.getRates(code, new DatePeriod(LocalDate.of(2002, 1, 2), LocalDate.now()));
            Pair<LocalDate, Double> minn = Arrays.stream(rates.getRates())
                    .map(x -> new Pair<>(x.getEffectiveDate(), x.getMid()))
                    .min(Comparator.comparing(Pair::getValue))
                    .orElseThrow(RuntimeException::new);
            Pair<LocalDate, Double> maxx = Arrays.stream(rates.getRates())
                    .map(x -> new Pair<>(x.getEffectiveDate(), x.getMid()))
                    .max(Comparator.comparing(Pair::getValue))
                    .orElseThrow(RuntimeException::new);
            System.out.printf("Currency %s had the lowest exchange rate on %s, which was %f and the highest exchange rate on %s, which was %f.", rates.getCurrency(), minn.getKey(), minn.getValue(), maxx.getKey(), maxx.getValue());
        } catch (JsonParserException | WrongDatePeriodException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println("An error occurred!");
            System.out.println(e.getMessage());
        }
    }
}
// DONE
