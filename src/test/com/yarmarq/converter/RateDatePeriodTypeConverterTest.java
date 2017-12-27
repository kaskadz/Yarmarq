package com.yarmarq.converter;

import com.yarmarq.exception.DateFromTheFutureException;
import com.yarmarq.exception.TooEarlyDateException;
import com.yarmarq.exception.WrongDatePeriodException;
import com.yarmarq.exception.WrongDatePeriodFormatException;
import org.junit.jupiter.api.Test;
import picocli.CommandLine.ITypeConverter;

import static org.junit.jupiter.api.Assertions.*;

class RateDatePeriodTypeConverterTest {

    @Test
    void convert() {
        ITypeConverter converter = new RateDatePeriodTypeConverter();
        assertThrows(WrongDatePeriodFormatException.class, () -> converter.convert("2017-32-12:::3992-500-20"));
        assertThrows(TooEarlyDateException.class, () -> converter.convert("2001-09-11:2014-12-03"));
        assertThrows(DateFromTheFutureException.class, () -> converter.convert("2016-10-17:3056-12-20"));
        assertThrows(WrongDatePeriodException.class, () -> converter.convert("2015-10-17:2014-12-20"));
    }
}