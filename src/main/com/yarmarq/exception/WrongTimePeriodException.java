package com.yarmarq.exception;

public class WrongTimePeriodException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong time period.";
    }
}
