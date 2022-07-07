package com.ttce.vehiclemanage.ui.main.activity.my.order;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;
import com.google.gson.Gson;
import com.haibin.calendarview.CalendarView;
import com.jaydenxiao.common.base.BaseActivity;
import com.rk.datetimepicker.TimeUtil;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.OrderSelectBean;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 已修改 我的订单
 */
public class OrderSelectActivity extends BaseActivity implements CalendarView.OnCalendarSelectListener, CalendarView.OnYearChangeListener {

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.this_day)
    TextView thisDay;
    @Bind(R.id.calendarView)
    CalendarView calendarView;
    @Bind(R.id.tv_last_month)
    TextView tvLastMonth;
    @Bind(R.id.tv_this_month)
    TextView tvThisMonth;
    @Bind(R.id.tv_Last_3_days)
    TextView tvLast3Days;
    @Bind(R.id.tv_Last_7_days)
    TextView tvLast7Days;
    @Bind(R.id.tv_today)
    TextView todayTV;
    @Bind(R.id.tv_last_day)
    TextView lastDayTV;
    @Bind(R.id.tv_this_weeek)
    TextView thisWeekTV;
    @Bind(R.id.tv_last_weeek)
    TextView lastWeekTV;
    @Bind(R.id.tv_start_time)
    TextView startTimeTV;
    @Bind(R.id.tv_end_time)
    TextView endTimeTV;
  @Bind(R.id.edt_cph)
  EditText edt_cph;
  @Bind(R.id.edt_xm_or_phone)
  EditText edt_xm_or_phone;
 @Bind(R.id.edt_ddh)
  EditText edt_ddh;
 @Bind(R.id.lin_carnumber)
  LinearLayout lin_carnumber;
 @Bind(R.id.lin_idNumber)
  LinearLayout lin_idNumber;
 @Bind(R.id.lin_ddh)
  LinearLayout lin_ddh;
 @Bind(R.id.edt_idnumber)
  EditText edt_idnumber;
    @Override
    public int getLayoutId() {
        return R.layout.activity_order_select;
    }

    @Override
    public void initPresenter() {

    }
    String thisyear = "";
    String thismonth = "";
    String thisday = "";
    String selectTypestr;
    OrderSelectBean orderSelectBean;
    public void initView() {
        titleBarTitle.setText("高级筛选");
        selectTypestr = this.getIntent().getStringExtra("type");

        thisyear = calendarView.getCurYear() + "";
        thismonth = calendarView.getCurMonth() + "";
        thisday = calendarView.getCurDay() + "";

        thisDay.setText(calendarView.getCurYear() + "年" + calendarView.getCurMonth() + "月");

        if(selectTypestr.equals("信息审核")){
            lin_carnumber.setVisibility(View.GONE);
            lin_ddh.setVisibility(View.GONE);
            lin_idNumber.setVisibility(View.VISIBLE);


            String orderselect=SPDefaultHelper.getString("shenheselect");
            orderSelectBean=new Gson().fromJson(orderselect, OrderSelectBean.class);
            String starttime="",endtime="";
            if(orderSelectBean!=null){
                edt_idnumber.setText(orderSelectBean.getOrderId());
                edt_xm_or_phone.setText(orderSelectBean.getNameOrPhone());
                if(orderSelectBean.getStartTime().contains(":")){
                    starttime=orderSelectBean.getStartTime().substring(0,orderSelectBean.getStartTime().lastIndexOf(":"));
                }
                if(orderSelectBean.getEndTime().contains(":")){
                    endtime=orderSelectBean.getEndTime().substring(0,orderSelectBean.getEndTime().lastIndexOf(":"));
                }
                startTimeTV.setText(starttime);
                endTimeTV.setText(endtime);
                if(Integer.valueOf(orderSelectBean.getTypeclass())!=-1){
                    initTimePicker(Integer.valueOf(orderSelectBean.getTypeclass()));
                }else{
                    if(starttime.contains(" ")){
                        String year=starttime.split(" ")[0];
                        thisDay.setText(year.split("-")[0] + "年" + year.split("-")[1] + "月");
                        thisyear = year.split("-")[0] + "";
                        thismonth = year.split("-")[1]+ "";
                        thisday =  year.split("-")[2] + "";
                        calendarView.scrollToCalendar(Integer.valueOf(year.split("-")[0]),Integer.valueOf(year.split("-")[1]),Integer.valueOf(year.split("-")[2]),false,false);
                    }
                }
            }else{
                initTimePicker(3);
            }
        }else{
            lin_carnumber.setVisibility(View.VISIBLE);
            lin_ddh.setVisibility(View.VISIBLE);
            lin_idNumber.setVisibility(View.GONE);

            String orderselect=SPDefaultHelper.getString("orderselect");
            orderSelectBean=new Gson().fromJson(orderselect, OrderSelectBean.class);
            String starttime="",endtime="";

            if(orderSelectBean!=null){
                edt_cph.setText(orderSelectBean.getCarNumber());
                edt_xm_or_phone.setText(orderSelectBean.getNameOrPhone());
                edt_ddh.setText(orderSelectBean.getOrderId());
                if(orderSelectBean.getStartTime().contains(":")){
                    starttime=orderSelectBean.getStartTime().substring(0,orderSelectBean.getStartTime().lastIndexOf(":"));
                }
                if(orderSelectBean.getEndTime().contains(":")){
                    endtime=orderSelectBean.getEndTime().substring(0,orderSelectBean.getEndTime().lastIndexOf(":"));
                }
                startTimeTV.setText(starttime);
                endTimeTV.setText(endtime);
                if(Integer.valueOf(orderSelectBean.getTypeclass())!=-1){
                    initTimePicker(Integer.valueOf(orderSelectBean.getTypeclass()));
                }else{
                    if(starttime.contains(" ")){
                        String year=starttime.split(" ")[0];
                        thisDay.setText(year.split("-")[0] + "年" + year.split("-")[1] + "月");
                        thisyear = year.split("-")[0] + "";
                        thismonth = year.split("-")[1]+ "";
                        thisday =  year.split("-")[2] + "";
                        calendarView.scrollToCalendar(Integer.valueOf(year.split("-")[0]),Integer.valueOf(year.split("-")[1]),Integer.valueOf(year.split("-")[2]),false,false);
                    }
                }
            }else{
                initTimePicker(3);
            }
        }


        calendarView.setOnCalendarSelectListener(this);
        calendarView.setOnYearChangeListener(this);
    }

    @OnClick({R.id.img_before_year,R.id.img_before_month,R.id.img_after_year, R.id.img_after_month, R.id.title_bar_back,  R.id.tv_cancel, R.id.tv_confirm,R.id.tv_this_month, R.id.tv_last_month,   R.id.tv_Last_7_days, R.id.tv_Last_3_days,
            R.id.tv_this_weeek, R.id.tv_last_weeek,R.id.tv_today, R.id.tv_last_day,R.id.tv_start_time, R.id.tv_end_time
           })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_before_year:
                initTimePicker(-1);
                thisyear = Integer.valueOf(thisyear) - 1 + "";
                thisDay.setText(Integer.valueOf(thisyear) + "年" + Integer.valueOf(thismonth) + "月");
                calendarView.scrollToCalendar(Integer.valueOf(thisyear), Integer.valueOf(thismonth), Integer.valueOf(thisday), false, false);
                startTimeTV.setText(thisyear + "-" + thismonth + "-" + thisday+" 00:00");
                endTimeTV.setText(thisyear + "-" + thismonth + "-" + thisday+" 23:59");
                break;
            case R.id.img_before_month:
                initTimePicker(-1);
                calendarView.scrollToPre();
                break;
            case R.id.img_after_year:
                initTimePicker(-1);
                thisyear = Integer.valueOf(thisyear) + 1 + "";
                thisDay.setText(Integer.valueOf(thisyear) + "年" + Integer.valueOf(thismonth) + "月");
                calendarView.scrollToCalendar(Integer.valueOf(thisyear), Integer.valueOf(thismonth), Integer.valueOf(thisday), false, false);
                startTimeTV.setText(thisyear + "-" + thismonth + "-" + thisday+" 00:00");
                endTimeTV.setText(thisyear + "-" + thismonth + "-" + thisday+" 23:59");
                break;
            case R.id.img_after_month:
                initTimePicker(-1);
                calendarView.scrollToNext();
                break;
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_cancel:
                selectindex=3;
                startTimeTV.setText("");
                endTimeTV.setText("");
                edt_cph.setText("");
                edt_xm_or_phone.setText("");
                edt_ddh.setText("");
                edt_idnumber.setText("");
                break;
            case R.id.tv_confirm:
                OrderSelectBean orderSelectBean;
                String starttime="",endtime="";
                if(startTimeTV.getText().toString().trim().equals("")){
                    starttime="";
                }else{
                    starttime=startTimeTV.getText().toString().trim()+":00";
                }

                if(endTimeTV.getText().toString().trim().equals("")){
                    endtime="";
                }else{
                    endtime=endTimeTV.getText().toString().trim()+":59";
                }
                if(selectTypestr.equals("信息审核")){
                    orderSelectBean= new OrderSelectBean(starttime,endtime,"",edt_xm_or_phone.getText().toString().trim(),edt_idnumber.getText().toString().trim(),selectTypestr,String.valueOf(selectindex));
                    EventBus.getDefault().postSticky(orderSelectBean);
                    SPDefaultHelper.saveString("shenheselect", new Gson().toJson(orderSelectBean));
                }else{
                    orderSelectBean= new OrderSelectBean(starttime,endtime,edt_cph.getText().toString().trim(),edt_xm_or_phone.getText().toString().trim(),edt_ddh.getText().toString().trim(),selectTypestr,String.valueOf(selectindex));
                    EventBus.getDefault().postSticky(orderSelectBean);
                    SPDefaultHelper.saveString("orderselect", new Gson().toJson(orderSelectBean));
                }
                finish();
                break;
            case R.id.tv_last_month:
                initTimePicker(4);
                break;
            case R.id.tv_this_month:
                initTimePicker(5);
                break;
            case R.id.tv_Last_3_days:
                initTimePicker(6);
                break;
            case R.id.tv_Last_7_days:
                initTimePicker(7);
                break;
            case R.id.tv_last_weeek:
                initTimePicker(0);
                break;
            case R.id.tv_this_weeek:
                initTimePicker(1);
                break;
            case R.id.tv_last_day:
                initTimePicker(2);
                break;
            case R.id.tv_today:
                initTimePicker(3);
                break;
            case R.id.tv_start_time:
                setTimeSelect("开始时间", startTimeTV);
                break;
            case R.id.tv_end_time:
                setTimeSelect("结束时间", endTimeTV);
                break;
        }
    }
    TimePickerView pvTime = null;
    private void setTimeSelect(String title, TextView textview) {
        //时间选择器
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                textview.setText(format.format(date));
            }
        })
                .setLayoutRes(R.layout.dialog_time_select, new CustomListener() {
                    @Override
                    public void customLayout(View v) {

                        LinearLayout lin = (LinearLayout) v.findViewById(R.id.dialog_date_lin);
                        TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        TextView tvSure = (TextView) v.findViewById(R.id.tv_sure);
                        tvTitle.setText(title);
                        tvSure.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                pvTime.returnData();
                                pvTime.dismiss();
                            }
                        });
                        //取控件当前的布局参数
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lin.getLayoutParams();
                        int width = getResources().getDisplayMetrics().widthPixels;
                        int height = getResources().getDisplayMetrics().heightPixels;
                        params.setMargins(20, 0, 20, 0);
                        //设置宽度值
                        params.width = width;
                        //设置高度值
                        params.height = height / 3;
                        //使设置好的布局参数应用到控件
                        lin.setLayoutParams(params);
                    }
                })
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                .setDividerColor(Color.GRAY)
                .setDividerType(WheelView.DividerType.WRAP)
                .setTextXOffset(0, 0, 0, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
                .isDialog(true)//是否显示为对话框样式
                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
                .setItemVisibleCount(3)
                .setContentTextSize(18)
                .build();
        pvTime.show();
    }
    public int selectindex = 0;//筛选条件的角标
    private void initTimePicker(int index) {
        selectindex=index;
        calendarView.clearSingleSelect();
        todayTV.setTextColor(getResources().getColor(R.color.wheel_item_text_color_no_select));
        todayTV.setBackgroundResource(0);
        lastDayTV.setTextColor(getResources().getColor(R.color.wheel_item_text_color_no_select));
        lastDayTV.setBackgroundResource(0);
        thisWeekTV.setTextColor(getResources().getColor(R.color.wheel_item_text_color_no_select));
        thisWeekTV.setBackgroundResource(0);
        lastWeekTV.setTextColor(getResources().getColor(R.color.wheel_item_text_color_no_select));
        lastWeekTV.setBackgroundResource(0);
        tvLastMonth.setTextColor(getResources().getColor(R.color.wheel_item_text_color_no_select));
        tvLastMonth.setBackgroundResource(0);
        tvLast3Days.setTextColor(getResources().getColor(R.color.wheel_item_text_color_no_select));
        tvLast3Days.setBackgroundResource(0);
        tvThisMonth.setTextColor(getResources().getColor(R.color.wheel_item_text_color_no_select));
        tvThisMonth.setBackgroundResource(0);
        tvLast7Days.setTextColor(getResources().getColor(R.color.wheel_item_text_color_no_select));
        tvLast7Days.setBackgroundResource(0);
        Calendar calendar = Calendar.getInstance();
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);//1-7 周日-周六
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        switch (index) {
            case 3:
                startTimeTV.setText(TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd 00:00"));
                endTimeTV.setText(TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                todayTV.setTextColor(getResources().getColor(R.color.white));
                todayTV.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 2:
                calendar.add(Calendar.DATE, -1);
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 00:00"));
                endTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 23:59"));
                lastDayTV.setTextColor(getResources().getColor(R.color.white));
                lastDayTV.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 1://本周
                if (weekday == 1) {
                    calendar.add(Calendar.DATE, weekday - 7);
                } else {
                    calendar.add(Calendar.DATE, 2 - weekday);
                }
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 00:00"));
                calendar.add(Calendar.DATE, 6);
                endTimeTV.setText(TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                thisWeekTV.setTextColor(getResources().getColor(R.color.white));
                thisWeekTV.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 0://上周
                if (weekday == 1) {
                    calendar.add(Calendar.DATE, weekday - 2 * 7);
                } else {
                    calendar.add(Calendar.DATE, -(weekday + 5));
                }
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 00:00"));
                calendar.add(Calendar.DATE, 6);
                endTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 23:59"));
                lastWeekTV.setTextColor(getResources().getColor(R.color.white));
                lastWeekTV.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 4://上月
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-01 00:00"));
                endTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 23:59"));
                tvLastMonth.setTextColor(getResources().getColor(R.color.white));
                tvLastMonth.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 5://本月
                calendar.add(Calendar.MONTH, 0);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-01 00:00"));

                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                endTimeTV.setText(TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                tvThisMonth.setTextColor(getResources().getColor(R.color.white));
                tvThisMonth.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 6://最近3天
                calendar.add(Calendar.DATE, -2);
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 00:00"));
                endTimeTV.setText(TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                tvLast3Days.setTextColor(getResources().getColor(R.color.white));
                tvLast3Days.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 7://最近7天
                calendar.add(Calendar.DATE, -6);
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 00:00"));
                endTimeTV.setText(TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                tvLast7Days.setTextColor(getResources().getColor(R.color.white));
                tvLast7Days.setBackgroundResource(R.drawable.blue_22_bg);
                break;
        }
        try {
            startTimeTV.setTag(simpleDateFormat.parse(startTimeTV.getText().toString()).getTime());
            endTimeTV.setTag(simpleDateFormat.parse(endTimeTV.getText().toString()).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goToPage(Activity activity, String type) {
        Intent intent = new Intent(activity, OrderSelectActivity.class);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    @Override
    public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {
    }

    @Override
    public void onCalendarSelect(com.haibin.calendarview.Calendar calendar, boolean isClick) {
        initTimePicker(-1);
        calendarView.scrollToCalendar(calendar.getYear(), calendar.getMonth(), calendar.getDay() , false, false);
        thisyear = calendar.getYear() + "";
        thismonth = calendar.getMonth() + "";
        thisday = calendar.getDay() + "";
        thisDay.setText(calendar.getYear() + "年" + calendar.getMonth() + "月");
        startTimeTV.setText(calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay()+" 00:00");
        endTimeTV.setText(calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay()+" 23:59");
    }

    @Override
    public void onYearChange(int year) {
        initTimePicker(-1);
        thisyear = year + "";
        thisDay.setText(thisyear + "年" + thismonth + "月");
    }
}