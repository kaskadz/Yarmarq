package com.yarmarq.task;

import com.yarmarq.deserializable.TRate;
import com.yarmarq.deserializable.Table;

import java.time.LocalDate;
import java.util.*;

public class MaxFluctuationsCurrencyTask implements ITask {
    private final List<Table> tables;
    private final LocalDate date;

    public MaxFluctuationsCurrencyTask(List<Table> tables, LocalDate date) {
        this.tables = tables;
        this.date = date;
    }

    public void accomplish() {
        // initialize min and max values
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
        // calculate amplitudes
        Map<String, Double> ampl = new HashMap<>();
        mins
                .keySet()
                .forEach(x -> ampl.put(x, maxs.get(x) - mins.get(x)));
        // get max amplitude
        Map.Entry<String, Double> entry = ampl
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .orElseThrow(RuntimeException::new);
        // get other info about the rate
        TRate currency = tables
                .stream()
                .flatMap(x -> Arrays.stream(x.getRates()))
                .filter(x -> x.getCode().equals(entry.getKey()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        // print info
        System.out.printf(
                "Currency %s (%s) from %s fluctuated the most since %s. The amplitude was %f.\n",
                currency.getCurrency(), entry.getKey(), currency.getCountry(), date, entry.getValue()
        );
    }
}
