package com.example.SpringBoot.utils.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lingjun.jlj
 * @date 2017/12/2
 * 时间转换工具类
 */
public class DateFormatUtils {

    /**
     * date转换String方法类
     * @param date
     * @param patter  时间格式
     * @return String
     * */
    public static String DateToString(Date date, String patter){
        SimpleDateFormat sdf=new SimpleDateFormat(patter);
        return sdf.format(date);
    }

    /**
     * String转换date方法类
     * @param obj
     * @param patter  时间格式
     * @return date
     * */
    public static Date StringToDate(String obj, String patter){
        SimpleDateFormat sdf=new SimpleDateFormat(patter);
        Date date = null;
        try {
            date = sdf.parse(obj);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date转LocalDateTime
     * @param date
     * */
    public static LocalDateTime DateToLocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime;
    }

    /**
     * Date转LocalDate
     * @param date
     * */
    public static LocalDate DateToLocalDate(Date date){
        return DateToLocalDateTime(date).toLocalDate();
    }

    /**
     * LocalDateTime转Date
     * @param time
     * */
    public static Date LocalDateTimeToDate(LocalDateTime time){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = time.atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     LocalDateTime转Date
     * @param time
     * */
    public static Date LocalDateToDate(LocalDate time){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = time.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    /**
     * LocalDateTime转String
     * @param localDateTime
     *@param patter
     * */
    public static String LocalDateTimeToString(LocalDateTime localDateTime,String patter){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(patter);
        return df.format(localDateTime);
    }

    /**
     * String转LocaDateTime
     * @param obj
     * */
    public static LocalDateTime StringToLocalDateTime(String obj){
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(obj,DATEFORMATTER);
        return localDateTime;
    }
    /**
     * String转LocaDate
     * @param obj
     * */
    public static LocalDate StringToLocalDate(String obj){
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(obj,DATEFORMATTER);
        return localDate;
    }

//    public static void main(String[] args){
//        Date time = new Date();
//        String str = DateToString(time,"yyyy-MM-dd");
//        System.out.println(str);
//    }
}
