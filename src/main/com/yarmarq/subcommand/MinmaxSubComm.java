package com.yarmarq.subcommand;

import com.yarmarq.deserializable.Rate;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import javafx.util.Pair;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

@Command(name = "minmax",
        description = "For a given currency in a table A prints info, when it's exchange rate was the lowest and the highest.")
public class MinmaxSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "CODE",
            description = "Currency code.")
    private String code;

    @Override
    public void run() {
        System.out.println("Minmax");
        System.out.println(code);
        NBPApiFacade facade = NBPApiFacade.getInstance();
        try {
            Rate rates = facade.getRates(code, LocalDate.of(2002, 1, 2), LocalDate.now());
            Pair<LocalDate, Double> minn = Arrays.stream(rates.getRates())
                    .map(x -> new Pair<>(x.getEffectiveDate(), x.getMid()))
                    .min(Comparator.comparing(Pair::getValue))
                    .get();
            Pair<LocalDate, Double> maxx = Arrays.stream(rates.getRates()).map(x -> new Pair<>(x.getEffectiveDate(), x.getMid())).max(Comparator.comparing(Pair::getValue)).get();
            System.out.printf("Currency %s had the lowest exchange rate on %s, which was %f and the highest exchange rate on %s, which was %f.", rates.getCurrency(), minn.getKey(), minn.getValue(), maxx.getKey(), maxx.getValue());
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
// DONE
