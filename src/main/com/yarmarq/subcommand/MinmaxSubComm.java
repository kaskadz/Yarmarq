package com.yarmarq.subcommand;

import com.yarmarq.exception.JsonParserException;
import com.yarmarq.exception.OnlineResourcesAccessException;
import com.yarmarq.module.NBPApiFacade;
import com.yarmarq.serializable.Rate;
import picocli.CommandLine.*;

import java.time.LocalDate;
import java.util.List;

@Command(name = "minmax",
        description = "For a given currency in a table A prints info, about the lowest and the highest exchange rate.")
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
//        NBPApiFacade facade = NBPApiFacade.getInstance();
//        try {
//            List<Rate> rates = facade.getRates(code, LocalDate.of(2002, 1,2), LocalDate.now());
//        } catch (JsonParserException e) {
//            e.printStackTrace();
//        } catch (OnlineResourcesAccessException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
//TODO: Consider modifying NBPApiFacade getRates(...) method.
//TODO: Implement minmax functionality.
