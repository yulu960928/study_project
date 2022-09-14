package com.stat.demo.Test;

import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.util.Date;

public class Test04 {
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static void main(String[] args) throws ParseException {

        String string ="2022-08-10";
        Date anchorTime = DateUtils.parseDate(string, YYYY_MM_DD_HH_MM, YYYY_MM_DD);
        DateTime dateTime = new DateTime(anchorTime);
        int hour = dateTime.hourOfDay().get();
        int min = dateTime.minuteOfHour().get();
        System.out.println(hour);
        System.out.println(min);
    }

}
