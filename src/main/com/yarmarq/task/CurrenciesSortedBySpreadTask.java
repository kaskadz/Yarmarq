package com.yarmarq.task;

import com.yarmarq.deserializable.TRate;
import com.yarmarq.deserializable.Table;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class CurrenciesSortedBySpreadTask implements ITask {
    private final Table table;
    private final LocalDate date;
    private final int n;
    private final boolean asc;

    public CurrenciesSortedBySpreadTask(Table table, int n, boolean asc, LocalDate date) {
        this.table = table;
        this.n = n;
        this.asc = asc;
        this.date = date;
    }

    @Override
    public void accomplish() {
        Stream<TRate> str = Arrays.stream(table.getRates());
        if (asc) str = str
                .sorted(Comparator.comparing(TRate::getSpread));
        else str = str
                .sorted(Comparator.comparing(TRate::getSpread).reversed());
        System.out.printf(
                "List of currencies sorted by spread in %s (TOP: %d ORDER: %s)\n",
                date, n, (asc) ? "ASC" : "DESC"
        );
        str
                .limit(n)
                .forEach(x -> System.out.printf(
                        "Currency: %-30s [%s] Buy: %.4f Sell: %.4f Spread: %.4f\n",
                        x.getCurrency(), x.getCode(), x.getBid(), x.getAsk(), x.getSpread()
                ));
    }
}
