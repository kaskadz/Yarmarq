package com.yarmarq.module;

import com.yarmarq.serializable.Gold;
import com.yarmarq.serializable.TRate;
import com.yarmarq.serializable.Table;

import java.util.Date;
import java.util.List;

public class NBPApiClient {
    public TRate getRate(String code) {
        return null;
    }

    public List<TRate> getRates(String code, Date startdate, Date enddate) {
        return null;
    }

    public Gold getGold() {
        return null;
    }

    public List<Gold> getGolds(Date startDate, Date endDate) {
        return null;
    }

    public Table getTable(String table, Date date) {
        return null;
    }

    public List<Table> getTables(String table, Date startDate, Date endDate) {
        return null;
    }
}
