package com.yarmarq;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import com.yarmarq.subcommand.*;

@Command(
        name = "yarmarq", sortOptions = false,
        header = "Yarmarq - simple access to NBP.pl resources.",
        description = "Provides an interface to Polish NBP api.",
        footer = "\nAuthor: Kasper Kądzielawa",
        subcommands = {
                PriceSubComm.class,
                GoldSubComm.class,
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
        System.out.println(" __      __                                                                 \n" +
                "|  \\    /  \\                                                                \n" +
                " \\$$\\  /  $$  ______    ______   ______ ____    ______    ______    ______  \n" +
                "  \\$$\\/  $$  |      \\  /      \\ |      \\    \\  |      \\  /      \\  /      \\ \n" +
                "   \\$$  $$    \\$$$$$$\\|  $$$$$$\\| $$$$$$\\$$$$\\  \\$$$$$$\\|  $$$$$$\\|  $$$$$$\\\n" +
                "    \\$$$$    /      $$| $$   \\$$| $$ | $$ | $$ /      $$| $$   \\$$| $$  | $$\n" +
                "    | $$    |  $$$$$$$| $$      | $$ | $$ | $$|  $$$$$$$| $$      | $$__| $$\n" +
                "    | $$     \\$$    $$| $$      | $$ | $$ | $$ \\$$    $$| $$       \\$$    $$\n" +
                "     \\$$      \\$$$$$$$ \\$$       \\$$  \\$$  \\$$  \\$$$$$$$ \\$$        \\$$$$$$$\n" +
                "                                                                        | $$\n" +
                "                                       Data via: Narodowy Bank Polski.  | $$\n" +
                "                                                                         \\$$");
        CommandLine.usage(this, System.out);
    }
}
//TODO: Think about placing banner in every subcommand. (Maybe extract banner to abstractCommand.)
//TODO: Consider extracting some functionality from subcommand classes.
//TODO: Check help metadata.
//TODO: Write tests.
