package com.yarmarq.module;

import com.yarmarq.exception.WrongDatePeriodException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.time.temporal.TemporalAdjusters.*;

public class DatePeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public DatePeriod(LocalDate startDate, LocalDate endDate) throws WrongDatePeriodException {
        if (startDate.isAfter(endDate)) throw new WrongDatePeriodException(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DatePeriod(int startYear, int startMonth, int startWeek, int endYear, int endMonth, int endWeek) throws WrongDatePeriodException {
        LocalDate startDate = LocalDate.of(startYear, startMonth, 1);
        for (int i = 0; i < startWeek - 1; i++) {
            startDate = startDate.with(next(DayOfWeek.MONDAY));
        }
        LocalDate endDate = LocalDate.of(endYear, endMonth, 1).with(firstInMonth(DayOfWeek.SUNDAY));
        for (int i = 0; i < endWeek - 1; i++) {
            LocalDate tempEndDate = endDate.with(next(DayOfWeek.SUNDAY));
            endDate = (tempEndDate.getMonthValue() == endMonth) ? tempEndDate : endDate.with(lastDayOfMonth());
        }
        if (startDate.isAfter(endDate)) throw new WrongDatePeriodException(startDate, endDate);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatePeriod period = (DatePeriod) o;
        return Objects.equals(startDate, period.startDate) &&
                Objects.equals(endDate, period.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
