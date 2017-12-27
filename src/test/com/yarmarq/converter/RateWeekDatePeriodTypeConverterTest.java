package com.yarmarq.converter;

import com.yarmarq.exception.DateFromTheFutureException;
import com.yarmarq.exception.TooEarlyDateException;
import com.yarmarq.exception.WrongDatePeriodException;
import com.yarmarq.exception.WrongWeekDatePeriodFormatException;
import org.junit.jupiter.api.Test;
import picocli.CommandLine.ITypeConverter;

import static org.junit.jupiter.api.Assertions.*;

class RateWeekDatePeriodTypeConverterTest {

    @Test
    void convertTest() {
        ITypeConverter converter = new RateWeekDatePeriodTypeConverter();
        assertThrows(WrongWeekDatePeriodFormatException.class, () -> converter.convert("2017-32-12:::3992-500-20"));
        assertThrows(TooEarlyDateException.class, () -> converter.convert("2001-09,1:2014-12,3"));
        assertThrows(DateFromTheFutureException.class, () -> converter.convert("2016-10,1:3056-12,2"));
        assertThrows(WrongDatePeriodException.class, () -> converter.convert("2015-10,1:2014-12,2"));
    }
}