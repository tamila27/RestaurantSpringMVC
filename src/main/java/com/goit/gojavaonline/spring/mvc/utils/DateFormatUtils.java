package com.goit.gojavaonline.spring.mvc.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    //private static final String DATE_FORMAT_STR = "MM/dd/yyyy";
    private static final SimpleDateFormat mysqlDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static String fromDate(Date date) {
        return mysqlDateTimeFormat.format( date );
    }

    public static Date toDate(String dateString) {
        //SimpleDateFormat dateFormat = new SimpleDateFormat( DATE_FORMAT_STR);
        try {
            return mysqlDateTimeFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
