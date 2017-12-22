package com.yarmarq.subcommand;

import com.yarmarq.converter.GoldLocalDateTypeConverter;
import com.yarmarq.exception.DateFromTheFutureException;
import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.deserializable.Gold;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Command(name = "gold",
        description = "Prints gold price in a given day.")
public class GoldSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "0..1", paramLabel = "DATE",
            description = "Date of gold price.",
            converter = GoldLocalDateTypeConverter.class)
    private LocalDate date;

    @Override
    public void run() {
        try {
            NBPApiFacade facade = NBPApiFacade.getInstance();
            Gold gold;
            if (date == null) {
                gold = facade.getGold();
            } else {
                gold = facade.getGold(date);
            }
            System.out.println(gold);
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println("An error occurred!");
            System.out.println(e.getMessage());
        }
    }
}
// DONE