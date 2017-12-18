package com.yarmarq.exception;

public class UnknownCurrencyCodeException extends Exception {
    private String code;

    public UnknownCurrencyCodeException(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
