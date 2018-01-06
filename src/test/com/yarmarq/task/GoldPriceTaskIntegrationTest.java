package com.yarmarq.task;

import com.yarmarq.deserializable.Gold;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GoldPriceTaskIntegrationTest {

    private final PrintStream oldOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(oldOut);
    }

    @Test
    void accomplishTest() {
        Gold gold = new Gold();
        gold.setDate(LocalDate.of(2016, 2, 14));
        gold.setPrice(123.4);
        ITask task = new GoldPriceTask(gold);
        task.accomplish();
        assertEquals("Gold price in 2016-02-14 was 123.4000\n", outContent.toString());
    }
}