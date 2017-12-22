package com.yarmarq.converter;

import com.yarmarq.exception.WrongCurrencyCodeException;
import picocli.CommandLine.ITypeConverter;

public class CurrencyCodeTypeConverter implements ITypeConverter<String> {
    @Override
    public String convert(String s) throws Exception {
        if (!s.matches("^[A-Za-z]{3}$")) throw new WrongCurrencyCodeException();
        return s.toLowerCase();
    }
}
