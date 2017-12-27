package com.yarmarq.exception;

public class WrongDatePeriodFormatException extends Exception {
    @Override
    public String getMessage() {
        return "Wrong time period format.\nIt should be yyyy-MM-dd:yyyy-MM-dd.";
    }
}
