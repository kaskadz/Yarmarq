package com.yarmarq.converter;

import com.yarmarq.exception.DateFromTheFutureException;
import com.yarmarq.exception.TooEarlyDateException;
import com.yarmarq.exception.WrongWeekDatePeriodFormatException;
import com.yarmarq.module.DatePeriod;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RateWeekDatePeriodTypeConverter implements CommandLine.ITypeConverter<DatePeriod> {
    private final Pattern pattern = Pattern.compile("^(\\d{4})-(\\d{2}),(\\d):(\\d{4})-(\\d{2}),(\\d)$");
    private final LocalDate refDate = LocalDate.of(2002, 1, 2);

    @Override
    public DatePeriod convert(String s) throws Exception {
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) throw new WrongWeekDatePeriodFormatException();
        int startYear = Integer.parseInt(matcher.group(1));
        int startMonth = Integer.parseInt(matcher.group(2));
        int startWeek = Integer.parseInt(matcher.group(3));
        int endYear = Integer.parseInt(matcher.group(4));
        int endMonth = Integer.parseInt(matcher.group(5));
        int endWeek = Integer.parseInt(matcher.group(6));
        DatePeriod period = new DatePeriod(startYear, startMonth, startWeek, endYear, endMonth, endWeek);
        if (period.getStartDate().isBefore(refDate)) throw new TooEarlyDateException(period.getStartDate(), refDate);
        if (period.getEndDate().isAfter(LocalDate.now())) throw new DateFromTheFutureException(period.getEndDate());
        return period;
    }
}
