package com.yarmarq.subcommand;

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
            description = "Date of gold price.")
    private Date basicDate;
    private LocalDate date;

    private void preRun() throws DateFromTheFutureException {
        if (basicDate != null) {
            date = LocalDate.ofInstant(basicDate.toInstant(), ZoneId.systemDefault());
            if (date.isAfter(LocalDate.now())) throw new DateFromTheFutureException(date);
        }
    }

    @Override
    public void run() {
        try {
            preRun();
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
        } catch (DateFromTheFutureException e) {
            System.out.println(e.getMessage());
        }
    }
}
// DONE