package com.yarmarq.deserializable;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class RateTest {

    @Test
    void deserialize() {
        try {
            Rate rate = Rate.deserialize("{\"table\":\"A\",\"currency\":\"frank szwajcarski\",\"code\":\"CHF\",\"rates\":[{\"no\":\"248/A/NBP/2017\",\"effectiveDate\":\"2017-12-22\",\"mid\":3.5764}]}");
            assertEquals("A", rate.getTable());
            assertEquals("frank szwajcarski", rate.getCurrency());
            assertEquals("CHF", rate.getCode());
            RRate rRate = rate.getRates()[0];
            assertEquals("248/A/NBP/2017", rRate.getNo());
            assertEquals(LocalDate.of(2017, 12, 22), rRate.getEffectiveDate());
            assertEquals((Double) 3.5764, rRate.getMid());
        } catch (IOException e) {
            fail("Not expecting an error, but error was thrown.");
        }
        assertThrows(IOException.class, () -> Rate.deserialize("\"table\":\"A\",\"currency\":\"frank szwajcarski\",\"code\":\"CHF\",\"rates\":[{\"no\":\"248/A/NBP/2017\",\"effectiveDate\":\"2017-12-22\",\"mid\":3.5764}]}"));
        assertThrows(IOException.class, () -> Rate.deserialize("{\"table\":\"A\",\"currenc\"frank szwajcarski\",\"code\":\"CHF\",\"rates\":[{\"no\":\"248/A/NBP/2017\",\"effectiveDate\":\"2017-12-22\",\"mid\":3.5764}]}"));
        assertThrows(IOException.class, () -> Rate.deserialize("{\"table\":\"A\",\"currency\":\"frank szwajcarski\",\"code\":\"CHF\",\"rates\":[{\"no\":\"248/A/NBP/20effectiveDate\":\"2017-12-22\",\"mid\":3.5764}]}"));
    }
}