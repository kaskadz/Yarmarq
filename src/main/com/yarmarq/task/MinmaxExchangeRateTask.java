package com.yarmarq.task;

import com.yarmarq.deserializable.Rate;
import javafx.util.Pair;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class MinmaxExchangeRateTask implements ITask {
    private final Rate rates;

    public MinmaxExchangeRateTask(Rate rates) {
        this.rates = rates;
    }

    @Override
    public void accomplish() {
        Pair<LocalDate, Double> minn = Arrays.stream(rates.getRates())
                .map(x -> new Pair<>(x.getEffectiveDate(), x.getMid()))
                .min(Comparator.comparing(Pair::getValue))
                .orElseThrow(RuntimeException::new);
        Pair<LocalDate, Double> maxx = Arrays.stream(rates.getRates())
                .map(x -> new Pair<>(x.getEffectiveDate(), x.getMid()))
                .max(Comparator.comparing(Pair::getValue))
                .orElseThrow(RuntimeException::new);
        System.out.printf(
                "Currency %s had the lowest exchange rate on %s, which was %f and the highest exchange rate on %s, which was %f.\n",
                rates.getCurrency(), minn.getKey(), minn.getValue(), maxx.getKey(), maxx.getValue()
        );
    }
}
