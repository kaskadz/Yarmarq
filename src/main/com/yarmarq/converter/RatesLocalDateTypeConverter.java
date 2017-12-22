package com.yarmarq.converter;

import com.yarmarq.exception.DateFromTheFutureException;
import com.yarmarq.exception.TooEarlyDateException;
import picocli.CommandLine.ITypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RatesLocalDateTypeConverter implements ITypeConverter<LocalDate> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final LocalDate refDate = LocalDate.of(2002, 1, 2);

    @Override
    public LocalDate convert(String s) throws Exception {
        LocalDate date = LocalDate.parse(s, formatter);
        if (date.isBefore(refDate)) throw new TooEarlyDateException(date, refDate);
        if (date.isAfter(LocalDate.now())) throw new DateFromTheFutureException(date);
        return date;
    }
}
