package com.kit.common.util.common;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Lucifer
 * @date 2017/12/2
 * 时间转换工具类
 */
public class DateFormatUtils {

    /**
     * date -> String方法类
     *
     * @param date
     * @param patter 时间格式
     * @return String
     */
    public static String DateToString(Date date, String patter) {
        SimpleDateFormat sdf = new SimpleDateFormat(patter);
        return sdf.format(date);
    }

    /**
     * String -> date方法类
     *
     * @param obj
     * @param patter 时间格式
     * @return date
     */
    public static Date StringToDate(String obj, String patter) {
        SimpleDateFormat sdf = new SimpleDateFormat(patter);
        Date date = null;
        try {
            date = sdf.parse(obj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date
     */
    public static LocalDateTime DateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    /**
     * Date -> LocalDate
     *
     * @param date
     */
    public static LocalDate DateToLocalDate(Date date) {
        return DateToLocalDateTime(date).toLocalDate();
    }

    /**
     * LocalDateTime -> Date
     *
     * @param time
     */
    public static Date LocalDateTimeToDate(LocalDateTime time) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = time.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * LocalDateTime -> Date
     *
     * @param time
     */
    public static Date LocalDateToDate(LocalDate time) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = time.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * LocalDateTime -> String
     *
     * @param localDateTime
     * @param patter
     */
    public static String LocalDateTimeToString(LocalDateTime localDateTime, String patter) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(patter);
        return df.format(localDateTime);
    }

    /**
     * String -> LocalDateTime
     *
     * @param obj
     */
    public static LocalDateTime StringToLocalDateTime(String obj) {
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(obj, DATEFORMATTER);
        return localDateTime;
    }

    /**
     * String -> LocalDate
     *
     * @param obj
     */
    public static LocalDate StringToLocalDate(String obj) {
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(obj, DATEFORMATTER);
        return localDate;
    }

    public static Long LocalDateTimeToTimeStamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            throw new IllegalArgumentException("localDateTime must not be null");
        } else {
            return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
    }

    public static Long StringToTimeStamp(String dateString, String dateFormat) {
        if (StringUtils.isEmpty(dateString)) {
            throw new IllegalArgumentException("dateString must not be empty");
        } else {
            try {
                if (StringUtils.isEmpty(dateFormat)) {
                    return LocalDateTimeToTimeStamp(LocalDateTime.parse(dateString));
                } else {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat);
                    return LocalDateTimeToTimeStamp(LocalDateTime.parse(dateString, dateTimeFormatter));
                }
            } catch (Exception var3) {
                throw new IllegalArgumentException("dateString parse error");
            }
        }
    }

}
