package com.yarmarq.deserializable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GoldTest {

    @Test
    void deserializeOneTest() {
        try {
            Gold gold = Gold.deserializeOne("[{\"data\":\"2017-12-22\",\"cena\":143.81}]");
            assertEquals((Double) 143.81, gold.getPrice());
            assertEquals(LocalDate.of(2017, 12, 22), gold.getDate());
        } catch (IOException e) {
            fail("Not expecting an error, but error was thrown.");
        }
        assertThrows(IOException.class, () -> Gold.deserializeOne("[{\"data\":\"2017-2-22\",\"cena\"143.81}]"));
        assertThrows(IOException.class, () -> Gold.deserializeOne("[{\"data\":\"2017-12-22\"\"cena\":143.81}]"));
        assertThrows(IOException.class, () -> Gold.deserializeOne("[{\"data\":\"2017-12-22\",\"cena\":asd}]"));
    }

    @Test
    void deserializeMany() {
        try {
            Gold[] golds = Gold.deserializeMany("[{\"data\":\"2017-12-20\",\"cena\":144.18},{\"data\":\"2017-12-21\",\"cena\":144.18},{\"data\":\"2017-12-22\",\"cena\":143.81}]");
            assertEquals((Double) 144.18, golds[0].getPrice());
            assertEquals(LocalDate.of(2017, 12, 20), golds[0].getDate());
            assertEquals((Double) 144.18, golds[1].getPrice());
            assertEquals(LocalDate.of(2017, 12, 21), golds[1].getDate());
            assertEquals((Double) 143.81, golds[2].getPrice());
            assertEquals(LocalDate.of(2017, 12, 22), golds[2].getDate());
        } catch (IOException e) {
            fail("Not expecting an error, but error was thrown.");
        }
        assertThrows(IOException.class, () -> Gold.deserializeMany("[{\"data\":\"2017-12-20\",\"cena\":144.18},{\"data\":\"2017-12-21\",\"cena\":144.18},\"data\":\"2017-12-22\",\"cena\":143.81}]"));
        assertThrows(IOException.class, () -> Gold.deserializeMany("[{\"data\":\"2017-12-20\",\"cena\":144.18},{\"data\":\"2017-12-21\",\"cena\":144.18}{\"data\":\"2017-12-22\",\"cena\":143.81}]"));
        assertThrows(IOException.class, () -> Gold.deserializeMany("[{\"data\":\"2017-12-20\",\"cena\":144.18},{\"data\":\"2017-12-2\",\"cena\":144.18},{\"data\":\"2017-12-22\",\"cena\":143.81}]"));
    }
}