package com.yarmarq.converter;

import com.yarmarq.exception.WrongCurrencyCodeException;
import picocli.CommandLine.ITypeConverter;

/**
 * Converter class for currency codes.
 */
public class CurrencyCodeTypeConverter implements ITypeConverter<String> {
    /**
     * Converts and validates string to a lower case currency code.
     *
     * @param s currency code
     * @return valid currency code
     * @throws Exception thrown, when currency code is in a wrong format
     */
    @Override
    public String convert(String s) throws Exception {
        if (!s.matches("^[A-Za-z]{3}$")) throw new WrongCurrencyCodeException();
        return s.toLowerCase();
    }
}
