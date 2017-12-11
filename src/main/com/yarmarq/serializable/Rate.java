package com.yarmarq.serializable;

import java.io.Serializable;

public class Rate implements Serializable {
    private String country;                 // – nazwa kraju
    private String symbol;                  // – symbol waluty (numeryczny, dotyczy kursów archiwalnych)
    private String currency;                // – nazwa waluty
    private String code;                    // – kod waluty
    private String bid;                     // – przeliczony kurs kupna waluty (dotyczy tabeli C)
    private String ask;                     // – przeliczony kurs sprzedaży waluty (dotyczy tabeli C)
    private String mid;                     // – przeliczony kurs średni waluty (dotyczy tabel A oraz B)

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }
}
