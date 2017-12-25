package com.yarmarq.task;

import com.yarmarq.deserializable.TRate;
import com.yarmarq.deserializable.Table;

import java.util.Arrays;
import java.util.Comparator;

public class SmallestBuyingRateTask implements ITask {
    private final Table table;

    public SmallestBuyingRateTask(Table table) {
        this.table = table;
    }

    @Override
    public void accomplish() {
        TRate rate = Arrays.stream(table.getRates())
                .min(Comparator.comparing(TRate::getBid))
                .orElseThrow(RuntimeException::new);
        System.out.printf(
                "Currency %s of code %s had the smallest buying rate in %s, which was %f",
                rate.getCurrency(), rate.getCode(), table.getEffectiveDate(), rate.getBid()
        );
    }
}
