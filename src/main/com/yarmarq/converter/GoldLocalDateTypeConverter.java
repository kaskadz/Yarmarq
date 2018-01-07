package com.yarmarq.converter;

import com.yarmarq.exception.DateFromTheFutureException;
import com.yarmarq.exception.TooEarlyDateException;
import picocli.CommandLine.ITypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Converter class for gold date.
 */
public class GoldLocalDateTypeConverter implements ITypeConverter<LocalDate> {
    private final LocalDate refDate = LocalDate.of(2013, 1, 2);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Converts string to LocalDate object.
     *
     * @param s date given as string.
     * @return LocalDate object, converted from string
     * @throws Exception thrown when wrong date format is given or wrong date is given
     */
    @Override
    public LocalDate convert(String s) throws Exception {
        LocalDate date = LocalDate.parse(s, formatter);
        if (date.isBefore(refDate)) throw new TooEarlyDateException(date, refDate);
        if (date.isAfter(LocalDate.now())) throw new DateFromTheFutureException(date);
        return date;
    }
}
