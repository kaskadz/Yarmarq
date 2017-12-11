package com.yarmarq.serializable;

import java.io.Serializable;

public class Gold implements Serializable {
    private String Date;                    // – data publikacji
    private String Code;                    // – wyliczona w NBP cena 1 g złota (w próbie 1000)

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
}
