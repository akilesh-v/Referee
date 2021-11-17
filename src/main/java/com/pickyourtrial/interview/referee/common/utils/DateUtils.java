package com.pickyourtrial.interview.referee.common.utils;

import com.pickyourtrial.interview.referee.common.deserializer.DateDeserializer;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiImplicitParam;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    @NotNull
    public static Date parseDate(String date, DateTimeFormatter formatter) {
        Instant instant = LocalDate.parse(date, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Locale getSupportedLocale() {
        return Locale.US;
    }

    public static Date getCurrentDate(){
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
