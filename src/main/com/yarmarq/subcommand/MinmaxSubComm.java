package com.yarmarq.subcommand;

import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.deserializable.Rate;
import picocli.CommandLine.*;

import java.time.LocalDate;

@Command(name = "minmax",
        description = "For a given currency in a table A prints info, when it's exchange rate was the lowest and the highest.")
public class MinmaxSubComm implements Runnable {

    @Option(names = {"-h", "-?", "--help"}, usageHelp = true,
            description = "Print usage help and exit.")
    private boolean usageHelpRequested;

    @Parameters(index = "0", arity = "1", paramLabel = "CODE",
            description = "Currency code.")
    private String code;

    @Override
    public void run() {
        System.out.println("Minmax");
        System.out.println(code);
        NBPApiFacade facade = NBPApiFacade.getInstance();
        try {
            Rate rates = facade.getRates(code, LocalDate.of(2002, 1, 2), LocalDate.now());
            System.out.println(rates);
        } catch (JsonParserException e) {
            e.printStackTrace();
        } catch (OnlineResourcesAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
//TODO: Consider modifying NBPApiFacade getRates(...) method.
//TODO: Implement minmax functionality.
