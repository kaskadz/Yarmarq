package com.yarmarq;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import picocli.CommandLine;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    void main() {
        Main main;

        String[] args1 = {"-h"};
        main = CommandLine.populateCommand(new Main(), args1);
        assertTrue(main.helpRequested);

        String[] args2 = {"--help"};
        main = CommandLine.populateCommand(new Main(), args2);
        assertTrue(main.helpRequested);

        String[] args3 = {"-?"};
        main = CommandLine.populateCommand(new Main(), args3);
        assertTrue(main.helpRequested);

        String[] args4 = {"dafdakj"};
        Executable closure = () -> CommandLine.populateCommand(new Main(), args4);
        assertThrows(CommandLine.UnmatchedArgumentException.class, closure);
    }
}
