package com.yarmarq.converter;

import com.yarmarq.exception.DateFromTheFutureException;
import com.yarmarq.exception.TooEarlyDateException;
import com.yarmarq.exception.WrongDatePeriodException;
import org.junit.jupiter.api.Test;
import picocli.CommandLine.ITypeConverter;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class GoldLocalDateTypeConverterTest {

    @Test
    void convert() {
        ITypeConverter converter = new GoldLocalDateTypeConverter();
        assertThrows(DateTimeParseException.class, () -> converter.convert("2001-09-11:2014-12-03"));
        assertThrows(TooEarlyDateException.class, () -> converter.convert("2001-09-11"));
        assertThrows(DateFromTheFutureException.class, () -> converter.convert("3056-12-20"));
    }
}