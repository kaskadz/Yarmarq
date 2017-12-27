package com.yarmarq.converter;

import com.yarmarq.exception.WrongCurrencyCodeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyCodeTypeConverterTest {

    @Test
    void convertTest() {
        CurrencyCodeTypeConverter converter = new CurrencyCodeTypeConverter();
        assertThrows(WrongCurrencyCodeException.class, () -> converter.convert("abcd"));
        assertThrows(WrongCurrencyCodeException.class, () -> converter.convert("b4d"));
        assertThrows(WrongCurrencyCodeException.class, () -> converter.convert("bd"));
        try {
            assertEquals("usd", converter.convert("uSd"));
            assertEquals("hkd", converter.convert("HKD"));
        } catch (Exception e) {
            fail("Nobody expected spanish inquisition, as well, as this failure.");
        }
    }
}