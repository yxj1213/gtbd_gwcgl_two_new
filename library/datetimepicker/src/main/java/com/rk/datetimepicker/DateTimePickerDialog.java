package com.rk.datetimepicker;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.MaterialDialog.SingleButtonCallback;
import com.afollestad.materialdialogs.Theme;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateTimePickerDialog {
	private MaterialDialog dialog;
	private PickerView yearPicker, monthPicker, dayPicker;
	private PickerView hourPicker, minutePicker, secondPicker;
	private OnDateTimePickerPickedListener listener;
	private int what;
	private View whatView;
	private int pickerType = YYYY_MM_DD_HH_MM_SS;
	private String format = "yyyy-MM-dd HH:mm:ss";
	private long limitTime;
	private LIMIT_TYPE limitType;
	private int extendedYear=100;

	private String date;
	private String selectDay;

	public static final int YYYY = 1;
	public static final int YYYY_MM = 2;
	public static final int YYYY_MM_DD = 3;
	public static final int YYYY_MM_DD_HH = 4;
	public static final int YYYY_MM_DD_HH_MM = 5;
	public static final int YYYY_MM_DD_HH_MM_SS = 6;
	public static final int HH_MM_SS = 7;

	public enum LIMIT_TYPE {
		BEFORE, AFTER
	}

	public DateTimePickerDialog(int whatDialog, Activity mActivity, int themeColor, String title,
                                OnDateTimePickerPickedListener pickListener) {

		this.listener = pickListener;
		this.what = whatDialog;

		initDateUI(mActivity, title,themeColor);

		initTimePicker();

		initDatePicker();

	}

	public DateTimePickerDialog(int whatDialog, Activity mActivity, int themeColor, String title,
                                OnDateTimePickerPickedListener pickListener, int pickerType) {

		this.listener = pickListener;
		this.what = whatDialog;
		this.pickerType = pickerType;

		initDateUI(mActivity, title,themeColor);

		initTimePicker();

		initDatePicker();
	}

	public DateTimePickerDialog(int whatDialog, Activity mActivity, int themeColor, String title,
                                OnDateTimePickerPickedListener pickListener, int pickerType, View view) {

		this.listener = pickListener;
		this.what = whatDialog;
		this.pickerType = pickerType;
		this.whatView = view;

		initDateUI(mActivity, title,themeColor);

		initTimePicker();

		initDatePicker();

	}

	public DateTimePickerDialog(int whatDialog, Activity mActivity, int themeColor, String title,
                                OnDateTimePickerPickedListener pickListener, int pickerType, View view, long limitTime,
                                LIMIT_TYPE limitType) {

		this.listener = pickListener;
		this.what = whatDialog;
		this.pickerType = pickerType;
		this.whatView = view;
		this.limitTime = limitTime;
		this.limitType = limitType;

		initDateUI(mActivity, title,themeColor);

		initTimePicker();

		initDatePicker();

	}

	private void initDateUI(final Activity mActivity, final String title, int themeColor) {
		int layout = R.layout.dialog_datetime_picker_all;
		switch (pickerType) {
		case YYYY:
			layout = R.layout.dialog_datetime_picker_yyyy;
			format = "yyyy";
			break;
		case YYYY_MM:
			layout = R.layout.dialog_datetime_picker_yyyymm;
			format = "yyyy-MM";
			break;
		case YYYY_MM_DD:
			layout = R.layout.dialog_datetime_picker_yyyymmdd;
			format = "yyyy-MM-dd";
			break;
		case YYYY_MM_DD_HH:
			layout = R.layout.dialog_datetime_picker_yyyymmddhh;
			format = "yyyy-MM-dd HH";
			break;
		case YYYY_MM_DD_HH_MM:
			layout = R.layout.dialog_datetime_picker_yyyymmddhhmm;
			format = "yyyy-MM-dd HH:mm";
			break;
		case YYYY_MM_DD_HH_MM_SS:
			layout = R.layout.dialog_datetime_picker_all;
			format = "yyyy-MM-dd HH:mm:ss";
			break;
		case HH_MM_SS:
			layout = R.layout.dialog_datetime_picker_hhmmss;
			format = "HH:mm:ss";
			break;
		}

		dialog = new MaterialDialog.Builder(mActivity).theme(Theme.LIGHT).title(title).customView(layout, false)
				.positiveText("确认").onPositive(new SingleButtonCallback() {

					@Override
					public void onClick(MaterialDialog dialog, DialogAction which) {
						if (listener != null) {
							String dateTimeStr = getSelectDateTime();
							long dateTimeLng = TimeUtil.farmatTime(dateTimeStr, format);
							
							if(limitType== LIMIT_TYPE.AFTER){
								if(dateTimeLng<limitTime){
									Toast.makeText(mActivity,"请选择"+TimeUtil.format(limitTime, format)+"之后的时间",Toast.LENGTH_LONG);
									return;
								}
							}else if(limitType== LIMIT_TYPE.BEFORE){
								if(dateTimeLng>limitTime){
									Toast.makeText(mActivity,"请选择"+TimeUtil.format(limitTime, format)+"之前的时间",Toast.LENGTH_LONG);
									return;
								}
							}
							
							listener.onPicked(what, dateTimeStr);
							listener.onPicked(what, dateTimeLng);
							if (whatView != null) {
								listener.onPicked(title, dateTimeLng, dateTimeStr, whatView);
							}
						}
					}

				}).positiveColorRes(themeColor).negativeText("取消").negativeColorRes(R.color.picker_gray)
				.build();

		yearPicker = (PickerView) dialog.getCustomView().findViewById(R.id.yearPicker);
		monthPicker = (PickerView) dialog.getCustomView().findViewById(R.id.monthPicker);
		dayPicker = (PickerView) dialog.getCustomView().findViewById(R.id.dayPicker);

		hourPicker = (PickerView) dialog.getCustomView().findViewById(R.id.hourPicker);
		minutePicker = (PickerView) dialog.getCustomView().findViewById(R.id.minutePicker);
		secondPicker = (PickerView) dialog.getCustomView().findViewById(R.id.secondPicker);
	}

	// 初始化时间
	private void initTimePicker() {
		List<String> data1 = new ArrayList<String>();
		for (int i = 0; i <= 23; i++) {
			data1.add(i < 10 ? "0" + i : "" + i);
		}

		List<String> data2 = new ArrayList<String>();
		for (int i = 0; i <= 59; i++) {
			data2.add(i < 10 ? "0" + i : "" + i);
		}
		List<String> data3 = new ArrayList<String>();
		for (int i = 0; i <= 59; i++) {
			data3.add(i < 10 ? "0" + i : "" + i);
		}

		if (hourPicker != null) {
			hourPicker.setData(data1);
			hourPicker.setSelected(getCurrenthourPicker());
		}

		if (minutePicker != null) {
			minutePicker.setData(data2);
			minutePicker.setSelected(getCurrentminutePickerPicker());
		}

		if (secondPicker != null) {
			secondPicker.setData(data3);
			secondPicker.setSelected(getCurrentsecondPickerPicker());
		}

	}
	public static boolean compare(String time1,String time2) throws ParseException
	{
		//如果想比较日期则写成"yyyy-MM-dd"就可以了
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//将字符串形式的时间转化为Date类型的时间

		Date a=sdf.parse(time1);
		Date b=sdf.parse(time2);
		//Date类的一个方法，如果a早于b返回true，否则返回false
		if(a.before(b)) {
			return true;
		}
		else {
			return false;
		}
		/*
		 * 如果你不喜欢用上面这个太流氓的方法，也可以根据将Date转换成毫秒*/
//		if(a.getTime()-b.getTime()<0)
//			return true;
//		else
//			return false;

	}
	public static boolean getDatePoor(Date endDate, Date nowDate) {

		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		if(min<10){
			return false;
		}
		return true;
	}
	private void initDatePicker() {
		List<String> years = new ArrayList<String>();
		List<String> months = new ArrayList<String>();

		for (int i = 1900; i <= (Integer.parseInt(getCurrentYear())+extendedYear); i++) {
			years.add(String.valueOf(i));
		}

		for (int i = 1; i <= 12; i++) {
			months.add(i < 10 ? "0" + i : "" + i);
		}

		if (yearPicker != null) {
			yearPicker.setData(years);
		}

		if (monthPicker != null) {
			monthPicker.setData(months);
		}

		if (!TextUtils.isEmpty(date)) {
			String[] dates = date.split("-");

			if (dates == null || dates.length != 3) {
				if (yearPicker != null) {
					yearPicker.setSelected(getCurrentYear());
				}
				if (monthPicker != null) {
					monthPicker.setSelected(getCurrentMonth());
				}

				selectDay = null;
			} else {
				if (yearPicker != null) {
					yearPicker.setSelected(dates[0]);
				}
				if (monthPicker != null) {
					monthPicker.setSelected(dates[1]);
				}

				selectDay = dates[2];
			}
		} else {
			if (yearPicker != null) {
				yearPicker.setSelected(getCurrentYear());
			}
			if (monthPicker != null) {
				monthPicker.setSelected(getCurrentMonth());
			}

			selectDay = null;
		}

		if (dayPicker != null) {
			initDayData();
		}

		setListener();
	}

	private void initDayData() {
		List<String> days = new ArrayList<String>();

		for (int i = 1; i <= TimeUtil.getDayCount(Integer.parseInt(yearPicker.getSelectedStr()),
				Integer.parseInt(monthPicker.getSelectedStr())); i++) {
			days.add(i < 10 ? "0" + i : "" + i);
		}

		dayPicker.setData(days);

		if (TextUtils.isEmpty(selectDay)) {
			selectDay = getCurrentDay();
		}

		if (!days.contains(selectDay)) {
			selectDay = "01";
		}

		dayPicker.setSelected(selectDay);
	}

	private void setListener() {
		if (yearPicker != null) {
			yearPicker.setOnSelectListener(new PickerView.onSelectListener() {

				@Override
				public void onSelect(String text) {
					if (dayPicker != null) {
						initDayData();
					}
				}
			});
		}

		if (monthPicker != null) {
			monthPicker.setOnSelectListener(new PickerView.onSelectListener() {

				@Override
				public void onSelect(String text) {
					if (dayPicker != null) {
						initDayData();
					}
				}
			});
		}

		if (dayPicker != null) {
			dayPicker.setOnSelectListener(new PickerView.onSelectListener() {

				@Override
				public void onSelect(String text) {
					selectDay = text;
				}
			});
		}

	}

	private String getSelectDate() {
		if (dayPicker != null) {
			return yearPicker.getSelectedStr() + "-" + monthPicker.getSelectedStr() + "-" + dayPicker.getSelectedStr();
		} else if (monthPicker != null) {
			return yearPicker.getSelectedStr() + "-" + monthPicker.getSelectedStr();
		} else {
			return yearPicker.getSelectedStr();
		}
	}

	private String getSelectTime() {
		if (secondPicker != null) {
			return hourPicker.getSelectedStr() + ":" + minutePicker.getSelectedStr() + ":"
					+ secondPicker.getSelectedStr();
		} else if (minutePicker != null) {
			return hourPicker.getSelectedStr() + ":" + minutePicker.getSelectedStr();
		} else if (hourPicker != null) {
			return hourPicker.getSelectedStr();
		} else {
			return "";
		}
	}

	private String getSelectDateTime() {
		return getSelectDate() + " " + getSelectTime();
	}

	private String getCurrentYear() {
		return new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis()));
	}

	private String getCurrentMonth() {
		return new SimpleDateFormat("MM").format(new Date(System.currentTimeMillis()));
	}

	private String getCurrentDay() {
		return new SimpleDateFormat("dd").format(new Date(System.currentTimeMillis()));
	}
	private String getCurrenthourPicker() {
		return new SimpleDateFormat("HH").format(new Date(System.currentTimeMillis()));
	}
	private String getCurrentminutePickerPicker() {
		return new SimpleDateFormat("mm").format(new Date(System.currentTimeMillis()));
	}
	private String getCurrentsecondPickerPicker() {
		return new SimpleDateFormat("ss").format(new Date(System.currentTimeMillis()));
	}
	public void showPicker() {
		if (dialog != null) {
			dialog.show();
		}
	}

	public void hidePicker() {
		if (dialog != null) {
			dialog.dismiss();
		}
	}

	public interface OnDateTimePickerPickedListener {
		public void onPicked(int what, String dateTimeStr);

		public void onPicked(int what, long dateTimeLng);

		public void onPicked(String what, long dateTimeLng, String dateTimeStr, View view);
	}
}
