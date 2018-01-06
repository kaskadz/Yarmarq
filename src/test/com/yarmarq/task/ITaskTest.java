package com.yarmarq.task;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ITaskTest {

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
        ITask task = () -> System.out.println("abc");
        task.accomplish();
        assertEquals("abc\r\n", outContent.toString());
    }

    @Test
    void accomplishTest2() {
        ITask task = () -> System.out.println("djsk");
        task.accomplish();
        assertEquals("djsk\r\n", outContent.toString());
    }
}