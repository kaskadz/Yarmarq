package com.yarmarq.serializable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RRate implements Serializable {
    private String no;
    private Date effectiveDate;
    private Double mid;
    private Double bid;
    private Double ask;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(this.effectiveDate);
        return String.format("No: %s, effectiveDate: %s, mid: %f, bid: %f, ask: %f", no, date, mid, bid, ask);
    }
}
