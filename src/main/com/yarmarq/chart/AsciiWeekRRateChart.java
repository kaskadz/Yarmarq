package com.yarmarq.chart;

import com.yarmarq.deserializable.RRate;
import com.yarmarq.deserializable.Rate;

import java.time.DayOfWeek;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

public class AsciiWeekRRateChart implements IChart {
    private final char c;
    private final Map<DayOfWeek, List<RRate>> rateMap;
    private final Double mulFactor;

    public AsciiWeekRRateChart(Rate rate, int maxBarWidth, char c) {
        this.c = c;
        rateMap = Arrays.stream(rate.getRates())
                .sorted(Comparator.comparing(RRate::getEffectiveDate))
                .collect(Collectors.groupingBy(x -> x.getEffectiveDate().getDayOfWeek()));
        Double maxValue = Arrays.stream(rate.getRates())
                .mapToDouble(RRate::getMid)
                .max()
                .orElse(1);
        mulFactor = maxBarWidth / maxValue;
    }

    private void drawBar(RRate rRate) {
        if (rRate.getMid() == null) return;
        int weekNo = rRate.getEffectiveDate().get(WeekFields.ISO.weekOfWeekBasedYear());
        System.out.printf("|%2d| ", weekNo);
        int length = (int) (rRate.getMid() * mulFactor);
        for (int i = 0; i < length; i++) System.out.print(c);
        System.out.printf(" %.4f\n", rRate.getMid());
    }

    @Override
    public void draw() {
        Arrays.stream(DayOfWeek.values()).forEach(
                day -> Optional.ofNullable(rateMap.get(day))
                        .ifPresent(x -> {
                            System.out.printf("%s:\n", day);
                            x.forEach(this::drawBar);
                        }));
    }
}
