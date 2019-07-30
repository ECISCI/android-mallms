/*******************************************************************
 * Copyright (C) 2013 Neusoft Group Ltd. All rights reserved.
 *
 * @fileName:TimeUtils.java
 * @author:zhaoyong.neu
 * @version:v1.0.0
 * Modification History:
 * Date           Author           Version      Description
 * -----------------------------------------------------------------
 * 2013-9-11     zhaoyong.neu     v1.0.0       create
 *
 *******************************************************************/
package com.mincat.sample.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class TimeUtils {
    /**
     * 获取当前日期-yyyyMMdd
     *
     * @return 格式化时间yyyyMMdd
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间-yyyyMMddHHmmss
     *
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getFullCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间-yyyyMMddHHmmss
     *
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getFullCurrentTimes() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取前一天时间-yyyyMMddHHmmss
     *
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getFullYesterdayTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 1);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取一小时后时间-yyyyMMddHHmmss
     *
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getOneHourAfterCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(calendar.getTimeInMillis() + 3600 * 1000);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取三个月前时间-yyyyMMddHHmmss
     *
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String get3MonthsAgoTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 3);
        return sdf.format(calendar.getTime());
    }

    /**
     * yyyyMMddHHmmss转为 yyyy-MM-dd HH:mm:ss
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getFormatTime(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * yyyyMMddHHmmss转为 yyyy-MM-dd HH:mm:ss
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getFormatTimeYMD(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取当前时间-yyyyMMdd
     *
     * @param calendar
     * @return 格式化时间yyyyMMdd
     */
    public static String getFormatTime(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间-yyyyMMddHHmmss
     *
     * @param calendar
     * @return 格式化时间yyyyMMdd
     */
    public static String getFormatFullTime(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间-yyyyMMddHHmmss
     *
     * @param calendar
     * @return 格式化时间yyyyMMdd
     */
    public static String getFullFormatTime(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取当前时间-MM/dd
     *
     * @return 格式化时间MM/dd
     */
    public static String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取明天时间-MM/dd
     *
     * @return 格式化时间MM/dd
     */
    public static String getTommorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取后天时间-MM/dd
     *
     * @return 格式化时间MM/dd
     */
    public static String getAfterTommorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 2);
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd");
        return sdf.format(calendar.getTime());
    }

    /**
     * 获取日期-MM-dd
     *
     * @param dateString-yyyyMMddHHmmss
     * @return
     */
    public static String getDate(String dateString) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
        try {
            return sdf2.format(sdf1.parse(dateString));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取日期-MM-dd
     *
     * @param dateLong
     * @return
     */
    public static String getDate(Long dateLong) {
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
        return sdf2.format(dateLong);
    }

    /**
     * 获取时间-HH:mm
     *
     * @param dateString-yyyyMMddHHmmss
     * @return
     */
    public static String getTime(String dateString) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        try {
            return sdf2.format(sdf1.parse(dateString));
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 获取当前时间-yyyyMMddHHmmss
     *
     * @return 格式化时间yyyyMMddHHmmssS
     */
    public static String getFullCurrentTimeSSS() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar calendar = Calendar.getInstance();
        String time = sdf.format(calendar.getTime());


        return String.valueOf(time).toString();
    }

    /**
     * 格式化活动日期-MM-dd
     *
     * @param dateString-yyyyMMddHHmmss
     * @return
     */
    public static String getPlayDate(String dateString) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd");
        try {
            return sdf2.format(sdf1.parse(dateString));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * yyyyMMddHHmmss转为 yyyy-MM-dd HH:mm:ss
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getMsgToTime(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * yyyyMMddHHmmss转为 yyyyMMdd
     *
     * @param time yyyyMMdd
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getFlowToTime(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * yyyyMMddHHmmss转为 yyyy-MM-dd HH:mm:ss
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getSignToTime(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getSignToDay(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * yyyyMMddHHmmss转为 yyyy-MM-dd HH:mm:ss
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static String getMyGoldToTime(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * yyyyMMddHHmmss转为 yyyy-MM-dd HH:mm:ss
     *
     * @param time yyyy-MM-dd HH:mm:ss
     * @return 格式化时间yyyyMMddHHmmss
     */
    public static int getSignMonth(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        try {
            return Integer.valueOf(sdf2.format(sdf1.parse(time)));
        } catch (ParseException e) {
            return -1;
        }
    }

    public static Date formatDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.ENGLISH);
        Date d = null;
        try {
            d = sdf.parse(str);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    public static String getWifiSYTime(String sss) {
        if (!TextUtils.isEmpty(sss)) {
            long time = Long.valueOf(sss);
            long mm = time % 60;
            long hh = time / 60 / 60;
            return hh + "小时" + mm + "分";
        } else {
            return "0小时0分";
        }
    }

    public static String getMiBiaoHistoryTime(String sss) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        try {
            return sdf2.format(sdf1.parse(sss));
        } catch (ParseException e) {
            return null;
        }
    }

    public static int getThisMonthActSYDay(String timeString) {
        Date date = formatActDate(timeString);
        long time = date.getTime();
        long thistime = System.currentTimeMillis();
        long syTime = time - thistime;
        if (syTime > 0) {
            int syDay = (int) (syTime / 1000 / 60 / 60 / 24);
            return syDay;
        } else {
            return 0;
        }
    }

    public static Date formatActDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date d = null;
        try {
            d = sdf.parse(str);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 获取当前时间 MM月dd日 HH:mm
     */
    public static String getMyFlowMiBiaoThisDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        return sdf.format(calendar.getTime());
    }

    public static String getMyFlowMiBiaoThisDate(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM月dd日 HH:mm");
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getCommentDate(String time) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    public static boolean thisTimeIsNight() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
//        int am_or_pm = calendar.get(GregorianCalendar.AM_PM);
//        if ((hour >= 2&& am_or_pm == 1)||(hour < 6&& am_or_pm == 0)||(hour == 6&&minute < 30&& am_or_pm == 0)){
//            return true;
//        }
        return hour >= 21 || hour < 6 || (hour == 6 && minute < 30);
    }

    public static String thisTimeHH() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
    }

    /**
     * 获取当前日期-yyyyMM的月数
     */
    public static String getDateMonthTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        try {
            Date date = sdf.parse(time);
            long timel = date.getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timel);
            return String.valueOf(calendar.get(Calendar.MONTH) + 1);
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 获取当前日期-yyyyMMdd的天数
     */
    public static String getDateDayTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = sdf.parse(time);
            long timel = date.getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(timel);
            return String.valueOf(calendar.get(Calendar.DATE));
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 获取时间yyyy.MM.dd
     *
     * @param dateString-yyyyMMddHHmmss
     * @return
     */
    public static String getChartTime(String dateString) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");
        try {
            return sdf2.format(sdf1.parse(dateString));
        } catch (ParseException e) {
            return "";
        }
    }
}
