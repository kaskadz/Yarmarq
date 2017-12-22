package com.yarmarq.subcommand;

import com.yarmarq.converter.RatesLocalDateTypeConverter;
import com.yarmarq.deserializable.TRate;
import com.yarmarq.deserializable.Table;
import com.yarmarq.exception.DateFromTheFutureException;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import javafx.scene.control.Tab;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Stream;

@Command(name = "fluctuations",
        description = "Finds a currency in table A, which exchange rate, from a given date, fluctuated the most.")
public class FluctuationsSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "DATE",
            description = "Date, from which to calculate fluctuations.",
            converter = RatesLocalDateTypeConverter.class)
    private LocalDate date;

    @Override
    public void run() {
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            List<Table> tables = facade.getTables('a', date, LocalDate.now());
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
            e.printStackTrace();
        } catch (JsonParserException e) {
            System.out.println("An error occurred!");
            System.out.println(e.getMessage());
        }
    }
}
// DONE