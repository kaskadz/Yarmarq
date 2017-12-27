package com.yarmarq.subcommand;

import com.yarmarq.Main;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheapestSubCommTest {

    @Test
    void cheapestSubCommTest() {
        String[] args = {"cheapest", "2015-10-12"};
        CommandLine commandLine = new CommandLine(new Main());
        List<CommandLine> parsed = commandLine.parse(args);
        assertEquals(2, parsed.size());
        assertEquals(Main.class, parsed.get(0).getCommand().getClass());
        assertEquals("yarmarq", parsed.get(0).getCommandName());
        assertEquals("cheapest", parsed.get(1).getCommandName());
    }
}