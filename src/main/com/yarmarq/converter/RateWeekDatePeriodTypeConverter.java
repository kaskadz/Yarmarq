package com.yarmarq.converter;

import com.yarmarq.exception.WrongDatePeriodFormatException;
import com.yarmarq.module.DatePeriod;
import picocli.CommandLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RateWeekDatePeriodTypeConverter implements CommandLine.ITypeConverter<DatePeriod> {
    private final Pattern pattern = Pattern.compile("^(\\d{4})-(\\d{2}),(\\d):(\\d{4})-(\\d{2}),(\\d)$");

    @Override
    public DatePeriod convert(String s) throws Exception {
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) throw new WrongDatePeriodFormatException();
        int startYear = Integer.parseInt(matcher.group(1));
        int startMonth = Integer.parseInt(matcher.group(2));
        int startWeek = Integer.parseInt(matcher.group(3));
        int endYear = Integer.parseInt(matcher.group(4));
        int endMonth = Integer.parseInt(matcher.group(5));
        int endWeek = Integer.parseInt(matcher.group(6));
        return new DatePeriod(startYear, startMonth, startWeek, endYear, endMonth, endWeek);
    }
}
