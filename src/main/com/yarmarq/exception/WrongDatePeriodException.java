package com.yarmarq.exception;

import java.time.LocalDate;

public class WrongDatePeriodException extends Exception {
    public WrongDatePeriodException(LocalDate startDate, LocalDate endDate) {
        super(String.format("Wrong time period: %s : %s", startDate, endDate));
    }
}
