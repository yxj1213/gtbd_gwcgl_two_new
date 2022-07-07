package com.ttce.vehiclemanage.utils;

import android.content.res.Resources;
import android.text.TextUtils;

import com.ttce.vehiclemanage.R;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class TimeUtil {

    public static final String XING_QI = "星期";
    public static final String ZHOU = "周";
    private static final String[] WEEK = {"天", "一", "二", "三", "四", "五", "六"};
    // 准备第一个模板，从字符串中提取出日期数字
    private static String pat1 = "yyyy-MM-dd HH:mm:ss";
    // 准备第二个模板，将提取后的日期数字变为指定的格式
    private static String pat2 = "yyyy年MM月dd日 HH:mm:ss";
    private static String pat3 = "yyyy-MM-dd";
    private static String pat4 = "MM月dd日";
    private static String pat5 = "yyyyMMdd";

    public static String pat6 = "yyyy-MM-dd HH:mm";
    // 实例化模板对象
    private static SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);
    private static SimpleDateFormat sdf2 = new SimpleDateFormat(pat2);
    private static SimpleDateFormat sdf3 = new SimpleDateFormat(pat3);
    private static SimpleDateFormat sdf4 = new SimpleDateFormat(pat4);
    private static SimpleDateFormat sdf5 = new SimpleDateFormat(pat5);

    private static SimpleDateFormat sdf6 = new SimpleDateFormat(pat6);
    private static int MILL_MIN = 1000 * 60;
    private static int MILL_HOUR = MILL_MIN * 60;
    public static int MILL_DAY = MILL_HOUR * 24;
    private static Calendar msgCalendar = null;
    private static SimpleDateFormat dayFormat = null;
    private static SimpleDateFormat dateFormat = null;
    private static SimpleDateFormat yearFormat = null;

    // 2016-04-27 to 04月27日
    public static String converToSimpleFormat(String date) {
        String rs = null;
        try {
            Date d = sdf3.parse(date);
            rs = sdf4.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static Date format(String dateStr, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateStr);
    }

    // 2016-04-27 to 20160427
    public static String converTo1(String date) {
        String rs = null;
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            SimpleDateFormat pat = new SimpleDateFormat("HH:mm");
            Date d = format.parse(date);
            rs = pat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 2016-04-27 to 20160427
    public static String converTo2(String date) {
        String rs = null;
        try {
            SimpleDateFormat pat = new SimpleDateFormat("MM-dd");
            Date d = sdf3.parse(date);
            rs = pat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    // 2016-04-27 to 20160427
    public static String converToSimpleFormat2(String date) {
        String rs = null;
        try {
            Date d = sdf3.parse(date);
            rs = sdf5.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static String format(Timestamp date) {
        if (date == null) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(date);
    }

    public static int getDayCount(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @SuppressWarnings("deprecation")
    public static String formatTimeStamp(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(Date.parse(date));
    }

    public static String formatDate(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            return getTime(format.parse(date).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String getTime(long time) {
        if (time <= 0) {
            return "刚刚";
        }

        String commitDate = formatTime(time);

        if (TextUtils.isEmpty(commitDate)) {
            return null;
        }

        try {
            // 在主页面中设置当天时间
            Date nowTime = new Date();
            String currDate = sdf1.format(nowTime);
            Date date = null;

            // 将给定的字符串中的日期提取出来
            date = sdf1.parse(commitDate);

            int nowDate = Integer.valueOf(currDate.substring(8, 10));
            int commit = Integer.valueOf(commitDate.substring(8, 10));
            String monthDay = sdf2.format(date).substring(5, 12);
            String yearMonthDay = sdf2.format(date).substring(0, 12);
            int month = Integer.valueOf(monthDay.substring(0, 2));
            int day = Integer.valueOf(monthDay.substring(3, 5));

            if (month < 10 && day < 10) {
                monthDay = monthDay.substring(1, 3) + monthDay.substring(4);
            } else if (month < 10) {
                monthDay = monthDay.substring(1);
            } else if (day < 10) {
                monthDay = monthDay.substring(0, 3) + monthDay.substring(4);
            }

            int yearMonth = Integer.valueOf(yearMonthDay.substring(5, 7));
            int yearDay = Integer.valueOf(yearMonthDay.substring(8, 10));

            if (yearMonth < 10 && yearDay < 10) {
                yearMonthDay = yearMonthDay.substring(0, 5)
                        + yearMonthDay.substring(6, 8)
                        + yearMonthDay.substring(9);
            } else if (yearMonth < 10) {
                yearMonthDay = yearMonthDay.substring(0, 5)
                        + yearMonthDay.substring(6);
            } else if (yearDay < 10) {
                yearMonthDay = yearMonthDay.substring(0, 8)
                        + yearMonthDay.substring(9);
            }

            String str = " 00:00:00";
            float currDay = farmatTime(currDate.substring(0, 10) + str);
            float commitDay = farmatTime(commitDate.substring(0, 10) + str);
            int currYear = Integer.valueOf(currDate.substring(0, 4));
            int commitYear = Integer.valueOf(commitDate.substring(0, 4));
            int flag = (int) (farmatTime(currDate) / 1000
                    - farmatTime(commitDate) / 1000);
            String des = null;
            String hourMin = commitDate.substring(11, 16);
            int temp = flag;

            if (temp < 60) {
                if (commitDay < currDay) {
                    des = "昨天" + hourMin;
                } else {
                    des = "刚刚";
                }
            } else if (temp < 60 * 60) {
                if (commitDay < currDay) {
                    des = "昨天" + hourMin;
                } else {
                    des = temp / 60 + "分钟以前";
                }
            } else if (temp < 60 * 60 * 24) {
                int hour = temp / (60 * 60);
                if (commitDay < currDay) {
                    des = "昨天" + hourMin;
                } else {
                    if (hour < 6) {
                        des = hour + "小时前";
                    } else {
                        des = hourMin;
                    }
                }
            } else if (temp < (60 * 60 * 24 * 2)) {
                if (nowDate - commit == 1) {
                    des = "昨天" + hourMin;
                } else {
                    des = "前天" + hourMin;
                }
            } else if (temp < 60 * 60 * 60 * 3) {
                if (nowDate - commit == 2) {
                    des = "前天" + hourMin;
                } else {
                    if (commitYear < currYear) {
                        des = yearMonthDay + hourMin;
                    } else {
                        des = monthDay + hourMin;
                    }
                }
            } else {
                if (commitYear < currYear) {
                    des = yearMonthDay + hourMin;
                } else {
                    des = monthDay + hourMin;
                }
            }

            if (des == null) {
                des = commitDate;
            }

            return des;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return commitDate;
    }

    public static String getDeviceTime(long time) {
        return new SimpleDateFormat("MM-dd HH:mm").format(new Date(time));
    }

    public static Long farmatTime(String time) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = Date(sf.parse(time));
            return date.getTime();
        } catch (ParseException e) {
            return 0l;
        }
    }

    public static String farmatTimeByFormat(long time, String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(new Date(time));
    }

    public static Long farmatTime(String time, String format) {
        try {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = Date(sf.parse(time));
            return date.getTime();
        } catch (ParseException e) {
            return 0l;
        }
    }

    public static Date Date(Date date) {
        Date datetimeDate;
        datetimeDate = new Date(date.getTime());
        return datetimeDate;
    }

    public static String formatTimeStamp1(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }

        DateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return format1.format(format2.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String getDay(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }

        DateFormat format1 = new SimpleDateFormat("dd");
        DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return format1.format(format2.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String formatTimeStamp2(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }

        DateFormat format1 = new SimpleDateFormat("HH:mm:ss");
        DateFormat format2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            return format1.format(format2.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String formatTime(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            return format.format(format.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String toLongString(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            return String.valueOf(format.parse(date).getTime());
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String formatTime(long date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new Date(date));
    }

    public static String formatTime1(long date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(date));
    }

    public static String formatCrashTime(long date) {
        return new SimpleDateFormat("yyyy-MM-dd_HH:mm").format(new Date(date));
    }

    public static String formatTime2(long date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(date));
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm")
                .format(new Date(System.currentTimeMillis()));
    }

    public static String getCurrentTimes() {
        return new SimpleDateFormat("HH:mm")
                .format(new Date(System.currentTimeMillis()));
    }

    public static String getCurrentTime2() {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                .format(new Date(System.currentTimeMillis()));
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date(System.currentTimeMillis()));
    }

    public static String getLastDate(String currentDate) {
        if (TextUtils.isEmpty(currentDate)) {
            return "";
        }

        try {
            Calendar c = Calendar.getInstance();
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.setTime(date);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day - 1);

            return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getNextDate(String currentDate) {
        if (TextUtils.isEmpty(currentDate)) {
            return "";
        }

        try {
            Calendar c = Calendar.getInstance();
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.setTime(date);
            int day = c.get(Calendar.DATE);
            c.set(Calendar.DATE, day + 1);

            return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    // 整改时间：当前日期一周之后
    public static String getGzsj() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.DATE, 7);
        return sdf.format(cal.getTime());
    }

    // 处理日期：当前日期三天之后
    public static String getClrq() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        cal.add(Calendar.DATE, 3);
        return sdf.format(cal.getTime());
    }

    public static String getThisMonth() {
        return new SimpleDateFormat("yyyy-MM")
                .format(new Date(System.currentTimeMillis()));
    }

    public static String getDeviceSignalTime(long time) {
        String rs = "--";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            Date d = sdf.parse(time + "");
            rs = formatTime(d.getTime());
        } catch (Exception e) {
        }
        return rs;
    }

    public static boolean isUpToday(String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date currentDate = format.parse(date);
            Date today = format.parse(getCurrentTime());

            if (currentDate.getTime() >= today.getTime()) {
                return true;
            }

        } catch (ParseException e) {
            e.printStackTrace();

            return false;
        }

        return false;
    }

    public static String getYesterday(String today) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        if (TextUtils.isEmpty(today)) {
            return format.format(
                    new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000));
        }

        try {
            Date todayDate = format.parse(today);

            return format.format(
                    new Date(todayDate.getTime() - 24 * 60 * 60 * 1000));
        } catch (ParseException e) {
            e.printStackTrace();

            return null;
        }
    }

    public static String[] getTodayArray() {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat format2 = new SimpleDateFormat("MM-dd HH:mm");

        Date date = new Date(System.currentTimeMillis());

        String[] todayArray = new String[2];
        todayArray[0] = format1.format(date);
        todayArray[1] = format2.format(date);

        return todayArray;
    }

    public static String getWeek(int num, String format) {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int weekNum = c.get(Calendar.DAY_OF_WEEK) + num;
        if (weekNum > 7)
            weekNum = weekNum - 7;
        return format + WEEK[weekNum - 1];
    }

    public static String getZhouWeek() {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd");
        return format.format(new Date(System.currentTimeMillis())) + " "
                + getWeek(0, ZHOU);
    }

    public static String getDay(long timesamp) {
        if (timesamp == 0L)
            return "未";
        String result = "未";
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        Date today = new Date(System.currentTimeMillis());
        Date otherDay = new Date(timesamp);
        int temp = Integer.parseInt(sdf.format(today))
                - Integer.parseInt(sdf.format(otherDay));

        switch (temp) {
            case 0:
                result = "今天" + getTime(timesamp);
                break;
            case 1:
                result = "昨天" + getTime(timesamp);
                break;
            case 2:
                result = "前天" + getTime(timesamp);
                break;

            default:
                result = temp + "天前" + getTime(timesamp);
                break;
        }

        return result;
    }

    public static long getLongTime(String time) {
        try {
            time = time.substring(0, time.indexOf('.'));
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                    .parse(time);
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public static String getTime_(long time) {
        return new SimpleDateFormat("HH:mm").format(new Date(time));
    }

    public static String formatServerTime() {
        return new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date(System.currentTimeMillis()));
    }

    public static String getDateTime(long time) {
        return new SimpleDateFormat("MM/dd").format(new Date(time));
    }

    public static String getDjkDate(long time) {
        return new SimpleDateFormat("MM月dd日").format(new Date(time));
    }

    public static String getListTime(Resources res, long time) {
        long now = System.currentTimeMillis();
        long msg = time;

        Calendar nowCalendar = Calendar.getInstance();

        if (msgCalendar == null)
            msgCalendar = Calendar.getInstance();

        msgCalendar.setTimeInMillis(time);

        long calcMills = now - msg;

        long calSeconds = calcMills / 1000;
        if (calSeconds < 60)
            // return res.getString(R.string.justnow);
            return new StringBuilder().append(calSeconds)
                    .append(res.getString(R.string.sec)).toString();

        long calMins = calSeconds / 60;
        if (calMins < 60)
            return new StringBuilder().append(calMins)
                    .append(res.getString(R.string.min)).toString();

        long calHours = calMins / 60;
        if (calHours < 24 && isSameDay(nowCalendar, msgCalendar)) {
            if (dayFormat == null)
                dayFormat = new SimpleDateFormat("HH:mm");
            String result = dayFormat.format(msgCalendar.getTime());
            return new StringBuilder().append(res.getString(R.string.today))
                    .append(" ").append(result).toString();
        }

        long calDay = calHours / 24;
        if (calDay < 31) {
            if (isYesterDay(nowCalendar, msgCalendar)) {
                if (dayFormat == null)
                    dayFormat = new SimpleDateFormat("HH:mm");
                String result = dayFormat.format(msgCalendar.getTime());
                return new StringBuilder(res.getString(R.string.yesterday))
                        .append(" ").append(result).toString();
            } else if (isTheDayBeforeYesterDay(nowCalendar, msgCalendar)) {
                if (dayFormat == null)
                    dayFormat = new SimpleDateFormat("HH:mm");
                String result = dayFormat.format(msgCalendar.getTime());
                return new StringBuilder(
                        res.getString(R.string.the_day_before_yesterday))
                        .append(" ").append(result).toString();
            } else {
                if (dateFormat == null)
                    dateFormat = new SimpleDateFormat(
                            res.getString(R.string.date_format));
                String result = dateFormat.format(msgCalendar.getTime());
                return new StringBuilder(result).toString();
            }
        }

        long calMonth = calDay / 31;

        if (calMonth < 12) {
            if (dateFormat == null)
                dateFormat = new SimpleDateFormat(
                        res.getString(R.string.date_format));

            String result = dateFormat.format(msgCalendar.getTime());
            return new StringBuilder().append(result).toString();

        }
        if (yearFormat == null)
            yearFormat = new SimpleDateFormat(
                    res.getString(R.string.year_format));
        String result = yearFormat.format(msgCalendar.getTime());
        return new StringBuilder().append(result).toString();

    }

    public static boolean isSameHalfDay(Calendar now, Calendar msg) {
        int nowHour = now.get(Calendar.HOUR_OF_DAY);
        int msgHOur = msg.get(Calendar.HOUR_OF_DAY);

        if (nowHour <= 12 & msgHOur <= 12) {
            return true;
        } else if (nowHour >= 12 & msgHOur >= 12) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSameDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return nowDay == msgDay;
    }

    private static boolean isYesterDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return (nowDay - msgDay) == 1;
    }

    private static boolean isTheDayBeforeYesterDay(Calendar now, Calendar msg) {
        int nowDay = now.get(Calendar.DAY_OF_YEAR);
        int msgDay = msg.get(Calendar.DAY_OF_YEAR);

        return (nowDay - msgDay) == 2;
    }

    // 年月日
    public static String formatUserCenterTime(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
    }

    // 格式：04.08
    public static String formatUserCenterTime1(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String todayStr = format.format(new Date(System.currentTimeMillis()));
        String timeTempStr = format.format(new Date(time));

        if (todayStr.equals(timeTempStr)) {
            return "今天";
        }

        return new SimpleDateFormat("MM.dd").format(new Date(time));
    }

    public static String formatLifeContentTime(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(time));
    }

    public static String getWeek(String timeStr) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = format.parse(timeStr);
            return getWeekOfTime(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getWeekOfTime(long time) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};

        Date date = new Date(time);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;

        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    public static String formatPhotoTime(long time) {
        String timeStr = null;

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            String todayStr = format
                    .format(new Date(System.currentTimeMillis()));
            String timeTempStr = format.format(new Date(time));

            if (todayStr.equals(timeTempStr)) {
                timeStr = new SimpleDateFormat("HH:mm").format(new Date(time));
            } else {
                String yearStr1 = todayStr.substring(0, 4);
                String yearStr2 = timeTempStr.substring(0, 4);

                if (yearStr1.equals(yearStr2)) {
                    timeStr = new SimpleDateFormat("MM-dd HH:mm")
                            .format(new Date(time));
                } else {
                    timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .format(new Date(time));
                }
            }
        } catch (Exception e) {
            timeStr = null;
        }

        if (TextUtils.isEmpty(timeStr)) {
            timeStr = new SimpleDateFormat("MM-dd HH:mm")
                    .format(new Date(time));
        }

        return timeStr;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getNowDate() {
        String temp_str = "";
        Date dt = new Date();
        // 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
        // yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        temp_str = sdf.format(dt);
        return temp_str;
    }


    /**
     * 比较两个时间字符串大小
     *
     * @param a
     * @param b
     * @return
     */
    public static int compareToTime(String a, String b) {
        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(b)) {
            return -2;
        }
        int c = a.compareTo(b);
        if (c == 0) {
            return 0;
        } else if (c > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    // birthday格式：0000-00-00
    public static String getAge(String birthday) {
        if (TextUtils.isEmpty(birthday)) {
            return "0";
        }

        try {
            int year = Integer.parseInt(birthday.substring(0, 4));
            int currentYear = getCurrentYear();

            if (currentYear > year) {
                return currentYear - year + "";
            }

            if (currentYear < year) {
                return "0";
            }

            int month = Integer.parseInt(birthday.substring(5, 7));
            int currentMonth = getCurrentMonth();

            if (currentMonth > month) {
                return currentMonth - month + "";
            }

            if (currentMonth < month) {
                return "0";
            }

            int day = Integer.parseInt(birthday.substring(7, 9));
            int currentDay = getCurrentDay();

            if (currentDay > day) {
                return currentDay - day + "";
            }

            if (currentDay < day) {
                return "0";
            }

            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    public static int getCurrentYear() {
        try {
            String currentYear = new SimpleDateFormat("yyyy").format(new Date(System
                    .currentTimeMillis()));
            return Integer.parseInt(currentYear);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int getCurrentMonth() {
        try {
            String currentMonth = new SimpleDateFormat("MM").format(new Date(System
                    .currentTimeMillis()));
            return Integer.parseInt(currentMonth);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static int getCurrentDay() {
        try {
            String currentDay = new SimpleDateFormat("dd").format(new Date(System
                    .currentTimeMillis()));
            return Integer.parseInt(currentDay);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getCurrentHour() {
        try {
            return new SimpleDateFormat("HH").format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
            return "00";
        }
    }

    public static String getCurrentMinute() {
        try {
            return new SimpleDateFormat("mm").format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
            return "00";
        }
    }

    public static String getCurrentSecond() {
        try {
            return new SimpleDateFormat("ss").format(new Date(System.currentTimeMillis()));
        } catch (Exception e) {
            e.printStackTrace();
            return "00";
        }
    }

    // 2017-07-27 00:00:00
    public static String getRKDate_value(String date) {
        if (TextUtils.isEmpty(date) || date.length() < 10) {
            return date;
        }
        return date.substring(0, 4) + "年" + date.substring(5, 7) + "月" + date.substring(8, 10) +
                "日";
    }

    // 2017-07-27 00:00:00
    public static String getRKTime_value(String date) {
        if (TextUtils.isEmpty(date) || date.length() != 19) {
            return "";
        }
        return date.substring(11, 13) + "时" + date.substring(14, 16) + "分";
    }

    // 2017-07-27 00:00:00
    public static String getRKDate(String date) {
        if (TextUtils.isEmpty(date) || date.length() < 10) {
            return date;
        }
        return date.substring(0, 10);
    }

    // 2017-07-27 00:00:00
    public static String getRKDateTime(String date) {
        if (TextUtils.isEmpty(date) || date.length() < 16) {
            return date;
        }
        return date.substring(0, 16);
    }

    // 2017-07-27 00:00:00
    public static String getRKTime(String date) {
        if (TextUtils.isEmpty(date) || date.length() != 19) {
            return "";
        }
        return date.substring(11);
    }

    // 补全日期：2017-07-27 00:00:00
    public static String getFullDate(String date) {
        if (TextUtils.isEmpty(date) || date.length() == 19) {
            return date;
        }

        if (date.length() == 16) {
            return date + ":00";
        }

        if (date.length() == 13) {
            return date + ":00:00";
        }

        if (date.length() == 10) {
            return date + " 00:00:00";
        }

        return date;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    public static int differentDaysByMillisecond(String date1Str, String date2Str) {
        long time1 = 0;
        long time2 = 0;
        try {
            time1 = sdf6.parse(date1Str).getTime();
            time2 = sdf6.parse(date2Str).getTime();
        }catch (ParseException e){

        }
        int days = (int) ((time1 - time2) / (1000 * 3600 * 24));
        return days;
    }
}