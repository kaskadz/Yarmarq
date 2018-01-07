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

/**
 * A class to store date periods.
 */
public class DatePeriod {
    /**
     * Beginning of period.
     */
    private final LocalDate startDate;
    /**
     * End of period.
     */
    private final LocalDate endDate;

    /**
     * Creates DatePeriod object from from points in time, specified two dates in ordinary form.
     *
     * @param startDate start date of period
     * @param endDate   end date of period
     * @throws WrongDatePeriodException thrown, when wrong params are given (endDate precedes startDate)
     */
    public DatePeriod(LocalDate startDate, LocalDate endDate) throws WrongDatePeriodException {
        if (startDate.isAfter(endDate))
            throw new WrongDatePeriodException(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates DatePeriod object from from points in time, specified by year, month and week of month.
     * <p>
     * The period will start with the first day of given week (or with the first day of month) and end with the last day
     * of the given month (or last day of month). If number of week is invalid (too large and there is no week of that
     * number) the last week is being taken.
     *
     * @param startYear  year of start date
     * @param startMonth month of start date
     * @param startWeek  number of week in month of start date
     * @param endYear    year of end date
     * @param endMonth   month of end date
     * @param endWeek    number of week in month of end date
     * @throws WrongDatePeriodException thrown, when wrong params are given (endDate precedes startDate)
     */
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
        if (startDate.isAfter(endDate))
            throw new WrongDatePeriodException(startDate, endDate);
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

    /**
     * Calculates time in fays between period start date and end date.
     *
     * @return returns length of period in days
     */
    public long getPeriodLength() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * Splits period into periods of maximum length, specified in the first parameter.
     *
     * @param maxPeriodLength maximum length (in days) of result period, into which the period is to be split
     * @return ordered list of periods
     */
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
