package com.example.liangchen.note;

import java.util.Calendar;
import java.util.TimeZone;

public class Date {
    public String getDate() {
        Calendar calendar;
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append(calendar.get(Calendar.YEAR));
        stringBuilder.append("-");
        if (calendar.get(Calendar.MONTH) < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(calendar.get(Calendar.MONTH));
        stringBuilder.append("-");
        if (calendar.get(Calendar.DATE) < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(calendar.get(Calendar.DATE));
        stringBuilder.append(" ");
        if (calendar.get(Calendar.AM) == 0) {
            if (calendar.get(Calendar.HOUR) < 10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(calendar.get(Calendar.HOUR));
        } else {
            stringBuilder.append(calendar.get(Calendar.HOUR) + 12);
        }
        stringBuilder.append(":");
        if (calendar.get(Calendar.MINUTE) < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(calendar.get(Calendar.MINUTE));
        stringBuilder.append(":");
        if (calendar.get(Calendar.SECOND) < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(calendar.get(Calendar.SECOND));
        return stringBuilder.toString();
    }
}
