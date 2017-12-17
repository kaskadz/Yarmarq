package com.yarmarq.serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.yarmarq.module.DateFormatter;

import java.io.Serializable;
import java.time.LocalDate;

public class RRate implements Serializable {
    private String no;                      // numer tabeli
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate effectiveDate;        // data publikacji
    private Double mid;                     // przeliczony kurs średni waluty (dotyczy tabel A oraz B)
    private Double bid;                     // przeliczony kurs kupna waluty (dotyczy tabeli C)
    private Double ask;                     // przeliczony kurs sprzedaży waluty (dotyczy tabeli C)

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Double getMid() {
        return mid;
    }

    public void setMid(Double mid) {
        this.mid = mid;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return String.format("No: %s, effectiveDate: %s, mid: %f, bid: %f, ask: %f", no, effectiveDate, mid, bid, ask);
    }
}
