package com.yarmarq;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import com.yarmarq.subcommand.*;

import java.time.LocalDate;

@Command(name = "yarmarq", sortOptions = false,
        header = "%nYarmarq - simple access to NBP.pl resources.%n",
        description = "Provides an interface to Polish NBP api.%n",
        footer = "%nAuthor: Kasper Kądzielawa",
        optionListHeading = "Options:\n",
        subcommands = {
                PriceSubComm.class,
                GoldSubComm.class,
                FluctuationsSubComm.class,
                CheapestSubComm.class,
                SpreadSubComm.class,
                MinmaxSubComm.class,
                GraphSubComm.class,
        })
public class Main implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    public static void main(String[] args) {
        CommandLine.run(new Main(), System.out, args);
    }

    @Override
    public void run() {
        System.out.println("No subcommand was given, printing help.");
        CommandLine.usage(this, System.out);
    }
}
//TODO: Implement subcommands.
//TODO: Think of input dates pre validation.
//TODO: Implement post validation for dates and time periods.
//TODO: Check help metadata.
//TODO: Write tests.
