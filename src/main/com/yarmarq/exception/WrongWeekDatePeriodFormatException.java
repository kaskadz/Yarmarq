package com.yarmarq.exception;

public class WrongWeekDatePeriodFormatException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong time period format.";
    }
}
