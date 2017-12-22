package com.yarmarq.subcommand;

import com.yarmarq.AbstractCommand;
import com.yarmarq.converter.RateLocalDateTypeConverter;
import com.yarmarq.deserializable.TRate;
import com.yarmarq.deserializable.Table;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.exception.WrongDatePeriodException;
import com.yarmarq.module.DatePeriod;
import com.yarmarq.module.NBPApiFacade;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.util.*;

@Command(
        name = "fluctuations",
        description = "Finds a currency in table A, which exchange rate, from a given date, fluctuated the most."
)
public class FluctuationsSubComm extends AbstractCommand implements Runnable {

    @Parameters(
            index = "0",
            arity = "1",
            paramLabel = "DATE",
            description = "Date, from which to calculate fluctuations.",
            converter = RateLocalDateTypeConverter.class
    )
    private LocalDate date;

    @Override
    public void run() {
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            List<Table> tables = facade.getTables('a', new DatePeriod(date, LocalDate.now()));
            Map<String, Double> mins = new HashMap<>();
            Map<String, Double> maxs = new HashMap<>();
            tables
                    .stream()
                    .flatMap(x -> Arrays.stream(x.getRates()))
                    .forEach(x -> {
                        Double mid = x.getMid();
                        String code = x.getCode();

                        Double minr = mins.get(code);
                        if (minr == null || mid < minr) mins.put(code, mid);

                        Double maxr = maxs.get(code);
                        if (maxr == null || mid > maxr) maxs.put(code, mid);
                    });
            Map<String, Double> ampl = new HashMap<>();
            mins
                    .keySet()
                    .forEach(x -> ampl.put(x, maxs.get(x) - mins.get(x)));
            Map.Entry<String, Double> entry = ampl
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(x -> x.getValue()))
                    .get();
            TRate currency = tables
                    .stream()
                    .flatMap(x -> Arrays.stream(x.getRates()))
                    .filter(x -> x.getCode().equals(entry.getKey()))
                    .findFirst()
                    .get();
            System.out.printf("Currency %s (%s) from %s fluctuated the most since %s. The amplitude was %f.", currency.getCurrency(), entry.getKey(), currency.getCountry(), date, entry.getValue());
        } catch (OnlineResourcesAccessException e) {
            System.out.println("An error occurred!");
            System.out.println(e.getMessage());
        } catch (JsonParserException | WrongDatePeriodException e) {
            e.printStackTrace();
        }
    }
}
// DONE