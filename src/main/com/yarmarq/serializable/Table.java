package com.yarmarq.serializable;

import java.io.Serializable;

public class Table implements Serializable {
    private String table;                   // typ tabeli
    private String no;                      // – numer tabeli
    private String tradingDate;             // – data notowania (dotyczy tabeli C)
    private String effectiveDate;           // – data publikacji
    private Rate[] rates;                   // – lista kursów poszczególnych walut w tabeli

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(String tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Rate[] getRates() {
        return rates;
    }

    public void setRates(Rate[] rates) {
        this.rates = rates;
    }
}
