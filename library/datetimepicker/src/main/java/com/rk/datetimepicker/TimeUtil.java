package com.rk.datetimepicker;

import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

	// 准备第一个模板，从字符串中提取出日期数字
	private static String pat1 = "yyyy-MM-dd HH:mm:ss";

	// 准备第二个模板，将提取后的日期数字变为指定的格式
	private static String pat2 = "yyyy年MM月dd日 HH:mm:ss";

	private static String pat3 = "yyyy-MM-dd";

	// 实例化模板对象
	private static SimpleDateFormat sdf1 = new SimpleDateFormat(pat1);
	private static SimpleDateFormat sdf2 = new SimpleDateFormat(pat2);
	private static SimpleDateFormat sdf3 = new SimpleDateFormat(pat3);

	public static String format(Timestamp date) {
		if (date == null) {
			return null;
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(date);
	}

	public static String format(long date) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(new Date(date));
	}

	public static String format(long date, String formatStr) {
		DateFormat format = new SimpleDateFormat(formatStr);

		return format.format(new Date(date));
	}


	// 秒转换成 xx:xx:xx
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00小时00分00秒";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				if (minute == 0) {
					timeStr = unitFormat(second) + "秒";
				} else {
					timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
				}
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99小时59分59秒";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
			}
		}
		return timeStr;
	}

	private static String unitFormat(int i) {
		/*
		 * String retStr = null; if (i >= 0 && i < 10) retStr = "0" +
		 * Integer.toString(i); else retStr = "" + i; return retStr;
		 */
		return i + "";
	}

	@SuppressWarnings("deprecation")
	public static String formatTimeStamp(String date) {
		if (TextUtils.isEmpty(date)) {
			return null;
		}

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		return format.format(Date.parse(date));
	}

	public static String getTime(long longDate) {
		if (longDate == 0) {
			return "";
		}

		// 在主页面中设置当天时间
		String des = null;
		Date nowTime = new Date();
		String currDate = sdf1.format(nowTime);
		Date date = null;
		String commitDate = null;

		try {
			// 将给定的字符串中的日期提取出来
			date = new Date(longDate);
			commitDate = sdf1.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (nowTime.getTime() < date.getTime()) {
			des = "您已穿越";
			return des;
		}

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
			yearMonthDay = yearMonthDay.substring(0, 5) + yearMonthDay.substring(6, 8) + yearMonthDay.substring(9);
		} else if (yearMonth < 10) {
			yearMonthDay = yearMonthDay.substring(0, 5) + yearMonthDay.substring(6);
		} else if (yearDay < 10) {
			yearMonthDay = yearMonthDay.substring(0, 8) + yearMonthDay.substring(9);
		}

		String str = " 00:00:00";
		float currDay = farmatTime(currDate.substring(0, 10) + str);
		float commitDay = farmatTime(commitDate.substring(0, 10) + str);
		int currYear = Integer.valueOf(currDate.substring(0, 4));
		int commitYear = Integer.valueOf(commitDate.substring(0, 4));
		int flag = (int) (farmatTime(currDate) / 1000 - farmatTime(commitDate) / 1000);
		String hourMin = commitDate.substring(11, 16);
		int temp = flag;

		if (temp < 60) {
			if (commitDay < currDay) {
				des = "昨天  " + hourMin;
			} else {
				des = "刚刚";
			}
		} else if (temp < 60 * 60) {
			if (commitDay < currDay) {
				des = "昨天  " + hourMin;
			} else {
				des = temp / 60 + "分钟前";
			}
		} else if (temp < 60 * 60 * 24) {
			int hour = temp / (60 * 60);
			if (commitDay < currDay) {
				des = "昨天  " + hourMin;
			} else {
				if (hour < 6) {
					des = hour + "小时前";
				} else {
					des = hourMin;
				}
			}
		} else if (temp < (60 * 60 * 24 * 2)) {
			if (nowDate - commit == 1) {
				des = "昨天  " + hourMin;
			} else {
				des = "前天  " + hourMin;
			}
		} else if (temp < 60 * 60 * 60 * 3) {
			if (nowDate - commit == 2) {
				des = "前天  " + hourMin;
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
	}

	public static Long farmatTime(String string) {
		Date date = null;

		try {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			date = Date(sf.parse(string));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date.getTime();
	}

	public static Long farmatTime(String string, String format) {
		Date date = null;

		try {
			SimpleDateFormat sf = new SimpleDateFormat(format);
			date = Date(sf.parse(string));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date.getTime();
	}

	public static Date Date(Date date) {
		Date datetimeDate;
		datetimeDate = new Date(date.getTime());
		return datetimeDate;
	}

	public static int getDayCount(int year, int month) {
		int day = 0;

		switch (month) {
		case 2:
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				day = 29;
			} else {
				day = 28;
			}
			break;
		case 4:
			day = 30;
			break;
		case 6:
			day = 30;
			break;
		case 9:
			day = 30;
			break;
		case 11:
			day = 30;
			break;
		default:
			day = 31;
			break;
		}

		return day;
	}
}