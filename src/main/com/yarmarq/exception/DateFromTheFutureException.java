package com.yarmarq.exception;

import java.time.LocalDate;

public class DateFromTheFutureException extends Exception {
    public DateFromTheFutureException(LocalDate date) {
        super(String.format("Date %s is from the future. This program cannot foretell the future. :P", date));
    }
}
