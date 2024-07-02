package com.rtm.pabean.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Logger;

public class DateUtil {

    public static String YYYY_MM_DD = "yyyy-MM-dd";
    public static String DD_MM_YYYY = "dd-MM-yyyy";
    public static String DD_MMM_YYYY = "dd-MMM-yyyy";
    public static String YYYYMMDD = "yyyyMMdd";
    public static String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static String YYYY_MM_DD_T_HHMMSS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static String HH_MM_SS = "HH:mm:ss";
    public static String YYYY = "YYYY";

    private static final Logger LOGGER = Logger.getLogger(DateUtil.class.getName());

    public static String toString(Date date, String format) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
    }

    public static String toString(Object date, String format) {
        if (date == null) {
            return "";
        }
        if (date instanceof Date) {
            return toString((Date) date, format);
        }
        return "";
    }

    public static Date toDate(LocalDateTime ldt) {
        if (ldt == null) {
            return null;
        }
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(String value, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
    }

    public static String convertFormat(String dateString, String from, String to) {
        Date date = toDate(dateString, from);
        return toString(date, to);
    }
}
