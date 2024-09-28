package com.myorg.service.time;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@NoArgsConstructor
public class TimeManagerService implements Serializable {
    //TODO Review ISO 8601 format.
    private static final String ISO_FORMAT="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String TIME_ZONE="UTC";


    public String getInstantIsoFormat(){
        TimeZone timeZone=TimeZone.getTimeZone(TIME_ZONE);
        DateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(new Date());
    }

    public String getLocalDateTimeIsoFormat(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        formatter.withZone(TimeZone.getTimeZone(TIME_ZONE).toZoneId());
        return localDateTime.format(formatter);
    }
    public LocalDateTime getLocalDateTimeIsoFormat(String localDateTime){
        if(localDateTime==null){
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        formatter.withZone(TimeZone.getTimeZone(TIME_ZONE).toZoneId());
        return LocalDateTime.parse(localDateTime,formatter);
    }

    public LocalDateTime getLocalDateTime(){
        return LocalDateTime.now();
    }

}
