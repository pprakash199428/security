package com.spring.security.common;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class DateUtilLocal implements AttributeConverter< LocalDateTime, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime attribute) {
        return attribute != null ? Timestamp.valueOf(attribute) : null;
    }
    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp dbData) {
        return dbData != null ? dbData.toLocalDateTime() : null;
    }

    public static LocalDateTime formatCurrentDateTime(String dateString, String formatterString) {
        DateTimeFormatter df = new DateTimeFormatterBuilder().parseCaseInsensitive() .appendPattern(formatterString).toFormatter(Locale.ENGLISH);
        LocalDate ld = LocalDate.parse(dateString, df);
        return ld.atStartOfDay();
    }
    public static LocalDateTime formatCurrentDateTimeInMM(String dateString, String formatterString) {
        DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern(formatterString).toFormatter(Locale.ENGLISH);
        LocalDate ld = LocalDate.parse(dateString, df);
        return ld.atStartOfDay();
    }

    public static LocalDateTime getLocalDateTimeFromMilli(long milliseconds){
        LocalDateTime cvDate =
                Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return cvDate;
    }

    public static LocalDateTime getLocalDateTimeFromString(String dateTimeText){
       LocalDateTime localDateTime=LocalDateTime.parse(dateTimeText, DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm"));
        return localDateTime;
    }

    public static LocalDateTime getLocalDateTimeFromStringYYYY(String dateTimeText){
        LocalDateTime localDateTime=LocalDateTime.parse(dateTimeText, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        return localDateTime;
    }

}
