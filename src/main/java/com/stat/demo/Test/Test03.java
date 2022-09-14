package com.stat.demo.Test;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test03 {

    private static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
    private static long ONE_DAY = 24 * 60 * 60 * 1000;

    public static void main(String[] args) throws ParseException {
        //long milliSecond = 1659596017L;
//        Date date = new Date();
//        date.setTime(milliSecond);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));


        String date = "2022-08-01 12:30";
        Date parseDate = DateUtils.parseDate(date, DATE_FORMAT_YMDHM, DATE_FORMAT_YMD);

        int from, to;
        if (parseDate.getHours() == 0 && parseDate.getMinutes() == 0) {
            //只有日期，没有时间
            from = (int) (parseDate.getTime() / 1000);
            to = (int) ((parseDate.getTime() + ONE_DAY -1000) / 1000);
        } else {
            //查询前后30min的日志
            from = (int) (parseDate.getTime() / 1000 - 30 * 60);
            to = (int) (parseDate.getTime() / 1000 + 30 * 60);
        }
        System.out.println("current time:"+parseDate.getTime()/1000);
        System.out.println("from time:"+from);
            System.out.println("to time:"+ to);
    }
}
