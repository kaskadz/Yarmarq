package com.yarmarq.module;

import com.yarmarq.exception.WrongDatePeriodException;
import javafx.util.Pair;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;

public class DatePeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DatePeriod(LocalDate startDate, LocalDate endDate) throws WrongDatePeriodException {
        if (startDate.isAfter(endDate)) throw new WrongDatePeriodException();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("%s : %s [%d]", startDate, endDate, getPeriodLength());
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getPeriodLength() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public List<DatePeriod> split(int maxPeriodLength) {
        List<DatePeriod> result = new LinkedList<>();
        Period period = Period.ofDays(maxPeriodLength);
        LocalDate sd = startDate;
        LocalDate ed = endDate;
        try {
            while (ChronoUnit.DAYS.between(sd, ed) > maxPeriodLength) {
                LocalDate tempEndDate = sd.plus(period);
                result.add(new DatePeriod(sd, tempEndDate));
                sd = tempEndDate.plusDays(1);
            }
            result.add(new DatePeriod(sd, ed));
        } catch (WrongDatePeriodException e) {
            e.printStackTrace();
        }
        return result;
    }
}
