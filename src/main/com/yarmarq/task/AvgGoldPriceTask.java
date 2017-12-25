package com.yarmarq.task;

import com.yarmarq.deserializable.Gold;
import com.yarmarq.module.DatePeriod;

import java.util.List;

public class AvgGoldPriceTask implements ITask {
    private final List<Gold> golds;
    private final DatePeriod period;

    public AvgGoldPriceTask(List<Gold> golds, DatePeriod period) {
        this.golds = golds;
        this.period = period;
    }

    @Override
    public void accomplish() {
        Double avg = golds
                .stream()
                .mapToDouble(Gold::getPrice)
                .average()
                .orElse(0.0);
        System.out.printf(
                "Gold average price from %s to %s is: %f",
                period.getStartDate(), period.getEndDate(), avg
        );
    }
}
