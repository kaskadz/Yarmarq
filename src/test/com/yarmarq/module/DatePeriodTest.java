package com.yarmarq.module;

import com.yarmarq.exception.WrongDatePeriodException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class DatePeriodTest {
    @Test
    public void DateConstructorTest() {
        try {
            DatePeriod period = new DatePeriod(LocalDate.of(2012, 5, 6), LocalDate.of(2016, 8, 30));
            assertEquals(period.getStartDate(), LocalDate.of(2012, 5, 6), "startDate validation failed");
            assertEquals(period.getEndDate(), LocalDate.of(2016, 8, 30), "endDate validation failed");
        } catch (WrongDatePeriodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void DateConstructorValidationTest() {
        boolean thrown = false;
        try {
            new DatePeriod(LocalDate.of(2012, 4, 27), LocalDate.of(2007, 8, 30));
        } catch (WrongDatePeriodException e) {
            thrown = true;
        }
        assertTrue(thrown, "period validation failed");
    }

    @Test
    public void WeekConstructorTest() {
        try {
            DatePeriod period = new DatePeriod(2017, 12, 2, 2017, 12, 4);
            DatePeriod period2 = new DatePeriod(LocalDate.of(2017, 12, 4), LocalDate.of(2017, 12, 24));
            assertTrue(!period.getStartDate().isAfter(period.getEndDate()));
            assertEquals(period2.getStartDate(), period.getStartDate(), "startDate validation failed");
            assertEquals(period2.getEndDate(), period.getEndDate(), "endDate validation failed");
        } catch (WrongDatePeriodException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void WeekConstructorValidationTest() {
        boolean thrown = false;
        try {
            new DatePeriod(2017, 12, 2, 2017, 12, 1);
        } catch (WrongDatePeriodException e) {
            thrown = true;
        }
        assertTrue(thrown, "period validation failed");
    }

    @Test
    void toStringTest() {
        try {
            DatePeriod period = new DatePeriod(LocalDate.of(2006, 3, 7), LocalDate.of(2006, 3, 12));
            assertEquals("2006-03-07 : 2006-03-12 [5]", period.toString());
        } catch (WrongDatePeriodException e) {
            fail("period validation failed");
        }
    }

    @Test
    void getStartDateTest() {
        try {
            DatePeriod period = new DatePeriod(LocalDate.of(2006, 3, 7), LocalDate.of(2006, 3, 12));
            assertEquals(LocalDate.of(2006, 3, 7), period.getStartDate());
        } catch (WrongDatePeriodException e) {
            fail("period validation failed");
        }
    }

    @Test
    void getEndDateTest() {
        try {
            DatePeriod period = new DatePeriod(LocalDate.of(2006, 3, 7), LocalDate.of(2006, 3, 12));
            assertEquals(LocalDate.of(2006, 3, 12), period.getEndDate());
        } catch (WrongDatePeriodException e) {
            fail("period validation failed");
        }
    }

    @Test
    void getPeriodLengthTest() {
        try {
            DatePeriod period = new DatePeriod(LocalDate.of(2006, 3, 7), LocalDate.of(2006, 3, 12));
            assertEquals(5, period.getPeriodLength());
        } catch (WrongDatePeriodException e) {
            fail("period validation failed");
        }
    }

    @Test
    void splitTest() {
        try {
            DatePeriod period = new DatePeriod(LocalDate.of(2006, 3, 7), LocalDate.of(2006, 3, 12));
            period.split(1).forEach(x -> assertEquals(1, x.getPeriodLength(), x.toString()));
        } catch (WrongDatePeriodException e) {
            fail("period validation failed");
        }
    }

    @Test
    void equalsTest() {
        try {
            DatePeriod period = new DatePeriod(2017, 12, 2, 2017, 12, 4);
            DatePeriod period2 = new DatePeriod(LocalDate.of(2017, 12, 4), LocalDate.of(2017, 12, 24));
            assertTrue(period.equals(period2), "equals validation failed");
        } catch (WrongDatePeriodException e) {
            fail("period validation failed");
        }
    }

    @Test
    void hashCodeTest() {
        try {
            DatePeriod period = new DatePeriod(2017, 12, 2, 2017, 12, 4);
            DatePeriod period2 = new DatePeriod(LocalDate.of(2017, 12, 4), LocalDate.of(2017, 12, 24));
            assertEquals(period.hashCode(), period2.hashCode(), "hashCode() validation failed");
        } catch (WrongDatePeriodException e) {
            fail("period validation failed");
        }
    }
}