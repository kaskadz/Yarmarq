package com.yarmarq.subcommand;

import com.yarmarq.Main;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FluctuationsSubCommTest {

    @Test
    void fluctuationsSubCommTest() {
        String[] args = {"fluctuations", "2015-10-12"};
        CommandLine commandLine = new CommandLine(new Main());
        List<CommandLine> parsed = commandLine.parse(args);
        assertEquals(2, parsed.size());
        assertEquals(Main.class, parsed.get(0).getCommand().getClass());
        assertEquals("yarmarq", parsed.get(0).getCommandName());
        assertEquals(FluctuationsSubComm.class, parsed.get(1).getCommand().getClass());
        assertEquals("fluctuations", parsed.get(1).getCommandName());
    }
}