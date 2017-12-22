package com.yarmarq.exception;

public class WrongDatePeriodFormatException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong time period format.";
    }
}
