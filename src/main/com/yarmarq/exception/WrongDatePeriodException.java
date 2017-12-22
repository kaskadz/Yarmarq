package com.yarmarq.exception;

public class WrongDatePeriodException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong time period.";
    }
}
