package com.yarmarq.module;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static String formatDate(Date date) {
        if (date == null) return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
