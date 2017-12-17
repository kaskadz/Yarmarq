package com.yarmarq.module;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yarmarq.serializable.Gold;
import com.yarmarq.serializable.Rate;
import com.yarmarq.serializable.Table;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

// Facade - Provide a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.
public class NBPApiFacade {

    public Rate getRate(String code) {
        // http://api.nbp.pl/api/exchangerates/rates/a/{code}/
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/"; // code
        ObjectMapper mapper = new ObjectMapper();
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, code));
            if (fetcher.fetchResource()) {
                return mapper.readValue(fetcher.getContent(), Rate.class);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Rate> getRates(String code, LocalDate startdate, LocalDate enddate) {
        // http://api.nbp.pl/api/exchangerates/rates/a/{code}/{startDate}/{endDate}/
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/"; // code, startDate, endDate
        return null;
    }

    public Gold getGold() {
        // http://api.nbp.pl/api/cenyzlota
        String url = "http://api.nbp.pl/api/cenyzlota";
        ObjectMapper mapper = new ObjectMapper();
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(url);
            if (fetcher.fetchResource()) {
                Gold[] golds = mapper.readValue(fetcher.getContent(), Gold[].class);
                return golds[0];
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Gold> getGolds(Date startDate, Date endDate) {
        // http://api.nbp.pl/api/cenyzlota/{startDate}/{endDate}
        String url = "http://api.nbp.pl/api/cenyzlota/%s/%s/"; // startDate, endDate
        return null;
    }

    public Table getTable(String table, LocalDate date) {
        // http://api.nbp.pl/api/exchangerates/tables/{table}/{date}/
        String url = "http://api.nbp.pl/api/exchangerates/tables/%s/%s/"; // table, date
        ObjectMapper mapper = new ObjectMapper();
        try {
            OnlineResourceFetcher fetcher = new OnlineResourceFetcher(String.format(url, table, date));
            if (fetcher.fetchResource()) {
                Table[] tables = mapper.readValue(fetcher.getContent(), Table[].class);
                return tables[0];
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Table> getTables(String table, Date startDate, Date endDate) {
        // http://api.nbp.pl/api/exchangerates/tables/{table}/{startDate}/{endDate}/
        String url = "http://api.nbp.pl/api/exchangerates/tables/%s/%s/%s/"; // table, startDate, endDate
        return null;
    }

    public static void main(String[] args) {
        NBPApiFacade facade = new NBPApiFacade();
        System.out.println(facade.getGold());
        System.out.println(facade.getRate("gbp"));
        System.out.println(facade.getTable("a", LocalDate.parse("2017-12-15")));
    }
}
//TODO: Consider usage of exceptions.
//TODO: Make periods generator.
//TODO: Implement facade for lists of rates/golds/tables.