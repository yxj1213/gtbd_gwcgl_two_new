package com.ttce.vehiclemanage.ui.locus.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;
import com.google.gson.Gson;
import com.haibin.calendarview.CalendarView;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.rk.datetimepicker.DateTimePickerDialog;
import com.rk.datetimepicker.TimeUtil;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.MessageEvent;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.bean.OrderSelectBean;
import com.ttce.vehiclemanage.bean.TravelItemBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.ui.locus.adapter.DoubleSpeedAdapter;
import com.ttce.vehiclemanage.ui.locus.contract.LocusContract;
import com.ttce.vehiclemanage.ui.locus.model.LocusModel;
import com.ttce.vehiclemanage.ui.locus.presenter.LocusPresenter;
import com.ttce.vehiclemanage.utils.ArithUtil;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.BitmapUtil;
import com.ttce.vehiclemanage.utils.CarStateFactory;
import com.ttce.vehiclemanage.utils.MapUtil;
import com.ttce.vehiclemanage.utils.NumberUtils;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.GjDialog;
import com.ttce.vehiclemanage.widget.MyLayout;
import com.ttce.vehiclemanage.widget.SeekBar.RangeSeekBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;


public class NewLocusActivity extends BaseActivity<LocusPresenter, LocusModel> implements LocusContract.View,CalendarView.OnCalendarSelectListener, CalendarView.OnYearChangeListener {
    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.iv_right)
    ImageView ivRight;
    @Bind(R.id.mapview)
    TextureMapView mMapView;

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
    @Bind(R.id.tv_device_number)
    TextView deviceNumberTV;
    @Bind(R.id.tv_cancel)
    TextView cancelTV;
    @Bind(R.id.tv_confirm)
    TextView confirmTV;
    @Bind(R.id.my_layout)
    MyLayout myLayout;

    @Bind(R.id.togglePanelIV)
    ImageView togglePanelIV;
    @Bind(R.id.playIV)
    ImageView playIV;
    @Bind(R.id.speedCtrlIV)
    ImageView speedCtrlIV;
    @Bind(R.id.detailDeviceNOTV)
    TextView detailDeviceNOTV;
    @Bind(R.id.toggleLocusTV)
    TextView toggleLocusTV;
    @Bind(R.id.editTV)
    TextView editTV;
    @Bind(R.id.totalMileageTV)
    TextView totalMileageTV;
    @Bind(R.id.speedTV)
    TextView speedTV;
    @Bind(R.id.detailStartTimeTV)
    TextView detailStartTimeTV;
    @Bind(R.id.detailEndTimeTV)
    TextView detailEndTimeTV;
    @Bind(R.id.bottomAdsLL)
    LinearLayout bottomAdsLL;
    @Bind(R.id.startAdsTV)
    TextView startAdsTV;
    @Bind(R.id.startTimeAdsTV)
    TextView startTimeAdsTV;
    @Bind(R.id.endAdsTV)
    TextView endAdsTV;
    @Bind(R.id.endTimeAdsTV)
    TextView endTimeAdsTV;
    @Bind(R.id.my_detail_layout)
    MyLayout myDetailLayout;
    @Bind(R.id.tv_yl)
    TextView tvYl;
    @Bind(R.id.tv_wd)
    TextView tvWd;
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
    @Bind(R.id.tv_beishu)
    TextView tv_beishu;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.lin_bottom)
    LinearLayout linbottom;

    @Bind(R.id.sb_single5)
    RangeSeekBar sbSingle5;
    @Bind(R.id.tv_gps_time)
    TextView tv_gps_time;


    MonitorResponseBean currentData;
    private String deviceId,CarNumber,deviceStatus;
    private BaiduMap mBaiduMap;
    private Marker carMarker;//???????????????????????????
    public int selectindex = 0;//?????????????????????
    String thisyear = "";
    String thismonth = "";
    String thisday = "";
    TimePickerView pvTime = null;
    MHandler mHandler;
    @Override
    public int getLayoutId() {
        return R.layout.activity_locus;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);
        mHandler = new MHandler();
        String JsonData = getIntent().getStringExtra("currentData");
        currentData = new Gson().fromJson(JsonData, MonitorResponseBean.class);
        deviceId=currentData.getDeviceId();
        CarNumber=currentData.getCarNumber();
        deviceStatus=currentData.getCarDisplayColorFormat();
        if (TextUtils.isEmpty(deviceId) || TextUtils.isEmpty(currentData.getDeviceNumber())) {
            finish();
            return;
        }
        titleBarTitle.setText("??????");
        ivRight.setVisibility(View.GONE);
        ivRight.setImageResource(R.mipmap.icon_guiji_topright);

        mMapView.showZoomControls(true);
        mBaiduMap = mMapView.getMap();
        mMapView.getChildAt(2).setPadding(0, 0, 10, DisplayUtil.getScreenHeight(this) / 4 * 2);//?????????????????????????????????


        thisyear = calendarView.getCurYear() + "";
        thismonth = calendarView.getCurMonth() + "";
        thisday = calendarView.getCurDay() + "";
        thisDay.setText(calendarView.getCurYear() + "???" + calendarView.getCurMonth() + "???");

        initData(false);

//        setCalendarData();

        calendarView.setOnCalendarSelectListener(this);
        calendarView.setOnYearChangeListener(this);
    }

    private void initData(boolean isClear) {
//        int selecttvtags = SPDefaultHelper.getInt("selectindex");
//        initTimePicker(selecttvtags);

        if(isClear==false){
            String orderselect=SPDefaultHelper.getString("guijiselect");
            OrderSelectBean orderSelectBean=new Gson().fromJson(orderselect, OrderSelectBean.class);
            String starttime="",endtime="";
            if(orderSelectBean!=null){//????????????????????????????????????????????????????????????????????????????????????????????????????????????
                if(orderSelectBean.getCarNumber().equals(CarNumber)){
                    deviceNumberTV.setText(orderSelectBean.getCarNumber());
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
                            thisDay.setText(year.split("-")[0] + "???" + year.split("-")[1] + "???");
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
                initTimePicker(3);
            }
        }else{
            initTimePicker(3);
        }

        initCarPosition();
//        setDoubleSpeedData();
    }

    //?????????????????????????????????
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
        int weekday = calendar.get(Calendar.DAY_OF_WEEK);//1-7 ??????-??????
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
            case 1://??????
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
            case 0://??????
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
            case 4://??????
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-01 00:00"));
                endTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 23:59"));
                tvLastMonth.setTextColor(getResources().getColor(R.color.white));
                tvLastMonth.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 5://??????
                calendar.add(Calendar.MONTH, 0);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-01 00:00"));

                calendar.add(Calendar.MONTH, 1);
                calendar.set(Calendar.DAY_OF_MONTH, 0);
                endTimeTV.setText(TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                tvThisMonth.setTextColor(getResources().getColor(R.color.white));
                tvThisMonth.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 6://??????3???
                calendar.add(Calendar.DATE, -2);
                startTimeTV.setText(TimeUtil.format(calendar.getTimeInMillis(), "yyyy-MM-dd 00:00"));
                endTimeTV.setText(TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
                tvLast3Days.setTextColor(getResources().getColor(R.color.white));
                tvLast3Days.setBackgroundResource(R.drawable.blue_22_bg);
                break;
            case 7://??????7???
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
        deviceNumberTV.setText(CarNumber);
    }

    //??????????????????
    private void initCarPosition() {
        LatLng carPosition = BDMapUtils.convert(new LatLng(currentData.getLat(), currentData.getLng()));
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(CarStateFactory.getCarColorByStatus(deviceStatus));
        OverlayOptions markerOptions = new MarkerOptions().flat(true).anchor(0.5f, 0.5f).icon(bitmap).position(carPosition).zIndex(9);
        carMarker = (Marker) mBaiduMap.addOverlay(markerOptions);

        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(carPosition);
        if (mBaiduMap != null && mapStatusUpdate != null) {
            mBaiduMap.animateMapStatus(mapStatusUpdate);
        }
    }

    //?????????????????????????????????
    protected void setDoubleSpeedData() {
        List<String> list = new ArrayList<>();
        list.add("x1");
        list.add("x2");
        list.add("x4");
        list.add("x8");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DoubleSpeedAdapter itemAdapter = new DoubleSpeedAdapter(NewLocusActivity.this, list);
        recyclerView.setAdapter(itemAdapter);
    }
    //????????????????????????
    protected void setCalendarData() {
        thisyear = calendarView.getCurYear() + "";
        thismonth = calendarView.getCurMonth() + "";
        thisday = calendarView.getCurDay() + "";
        thisDay.setText(calendarView.getCurYear() + "???" + calendarView.getCurMonth() + "???");
        calendarView.setOnCalendarSelectListener(this);
        calendarView.setOnYearChangeListener(this);

        int year = calendarView.getCurYear();
        int month = calendarView.getCurMonth();

        Map<String, com.haibin.calendarview.Calendar> map = new HashMap<>();
        map.put(getSchemeCalendar(year, month, 3, "???").toString(),
                getSchemeCalendar(year, month, 3, "???"));
        map.put(getSchemeCalendar(year, month, 6, "???").toString(),
                getSchemeCalendar(year, month, 6, "???"));
        //?????????????????????????????????????????????????????????????????????
        calendarView.setSchemeDate(map);
    }
    private com.haibin.calendarview.Calendar getSchemeCalendar(int year, int month, int day, String text) {
        int colors = 0xff87CEFF;
        com.haibin.calendarview.Calendar calendar = new com.haibin.calendarview.Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(colors);//???????????????????????????????????????????????????
        calendar.setScheme(text);
        return calendar;
    }

    boolean isZheng=true;
    @OnClick({R.id.tv_beishu,R.id.tv_Last_7_days, R.id.tv_Last_3_days, R.id.tv_this_month, R.id.tv_last_month, R.id.img_before_year, R.id.img_before_month, R.id.img_after_year, R.id.img_after_month, R.id.title_bar_back, R.id.iv_right, R.id.tv_today, R.id.tv_last_day, R.id.tv_this_weeek, R.id.tv_last_weeek, R.id.tv_start_time, R.id.tv_end_time, R.id.tv_device_number,
            R.id.tv_cancel, R.id.tv_confirm, R.id.togglePanelIV, R.id.editTV, R.id.playIV, R.id.speedCtrlIV, R.id.toggleLocusTV, R.id.tv_replace})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_beishu:
                int beishu;
                if(isZheng==true){
                     beishu=Integer.valueOf(tv_beishu.getText().toString().substring(1,2))*2;
                    NewLocusActivity.DISTANCE1=NewLocusActivity.DISTANCE1*10;
                }else{
                    beishu=Integer.valueOf(tv_beishu.getText().toString().substring(1,2))/2;
                    NewLocusActivity.DISTANCE1=NewLocusActivity.DISTANCE1/10;
                }
                tv_beishu.setText("x"+beishu);
                if(beishu==8){
                    isZheng=false;
                }
                if(beishu==1){
                    isZheng=true;
                }
                Log.d("111111111111",String.format("%.9f", NewLocusActivity.DISTANCE1)+"----");
                break;
            case R.id.img_before_year:
                initTimePicker(-1);
//                selectindex=-1;
                thisyear = Integer.valueOf(thisyear) - 1 + "";
                thisDay.setText(Integer.valueOf(thisyear) + "???" + Integer.valueOf(thismonth) + "???");
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
//                selectindex=-1;
                thisyear = Integer.valueOf(thisyear) + 1 + "";
                thisDay.setText(Integer.valueOf(thisyear) + "???" + Integer.valueOf(thismonth) + "???");
                calendarView.scrollToCalendar(Integer.valueOf(thisyear), Integer.valueOf(thismonth), Integer.valueOf(thisday), false, false);
                startTimeTV.setText(thisyear + "-" + thismonth + "-" + thisday+" 00:00");
                endTimeTV.setText(thisyear + "-" + thismonth + "-" + thisday+" 23:59");
                break;
            case R.id.img_after_month:
                initTimePicker(-1);
                calendarView.scrollToNext();
                break;
            case R.id.title_bar_back:
                if (task != null) {
                    task.cancel(true);
                    task = null;
                    // return;
                }
                finish();
                break;
            case R.id.iv_right:
                setGuiJi();
                break;
            case R.id.tv_cancel:
                if (editTV.getTag() == null) {
                    initTimePicker(3);
                } else {
                    myLayout.setVisibility(View.GONE);
                    ivRight.setVisibility(View.GONE);
                    myDetailLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_confirm:
                if(startTimeTV.getText().toString().equals("")||endTimeTV.getText().toString().equals("")){
                    ToastUtil.showToast("????????????????????????????????????");
                    return;
                }
                threadTest = null;
//                SPDefaultHelper.saveInt("selectindex", selectindex);
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
                orderSelectBean= new OrderSelectBean(starttime,endtime,deviceNumberTV.getText().toString().trim(),String.valueOf(selectindex));
                EventBus.getDefault().postSticky(orderSelectBean);
                SPDefaultHelper.saveString("guijiselect", new Gson().toJson(orderSelectBean));
                startProgressDialog();
                queryTravel();

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
                setTimeSelect("????????????", startTimeTV);
                break;
            case R.id.tv_end_time:
                setTimeSelect("????????????", endTimeTV);
                break;
            case R.id.tv_device_number:
                startActivityForResult(DeptActivity.class, AppConstant.REQUEST_SELECT_DEVICE);
                break;
            case R.id.togglePanelIV:
                String flag = (String) togglePanelIV.getTag();
                if (flag.equals("0")) {
                    bottomAdsLL.setVisibility(View.VISIBLE);
                    togglePanelIV.setTag("1");
                    togglePanelIV.setImageResource(R.mipmap.icon_down);
                } else {
                    bottomAdsLL.setVisibility(View.GONE);
                    togglePanelIV.setTag("0");
                    togglePanelIV.setImageResource(R.mipmap.icon_up);
                }
                break;
            case R.id.editTV:
                editTV.setTag(1);
                myDetailLayout.setVisibility(View.GONE);
                ivRight.setVisibility(View.GONE);
                myLayout.setVisibility(View.VISIBLE);
                initData(false);
                mBaiduMap.clear();
                isFirst = true;
                if (threadTest != null) {
                    isonDestroy = false;
                    threadTest.pauseThread();
                }
                playIV.setTag(0);
                playIV.setImageResource(R.mipmap.ico_play);

                if (task != null) {
                    task.cancel(true);
                    task = null;
                }
                break;
            case R.id.playIV:
                if (playIV.getTag() == null || (Integer) playIV.getTag() == 0) {
                    playIV.setTag(1);
                    playIV.setImageResource(R.mipmap.ico_pause);
                    if (isFirst == true) {
                        isZheng=true;
                        tv_beishu.setText("x1");
                        NewLocusActivity.DISTANCE1=0.00005;
                        clearLine();
                        moveLooper();
                    } else {
                        threadTest.resumeThread();
                    }
                } else {
                    threadTest.pauseThread();
                    playIV.setTag(0);
                    playIV.setImageResource(R.mipmap.ico_play);
                }
                break;
            case R.id.toggleLocusTV:
                if (toggleLocusTV.getTag() == null || (Integer) toggleLocusTV.getTag() == 1) {
                    polylineOverlay.setVisible(false);
                    toggleLocusTV.setText("????????????");
                    toggleLocusTV.setTag(0);
                } else {
                    polylineOverlay.setVisible(true);
                    toggleLocusTV.setTag(1);
                    toggleLocusTV.setText("????????????");
                }
                break;
            case R.id.tv_replace:
                if (isNormal) {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                    isNormal = false;
                } else {
                    mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                    isNormal = true;
                }
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case AppConstant.REQUEST_SELECT_DEVICE:
                if (resultCode == RESULT_OK && data != null) {
                    deviceId = data.getStringExtra("DeviceId");
                    CarNumber = data.getStringExtra("CarNumber");
                    deviceStatus = data.getStringExtra("DeviceStatus");
                    deviceNumberTV.setText(CarNumber);
                    initData(true);
                }
                break;
        }
    }

    boolean isFirst = true;
    // ???????????????????????????????????????????????????????????????????????????
    public int TIME_INTERVAL1 = 30;
    public static double DISTANCE1 = 0.00005;
    private List<LatLng> mlist = new ArrayList<>();
    boolean isonDestroy = false;
    ThreadTest threadTest;
    public void clearLine() {
        for (Overlay ov:linelist) {
            ov.remove();
        }
        linelist.removeAll(linelist);
    }
    public void moveLooper() {
        if (carMarker != null) {
            carMarker.remove();
            sbSingle5.setProgress(0f);
            changeAfterSeekBarProgress=0;
            isReft=false;
        }
        LatLng firstPoint = new LatLng(gjPointList.get(0).getLat(), gjPointList.get(0).getLng());
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(CarStateFactory.getCarColorByStatus(deviceStatus));
        OverlayOptions markerOptions = new MarkerOptions()
                .flat(true)
                .anchor(0.5f, 0.5f)
                .icon(bitmap)
                .position(firstPoint)
                .zIndex(9)
                .rotate((float) getAngle(0));
        // ??????????????????????????????
        carMarker = (Marker) mBaiduMap.addOverlay(markerOptions);
        // ????????????
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(firstPoint);
        if (mBaiduMap != null && mapStatusUpdate != null) {
            mBaiduMap.animateMapStatus(mapStatusUpdate);
        }

        linelist=new ArrayList<>();
        isFirst = false;
        threadTest = new ThreadTest();
        threadTest.start();
    }
    List<Overlay> linelist=new ArrayList();
    public static int pos=0;
    class ThreadTest extends Thread {
        Object lock = new Object();// ?????????????????????

        //????????????????????????
        public boolean pause = false;
        /**
         * ????????????????????????
         */
        public void pauseThread() {
            this.pause = true;
        }

        /**
         * ??????????????????????????????????????????
         */
        public void resumeThread() {
            this.pause = false;
            synchronized (lock) {
                //????????????
                lock.notify();
            }
        }

        /**
         * ?????????????????????run ??????????????????????????????????????????????????????????????????
         */
        void onPause() {
            synchronized (lock) {
                try {
                    //?????? ??????/??????
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void run() {
            super.run();

            try {

                for (int i = 0; i < gjPointList.size()-1; i++) {
                    if (pause) {
                        //?????? ??????/??????
                        onPause();
                    }
                    //????????????????????????
                    if (this.isInterrupted()) {
                        throw new InterruptedException();
                    }
                    if (isonDestroy == false) {
                        if(isReft){
                            if (pos > changeAfterSeekBarProgress) {//???????????????????????????seekbar
                                for (int i1=pos;i1>=changeAfterSeekBarProgress;i1--) {
                                    if(linelist.size()>0){
                                        linelist.get(i1).remove();
                                        linelist.remove(i1);
                                    }
                                }

                            }else{//???????????????????????????seekbar
                                for (int j =  pos+1; j < changeAfterSeekBarProgress; j++) {
                                    if(j>gjPointList.size()-2){//????????????????????????????????????
                                        carMarker.setPosition(new LatLng(gjPointList.get(j).getLat(), gjPointList.get(j).getLng()));
                                        carMarker.setRotate((float)getAngle(j));
                                        Message ms = new Message();
                                        ms.what = 1;
                                        ms.arg1 = 2;
                                        ms.obj = gjPointList.get(j);
                                        ms.arg2 = j;
                                        mHandler.sendMessage(ms);
                                        break;
                                    }
                                    LatLng startPoint = new LatLng(gjPointList.get(j).getLat(), gjPointList.get(j).getLng());
                                    LatLng endPoint = new LatLng(gjPointList.get(j + 1).getLat(), gjPointList.get(j + 1).getLng());
                                    mlist = new ArrayList<>();
                                    mlist.add(startPoint);
                                    mlist.add(endPoint);
                                    // ????????????PolyLine
                                    OverlayOptions polylineOptions = new PolylineOptions().points(mlist).width(8).dottedLine(false).zIndex(9)
                                            .color(getResources().getColor(R.color.black));
                                    Overlay overlay=mBaiduMap.addOverlay(polylineOptions);
                                    linelist.add(overlay);
                                }
                            }
                            i=changeAfterSeekBarProgress;
                            isReft=false;
                        }
                        pos=i;
                        if(i<=gjPointList.size()-2){
                            LatLng startPoint = new LatLng(gjPointList.get(i).getLat(), gjPointList.get(i).getLng());
                            LatLng endPoint = new LatLng(gjPointList.get(i + 1).getLat(), gjPointList.get(i + 1).getLng());
                            carMarker.setPosition(endPoint);
                            mlist = new ArrayList<>();
                            mlist.add(startPoint);
                            mlist.add(endPoint);
                            // ????????????PolyLine
                            OverlayOptions polylineOptions = new PolylineOptions().points(mlist).width(8).dottedLine(false).zIndex(9)
                                    .color(getResources().getColor(R.color.black));
                            Overlay overlay=mBaiduMap.addOverlay(polylineOptions);
                            linelist.add(overlay);
                            Message ms = new Message();
                            ms.what = 1;
                            if (i >= (gjPointList.size() - 2)) {
                                ms.arg1 = 2;
                            }
                            ms.obj = gjPointList.get(i);
                            ms.arg2 = i;
                            mHandler.sendMessage(ms);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    // refresh marker's rotate
                                    if (mMapView == null) {
                                        return;
                                    }
                                    carMarker.setRotate((float) getAngle(startPoint, endPoint));
                                }
                            });
                            double slope = getSlope(startPoint, endPoint);
                            // ????????????????????????
                            boolean isYReverse = (startPoint.latitude > endPoint.latitude);
                            boolean isXReverse = (startPoint.longitude > endPoint.longitude);
                            double intercept = getInterception(slope, startPoint);
                            double xMoveDistance =
                                    isXReverse ? getXMoveDistance(slope) : -1 * getXMoveDistance(slope);
                            double yMoveDistance =
                                    isYReverse ? getYMoveDistance(slope) : -1 * getYMoveDistance(slope);

                            for (double j = startPoint.latitude, k = startPoint.longitude;
                                 !((j > endPoint.latitude)
                                         ^ isYReverse)
                                         && !((k > endPoint.longitude)
                                         ^ isXReverse); ) {
                                LatLng latLng = null;

                                if (slope == Double.MAX_VALUE) {
                                    latLng = new LatLng(j, k);
                                    j = j - yMoveDistance;
                                } else if (slope == 0.0) {
                                    latLng = new LatLng(j, k - xMoveDistance);
                                    k = k - xMoveDistance;
                                } else {
                                    latLng = new LatLng(j, (j - intercept) / slope);
                                    j = j - yMoveDistance;
                                }

                                final LatLng finalLatLng = latLng;
                                if (finalLatLng.latitude == 0 && finalLatLng.longitude == 0) {
                                    continue;
                                }
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (mMapView == null) {
                                            return;
                                        }
                                        carMarker.setPosition(finalLatLng);

                                        // ?????? Marker ????????????????????????,??????????????????Marker?????????InfoWindow???????????????.
                                        carMarker.setPositionWithInfoWindow(finalLatLng);

                                        // ????????????
                                        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(finalLatLng);
                                        if (mBaiduMap != null && mapStatusUpdate != null) {
                                            mBaiduMap.animateMapStatus(mapStatusUpdate);
                                        }
                                    }
                                });
                                try {
                                    Thread.sleep(TIME_INTERVAL1);
                                } catch (InterruptedException e) {
                                    //e.printStackTrace();
                                    Thread.currentThread().interrupt();//?????????????????????
                                    return;
                                }
                            }
                        }
                    }
                }
            } catch (InterruptedException e) {

                Thread.currentThread().interrupt();//?????????????????????
                return;
            }

        }

    }

    class MHandler extends Handler {
        // ?????????????????????
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TravelItemBean travelItemBean;
            switch (msg.what) {
                case 1:
                    travelItemBean = (TravelItemBean) msg.obj;
                    if (speedTV != null && tvYl != null && tvWd != null && travelItemBean != null) {
                        speedTV.setText(NumberUtils.round(travelItemBean.getSpeed(), 2) + "km/h");
                        tvYl.setText("?????????" + travelItemBean.getOilQuantity());
                        tvWd.setText("?????????" + travelItemBean.getTemperatureFormat());
                        sbSingle5.setProgress(msg.arg2);
                        tv_gps_time.setText(travelItemBean.getGpsTimeFormat());
                        sbSingle5.setIndicatorTextStringFormat("");
                        if (msg.arg1 == 2) {
                            playIV.setTag(0);
                            playIV.setImageResource(R.mipmap.ico_play);
                            sbSingle5.setProgress(0);
                            isFirst = true;
                        }
                    }
                    break;
            }
        }

    }

    NewLocusActivity.GetCSDNLogoTask task;
    NewLocusActivity.GetCSDNLogoTask2 task2;
    List<Marker> mgjlist = new ArrayList<>();//??????????????????????????????????????????
    private void setGuiJi() {
        if (gjPointList.size() == 0) {
            return;
        }
        if(isShow){
            isShow = false;
            ivRight.setImageResource(R.mipmap.icon_guiji_topright_details);

            if (task == null) {
                task = new NewLocusActivity.GetCSDNLogoTask();
                task.execute(gjPointList);
            }
            if (task2 != null) {
                task2.cancel(true);
                task2 = null;
                // return;
            }

            if(mBaiduMap!=null){
                mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
                    //marker???????????????????????????
                    //??????????????????????????????true???????????????false
                    //????????????false
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        Bundle bundle = marker.getExtraInfo();
                        if (bundle == null) {
                            return false;
                        }
                        TravelItemBean infoUtil = (TravelItemBean) bundle.getSerializable("info");
                        GjDialog gjDialog = new GjDialog((Context) NewLocusActivity.this, infoUtil);
                        gjDialog.show();
                        return true;
                    }
                });
            }
        }else{
            isShow = true;
            ivRight.setImageResource(R.mipmap.icon_guiji_topright);
            if (task != null) {
                task.cancel(true);
                task = null;
               // return;
            }

            if (task2 == null) {
                task2 = new NewLocusActivity.GetCSDNLogoTask2();
                task2.execute(mgjlist);
            }
           /* if (mgjlist != null&&mgjlist.size()>=1) {
                for (Marker i : mgjlist) {
                    if(null!=i){
                        i.remove();
                    }
                }
            }*/
        }
    }

    class GetCSDNLogoTask extends AsyncTask<List<TravelItemBean>, Integer, TravelItemBean> {//??????AsyncTask
        TravelItemBean i;

        @Override
        protected TravelItemBean doInBackground(List<TravelItemBean>... params) {//???????????????????????????????????????????????????
            for (Iterator<TravelItemBean> it = gjPointList.iterator(); it.hasNext(); ) {
                i = it.next();
                if (isCancelled()) {
                    return null;
                }
                OverlayOptions parkingOptions = new MarkerOptions()
                        .position(new LatLng(i.getLat(), i.getLng())).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_address))
                        .zIndex(9).draggable(true).visible(true);
                //??????marker
                Marker marker = (Marker) mBaiduMap.addOverlay(parkingOptions);
                //??????marker??????info?????????????????????????????????????????????marker??????info??????
                Bundle bundle = new Bundle();
                //info???????????????????????????
                bundle.putSerializable("info", i);
                marker.setExtraInfo(bundle);
                mgjlist.add(marker);

            }
            return i;
        }

        protected void onProgressUpdate(Integer... progress) {//?????????publishProgress?????????????????????ui????????????
            super.onProgressUpdate(progress);
        }

        protected void onPostExecute(TravelItemBean result) {//??????????????????????????????????????????ui????????????
            super.onPostExecute(result);
            if (result != null) {
                Log.d("LocusActivity", "???????????????");
            } else {
                Log.d("LocusActivity", "???????????????");
            }
        }

        protected void onPreExecute() {//??? doInBackground(Params...)?????????????????????ui????????????
        }

        protected void onCancelled() {//???ui????????????
        }

    }

    class GetCSDNLogoTask2 extends AsyncTask<List<Marker>, Integer, Marker> {//??????AsyncTask
        Marker i;

        @Override
        protected Marker doInBackground(List<Marker>... params) {//???????????????????????????????????????????????????
            for (Iterator<Marker> it = mgjlist.iterator(); it.hasNext(); ) {
                i = it.next();
                if (isCancelled()) {
                    return null;
                }
                i.setVisible(false);
            }
            return i;
        }

        protected void onProgressUpdate(Integer... progress) {//?????????publishProgress?????????????????????ui????????????
            super.onProgressUpdate(progress);
        }

        protected void onPostExecute(Marker result) {//??????????????????????????????????????????ui????????????
            super.onPostExecute(result);
            if (result != null) {
                Log.d("LocusActivity", "???????????????");
            } else {
                Log.d("LocusActivity", "???????????????");
            }
        }

        protected void onPreExecute() {//??? doInBackground(Params...)?????????????????????ui????????????
        }

        protected void onCancelled() {//???ui????????????
        }

    }

    private void setTimeSelect(String title, TextView textview) {
        //???????????????
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str=format.format(date);
                if(title.equals("????????????")){
                    boolean isreturn= false;
                    try {
                        String ksstr=startTimeTV.getText().toString();
                        if(ksstr.length()==19){
                            isreturn = DateTimePickerDialog.compare(str,ksstr);
                        }else if(ksstr.length()==16){
                            ksstr=ksstr+":00";
                            isreturn = DateTimePickerDialog.compare(str,ksstr);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (isreturn) {
                        ToastUtil.showToast("?????????????????????????????????");
                        textview.setText("");
                        return;
                    }
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    textview.setText(format2.format(date));
                }else{
                    boolean isreturn= false;
                    try {
                        String jsstr=endTimeTV.getText().toString();
                        if(jsstr.length()==19){
                            isreturn = DateTimePickerDialog.compare(jsstr,str);
                        }else if(jsstr.length()==16){
                            jsstr=jsstr+":00";
                            isreturn = DateTimePickerDialog.compare(jsstr,str);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (isreturn) {
                        ToastUtil.showToast("????????????????????????????????????");
                        textview.setText("");
                        return;
                    }
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    textview.setText(format2.format(date));
                }
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
                        //??????????????????????????????
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lin.getLayoutParams();
                        int width = getResources().getDisplayMetrics().widthPixels;
                        int height = getResources().getDisplayMetrics().heightPixels;
                        params.setMargins(20, 0, 20, 0);
                        //???????????????
                        params.width = width;
                        //???????????????
                        params.height = height / 3;
                        //??????????????????????????????????????????
                        lin.setLayoutParams(params);
                    }
                })
                .setOutSideCancelable(false)//???????????????????????????????????????????????????????????????
                .setType(new boolean[]{true, true, true, true, true, false})
                .setLabel("", "", "", "", "", "")//?????????????????????????????????
                .setDividerColor(Color.GRAY)
                .setDividerType(WheelView.DividerType.WRAP)
                .setTextXOffset(0, 0, 0, 0, 0, 0)//??????X???????????????[ -90 , 90??]
                .isDialog(true)//??????????????????????????????
                .setLineSpacingMultiplier(2.0f)//????????????????????????????????????
                .setItemVisibleCount(3)
                .setContentTextSize(18)
                .build();
        pvTime.show();
    }

    private void queryTravel() {
        mPresenter.getTravelData(deviceId, startTimeTV.getText().toString(), endTimeTV.getText().toString());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getMsgWhat()) {
            case AppConstant.MESSAGE_UPDATE_CAR_ANIMATION_STOP:
                if (playIV.getTag() != null && (Integer) playIV.getTag() == 1) {
                    playIV.performClick();
                }
                break;
            case AppConstant.PROGRESS://?????????????????????????????????
                changeAfterSeekBarProgress = (int) event.getMsgObj();
                isReft=true;
                break;
        }
    }
    /**
     * ???????????????
     */
    private Overlay polylineOverlay = null;
    public static List<TravelItemBean> gjPointList = new ArrayList<>();
    private boolean isNormal = false;
    private boolean isShow = true;//??????????????????????????????
    private boolean isReft = false;//?????????seekbar????????????

    int changeAfterSeekBarProgress;
    @Override
    public void drawTravel(TravelListBean travelListBeanList) {
        stopProgressDialog();


        mBaiduMap.clear();
        gjPointList.clear();
        DISTANCE1 = 0.00005;


        EventBus.getDefault().post(new MessageEvent(AppConstant.MESSAGE_UPDATE_CAR_ANIMATION_STOP));
        if (travelListBeanList == null || travelListBeanList.getPois() == null || travelListBeanList.getPois().size() < 2) {
            ToastUtil.showToast("??????????????????");
            ivRight.setVisibility(View.GONE);
            if (null != polylineOverlay) {
                polylineOverlay.remove();
                polylineOverlay = null;
            }
            return;
        }
        BitmapUtil.init();
        myLayout.setVisibility(View.GONE);
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.mipmap.icon_guiji_topright);
        isShow = true;


        myDetailLayout.setVisibility(View.VISIBLE);
        mMapView.getChildAt(2).setPadding(50, 0, 10, 200);//?????????????????????????????????


        List<LatLng> points = new ArrayList<>();
        gjPointList = travelListBeanList.getPois();
        RangeSeekBar.maxProgress=gjPointList.size();
        tv_gps_time.setText(gjPointList.get(0).getGpsTimeFormat());
        sbSingle5.setIndicatorTextStringFormat("");
        sbSingle5.setProgress(0f);

        double a = 0.00;
        for (TravelItemBean point : gjPointList) {
            a = ArithUtil.add(a, point.getMileage_Interval());
            point.setTotal(Double.parseDouble(ArithUtil.accurateDecimal(String.valueOf(a / 1000), 1)));
   /*         CoordinateConverter converter = new CoordinateConverter();
            converter.from(CoordinateConverter.CoordType.GPS);
            converter.coord(new LatLng(point.getLat(),
                    point.getLng()));
            LatLng result = converter.convert();*/
            LatLng result = BDMapUtils.convert(new LatLng(point.getLat(),point.getLng()));
            points.add(result);
            point.setLat(result.latitude);
            point.setLng(result.longitude);
        }
        a = Double.parseDouble(ArithUtil.accurateDecimal(String.valueOf(a / 1000), 1));

        // ???????????????????????????
        List<List<LatLng>> latLngList = new ArrayList<>();
        latLngList.add(points);
        MapUtil.showAllArea(this, mMapView, mBaiduMap, latLngList);

        LatLng startPoint = points.get(0);
        LatLng endPoint = points.get(points.size() - 1);

        // ??????????????????
        OverlayOptions startOptions = new MarkerOptions()
                .position(startPoint).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_begin))
                .zIndex(9).draggable(true);
        // ??????????????????
        OverlayOptions endOptions = new MarkerOptions().position(endPoint)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_end)).zIndex(9).draggable(true);

        // ????????????????????????
        OverlayOptions polylineOptions = new PolylineOptions().width(8).dottedLine(false).zIndex(9)
                .color(getResources().getColor(R.color.blue_color1)).points(points);

        mBaiduMap.addOverlay(startOptions);
        mBaiduMap.addOverlay(endOptions);
        polylineOverlay = mBaiduMap.addOverlay(polylineOptions);

        //???????????????
        List<TravelItemBean> parkingList = travelListBeanList.getParking();
        List<OverlayOptions> partPointList = new ArrayList<>();
        for (TravelItemBean parking : parkingList) {
            LatLng result = BDMapUtils.convert(new LatLng(parking.getLat(),parking.getLng()));
           /* CoordinateConverter converter = new CoordinateConverter();
            converter.from(CoordinateConverter.CoordType.GPS);
            converter.coord(new LatLng(parking.getLat(),
                    parking.getLng()));
            LatLng result = converter.convert();*/
            parking.setLat(result.latitude);
            parking.setLng(result.longitude);

            OverlayOptions parkingOptions = new MarkerOptions()
                    .position(new LatLng(parking.getLat(), parking.getLng())).icon(BitmapDescriptorFactory.fromResource(R.mipmap.icon_parking))
                    .zIndex(9).draggable(true);
            partPointList.add(parkingOptions);
        }
        mBaiduMap.addOverlays(partPointList);

        //TODO  ??????????????????
        if (currentData.getIsOilSensor() == 0) {
            tvYl.setVisibility(View.GONE);
        } else {
            tvYl.setVisibility(View.VISIBLE);
            tvYl.setText("?????????" + currentData.getOilQuantity());
        }
        if (currentData.getIsTemperatureSensor() == 0) {
            tvWd.setVisibility(View.GONE);
        } else {
            tvWd.setVisibility(View.VISIBLE);
            tvWd.setText("?????????" + currentData.getTemperatureFormat());
            if (tvYl.getVisibility() == View.VISIBLE) {
                tvWd.setPadding(0, DisplayUtil.dip2px(10), 0, 0);
            } else {
                tvWd.setPadding(0, 0, 0, 0);
            }
        }

        detailDeviceNOTV.setText(CarNumber);
        totalMileageTV.setText(a + "km");
        speedTV.setText("0km");
        detailStartTimeTV.setText(travelListBeanList.getStartTime());
        detailEndTimeTV.setText(travelListBeanList.getEndTime());

        startTimeAdsTV.setText(travelListBeanList.getStartTime());
        endTimeAdsTV.setText(travelListBeanList.getEndTime());
        MapUtil.geoCoderResult(startPoint, new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    if (startAdsTV != null) {
                        //????????????????????????
                        startAdsTV.setText("--");
                    }
                    return;
                } else {
                    //????????????
                    //????????????
                    String address="";
                    if(reverseGeoCodeResult.getAddress()!=null&&reverseGeoCodeResult.getSematicDescription()!=null){
                        address = reverseGeoCodeResult.getAddress()+","+reverseGeoCodeResult.getSematicDescription();
                    }
                    if (startAdsTV != null) {
                        startAdsTV.setText(address);
                    }
                }
            }
        });
        MapUtil.geoCoderResult(endPoint, new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    if (endAdsTV != null) {
                        //????????????????????????
                        endAdsTV.setText("--");
                    }
                    return;
                } else {
                    //????????????
                    String address="";
                    if(reverseGeoCodeResult.getAddress()!=null&&reverseGeoCodeResult.getSematicDescription()!=null){
                        address = reverseGeoCodeResult.getAddress()+","+reverseGeoCodeResult.getSematicDescription();
                    }
                    if (endAdsTV != null) {
                        endAdsTV.setText(address);
                    }
                }
            }
        });
    }

    public static void goToPage(Activity activity, String currentData) {
        Intent intent = new Intent(activity, NewLocusActivity.class);
        intent.putExtra("currentData", currentData);
        activity.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        if (playIV.getTag() != null && (Integer) playIV.getTag() == 1) {
            playIV.performClick();
        }
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @Override
    public void onCalendarOutOfRange(com.haibin.calendarview.Calendar calendar) {

    }

    com.haibin.calendarview.Calendar mcalendar;
    @Override
    public void onCalendarSelect(com.haibin.calendarview.Calendar calendar, boolean isClick) {
        this.mcalendar=calendar;
        initTimePicker(-1);
//        selectindex=-1;
        thisyear = calendar.getYear() + "";
        thismonth = calendar.getMonth() + "";
        thisday = calendar.getDay() + "";
        calendarView.scrollToCalendar(calendar.getYear(), calendar.getMonth(), calendar.getDay() , false, false);
        thisDay.setText(calendar.getYear() + "???" + calendar.getMonth() + "???");
        startTimeTV.setText(calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay()+" 00:00");

        //??????????????????????????????????????????????????????????????????????????????0??????23???59??????
        if(thisyear.equals(String.valueOf(calendarView.getCurYear()))&&thismonth.equals(String.valueOf(calendarView.getCurMonth()))&&thisday.equals(String.valueOf(calendarView.getCurDay()))){
            endTimeTV.setText(TimeUtil.format(System.currentTimeMillis(), "yyyy-MM-dd HH:mm"));
        }else{
            endTimeTV.setText(calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay()+" 23:59");
        }
    }

    @Override
    public void onYearChange(int year) {
        initTimePicker(-1);
        thisyear = year + "";
        thisDay.setText(thisyear + "???" + thismonth + "???");
    }
    /**
     * ?????????????????????????????????
     */
    private double getAngle(int startIndex) {
        if ((startIndex + 1) >= gjPointList.size()) {
            return -1.0;
        }
        LatLng startPoint = new LatLng(gjPointList.get(startIndex).getLat(), gjPointList.get(startIndex).getLat());
        LatLng endPoint = new LatLng(gjPointList.get(startIndex + 1).getLat(), gjPointList.get(startIndex + 1).getLat());
        return getAngle(startPoint, endPoint);
    }

    /**
     * ????????????????????????????????????
     */
    private double getAngle(LatLng fromPoint, LatLng toPoint) {
        double slope = getSlope(fromPoint, toPoint);
        if (slope == Double.MAX_VALUE) {
            if (toPoint.latitude > fromPoint.latitude) {
                return 0;
            } else {
                return 180;
            }
        } else if (slope == 0.0) {
            if (toPoint.longitude > fromPoint.longitude) {
                return -90;
            } else {
                return 90;
            }
        }
        float deltAngle = 0;
        if ((toPoint.latitude - fromPoint.latitude) * slope < 0) {
            deltAngle = 180;
        }
        double radio = Math.atan(slope);
        double angle = 180 * (radio / Math.PI) + deltAngle;
        return angle;
    }

    /**
     * ??????????????????????????????
     */
    private double getInterception(double slope, LatLng point) {
        double interception = point.latitude - slope * point.longitude;
        return interception;
    }

    /**
     * ?????????
     */
    private double getSlope(LatLng fromPoint, LatLng toPoint) {
        if (toPoint.longitude == fromPoint.longitude) {
            return Double.MAX_VALUE;
        }
        double slope = ((toPoint.latitude - fromPoint.latitude) / (toPoint.longitude
                - fromPoint.longitude));
        return slope;
    }

    /**
     * ??????x???????????????????????????
     */
    private double getXMoveDistance(double slope) {
        if (slope == Double.MAX_VALUE || slope == 0.0) {
            return DISTANCE1;
        }
        return Math.abs((DISTANCE1 * 1 / slope) / Math.sqrt(1 + 1 / (slope * slope)));
    }

    /**
     * ??????y???????????????????????????
     */
    private double getYMoveDistance(double slope) {
        if (slope == Double.MAX_VALUE || slope == 0.0) {
            return DISTANCE1;
        }
        return Math.abs((DISTANCE1 * slope) / Math.sqrt(1 + slope * slope));
    }


    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        if(mBaiduMap!=null){
            mBaiduMap.clear();
        }
        isFirst = true;
        if (threadTest != null) {
            isonDestroy = true;
            threadTest.pauseThread();
            threadTest = null;
        }
        if (task != null) {
            task.cancel(true);
            task = null;
        }
        if (task2 != null) {
            task2.cancel(true);
            task2 = null;
        }
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
