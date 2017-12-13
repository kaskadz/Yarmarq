package com.yarmarq;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import com.yarmarq.subcommand.*;

@Command(name = "yarmarq", sortOptions = false,
        header = "%nYarmarq - simple access to NBP.pl resources.%n",
        description = "Provides an interface to Polish NBP api.%n",
        footer = "%nAuthor: Kasper Kądzielawa",
        optionListHeading = "Options:\n",
        subcommands = {
                PriceSumComm.class,
                GoldSubComm.class,
                FluctuationsSubComm.class,
                CheapestSubComm.class,
                SpreadSumComm.class,
                MinmaxSubComm.class,
                GraphSubComm.class,
        })
public class Main implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    boolean usageHelpRequested;

    public static void main(String[] args) {
        CommandLine.run(new Main(), System.err, args);
    }

    @Override
    public void run() {
        System.out.println("Hello World!");
        System.out.println("I've been just brought to life :)");
    }
}
