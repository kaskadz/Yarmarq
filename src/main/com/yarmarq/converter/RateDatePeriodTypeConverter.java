package com.yarmarq.converter;

import com.yarmarq.exception.WrongDatePeriodFormatException;
import com.yarmarq.module.DatePeriod;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converter class for rate date period.
 */
public class RateDatePeriodTypeConverter implements CommandLine.ITypeConverter<DatePeriod> {
    private final Pattern pattern = Pattern.compile("^(\\d{4}-\\d{2}-\\d{2}):(\\d{4}-\\d{2}-\\d{2})$");

    /**
     * Converts string to date period object.
     *
     * @param s rate period as string
     * @return DatePeriod constructed from input string
     * @throws Exception thrown, when wrong period format is given or the wrong date is given
     */
    @Override
    public DatePeriod convert(String s) throws Exception {
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) throw new WrongDatePeriodFormatException();
        RateLocalDateTypeConverter converter = new RateLocalDateTypeConverter();
        LocalDate startDate = converter.convert(matcher.group(1));
        LocalDate endDate = converter.convert(matcher.group(2));
        return new DatePeriod(startDate, endDate);
    }
}
