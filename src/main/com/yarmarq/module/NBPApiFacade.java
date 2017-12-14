package com.yarmarq.module;

import com.yarmarq.serializable.Gold;
import com.yarmarq.serializable.Rate;
import com.yarmarq.serializable.Table;

import java.util.Date;
import java.util.List;

public class NBPApiFacade {

    public Rate getRate(String code) {
        // http://api.nbp.pl/api/exchangerates/rates/a/{code}/
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/"; // code
        return null;
    }

    public List<Rate> getRates(String code, Date startdate, Date enddate) {
        // http://api.nbp.pl/api/exchangerates/rates/a/{code}/{startDate}/{endDate}/
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/%s/%s/%s/"; // code, startDate, endDate
        return null;
    }

    public Gold getGold() {
        // http://api.nbp.pl/api/cenyzlota
        String url = "http://api.nbp.pl/api/cenyzlota";
        return null;
    }

    public List<Gold> getGolds(Date startDate, Date endDate) {
        // http://api.nbp.pl/api/cenyzlota/{startDate}/{endDate}
        String url = "http://api.nbp.pl/api/cenyzlota/%s/%s/"; // startDate, endDate
        return null;
    }

    public Table getTable(String table, Date date) {
        // http://api.nbp.pl/api/exchangerates/tables/{table}/{date}/
        String url = "http://api.nbp.pl/api/exchangerates/tables/%s/%s/"; // table, date
        return null;
    }

    public List<Table> getTables(String table, Date startDate, Date endDate) {
        // http://api.nbp.pl/api/exchangerates/tables/{table}/{startDate}/{endDate}/
        String url = "http://api.nbp.pl/api/exchangerates/tables/%s/%s/%s/"; // table, startDate, endDate
        return null;
    }
}
