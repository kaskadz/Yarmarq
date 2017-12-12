package com.yarmarq.serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Table implements Serializable {
    private String table;                   // typ tabeli
    private String no;                      // – numer tabeli
    private Date tradingDate;             // – data notowania (dotyczy tabeli C)
    private Date effectiveDate;           // – data publikacji
    private TRate[] rates;                 // – lista kursów poszczególnych walut w tabeli

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

    public Date getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(Date tradingDate) {
        this.tradingDate = tradingDate;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public TRate[] getRates() {
        return rates;
    }

    public void setRates(TRate[] rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String tdate = (this.tradingDate != null) ? sdf.format(this.tradingDate) : null;
        String edate = (this.effectiveDate != null) ? sdf.format(this.effectiveDate) : null;
        StringBuilder sb = new StringBuilder();
        sb.append("> TABLE: [Table: ");
        sb.append(table);
        sb.append("] [No: ");
        sb.append(no);
        sb.append("] [Trading Date: ");
        sb.append(tdate);
        sb.append("] [Effective Date: ");
        sb.append(edate);
        sb.append("]\n");
        for (TRate rate : rates) {
            sb.append(rate);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Table[] table = mapper.readValue(new URL("http://api.nbp.pl/api/exchangerates/tables/a/2012-01-01/2012-01-31/?format=json"), Table[].class);
            Arrays.asList(table).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
