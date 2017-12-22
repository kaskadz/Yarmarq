package com.yarmarq.exception;

public class WrongCurrencyCodeException extends Exception {
    public WrongCurrencyCodeException() {
        super("Wrong currency code. It should be 3 letters ([A-Za-z]) long.");
    }
}
