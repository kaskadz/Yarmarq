package com.yarmarq.exception;

import java.time.LocalDate;

public class TooEarlyDateException extends Exception {
    public TooEarlyDateException(LocalDate date, LocalDate refDate) {
        super(String.format("Date %s is before %s. There is no data before %s.", date, refDate, refDate));
    }
}
