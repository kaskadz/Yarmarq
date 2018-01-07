package com.yarmarq;

import com.yarmarq.subcommand.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
        name = "yarmarq",
        sortOptions = false,
        header = "Yarmarq - simple access to exchange rates and gold prices from NBP.pl.",
        description = "Provides an interface to Polish NBP api.",
        footer = "\nAuthor: Kasper Kądzielawa",
        subcommands = {
                PriceSubComm.class,
                GoldSubComm.class,
                GoldAndRateSubComm.class,
                GoldAvgSubComm.class,
                FluctuationsSubComm.class,
                CheapestSubComm.class,
                SpreadSubComm.class,
                MinmaxSubComm.class,
                GraphSubComm.class,
        }
)
public class Main extends AbstractCommand implements Runnable {

    public static void main(String[] args) {
        CommandLine.run(new Main(), System.out, args);
    }

    @Override
    public void run() {
        printBanner();
        CommandLine.usage(this, System.out);
    }
}
//TODO: Check help metadata.
