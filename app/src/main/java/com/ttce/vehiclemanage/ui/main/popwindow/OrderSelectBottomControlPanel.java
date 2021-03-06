package com.ttce.vehiclemanage.ui.main.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;
import com.github.gzuliyujiang.wheelpicker.contract.OnLinkagePickedListener;
import com.google.gson.Gson;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.rk.datetimepicker.DateTimePickerDialog;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarPlateTypeListBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.NeedCarTabTypeAdapter;
import com.ttce.vehiclemanage.utils.OrderTimeSelect.AntFortuneLikePicker;
import com.ttce.vehiclemanage.utils.OrderTimeSelect.AntFortuneLikeProvider;
import com.ttce.vehiclemanage.utils.ToastUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderSelectBottomControlPanel implements DateTimePickerDialog.OnDateTimePickerPickedListener {

    private static OrderSelectBottomControlPanel instance;
    private PopupWindow mPopupWindow;
    @Bind(R.id.my_layout)
    RelativeLayout my_layout;
    @Bind(R.id.tv_ksyc)
    TextView tv_ksyc;
    @Bind(R.id.tv_jsyc)
    TextView tv_jsyc;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_xzyc)
    TextView tv_xzyc;
    @Bind(R.id.tv_yyyc)
    TextView tv_yyyc;
    @Bind(R.id.lin_ycsj)
    LinearLayout lin_ycsj;
    @Bind(R.id.lin_yclx)
    LinearLayout lin_yclx;
    @Bind(R.id.recycler_view)
    ListView listview;
    @Bind(R.id.lin_ryxx)
    LinearLayout lin_ryxx;
    @Bind(R.id.edt_xm)
    EditText edt_xm;
    @Bind(R.id.edt_dh)
    EditText edt_dh;
    @Bind(R.id.img_close)
    ImageView img_close;
    private ControlPanelListener mControlPanelListener;
    public static Activity mcontext;
    public static String mtype;
    public static String mliststr,mycrvaule;
    public void setmControlPanelListener(ControlPanelListener mControlPanelListener) {
        this.mControlPanelListener = mControlPanelListener;
    }
    public interface ControlPanelListener {
        void onwc(String mtype,String neir1,String neir2);
        void onTimeSelectWc(String mtype,CarPlateTypeListBean carPlateTypeListBean);
        void onTabSelect(String mtype,CarPlateTypeListBean carPlateTypeListBean);
    }

    private OrderSelectBottomControlPanel(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_my_need_car_time, null);
        ButterKnife.bind(this, view);

        mPopupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //??????????????????
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
        //?????????????????????popupwindow
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        my_layout.getBackground().setAlpha(40);

    }
    //??????????????????????????????????????????
    private void initview() {
        tv_title.setText(mtype);
        switch (mtype){
            case "?????????":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                ycrData();
                break;
            case "?????????":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                jsrData();
                break;
            case "????????????":
                img_close.setVisibility(View.GONE);
                tv_ksyc.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                lin_ycsj.setVisibility(View.VISIBLE);
                lin_yclx.setVisibility(View.GONE);
                lin_ryxx.setVisibility(View.GONE);
                break;
            case "????????????":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                yclxData();
                break;
            case "????????????":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.GONE);
                lin_ryxx.setVisibility(View.VISIBLE);
                edt_xm.setText("");
                edt_dh.setText("");
//                edt_dh.setFocusable(true);
//                edt_dh.setFocusableInTouchMode(true);
//                edt_dh.requestFocus();
//                mcontext.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                break;
            case "????????????":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.GONE);
                lin_ryxx.setVisibility(View.VISIBLE);
                edt_xm.setText("");
                edt_dh.setText("");
//                edt_dh.setFocusable(true);
//                edt_dh.setFocusableInTouchMode(true);
//                edt_dh.requestFocus();
//                mcontext.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                break;
            case "????????????":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.GONE);
                lin_ryxx.setVisibility(View.VISIBLE);
                edt_xm.setText("");
                edt_dh.setText("");
                break;
             case "????????????":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                hPlxData();
                break;
            case "?????????":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                zwsData();
            break;
            case "????????????":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                pclxData();
            break;

        }
    }
    private int mpclxselectPosition =0;//?????????????????????????????????
    private CarPlateTypeListBean mpclxselectBrand ;//?????????????????????????????????
    private void pclxData() {
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        mlist.add(new CarPlateTypeListBean("??????","20"));
        mlist.add(new CarPlateTypeListBean("??????","10"));

        if(myclxselectBrand==null){//??????????????????????????????
            mpclxselectBrand=mlist.get(0);
        }

        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext,mlist,mpclxselectPosition,"????????????");
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarPlateTypeListBean changeCompanyBean, int selectPosition) {
                mpclxselectBrand=changeCompanyBean;
                mpclxselectPosition=selectPosition;
            }

            @Override
            public void onMRClick(CarPlateTypeListBean changeCompanyBean) {
            }
        });

        setListViewHeight(listview,10);
    }


    public static int mhPlxselectPosition = 0;//?????????????????????????????????
    public CarPlateTypeListBean mhPlxselectBrand ;//?????????????????????????????????
    private void hPlxData() {
        EmptyOrderBean emptyOrderBean=new Gson().fromJson(mliststr, EmptyOrderBean.class);
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        for (CarPlateTypeListBean carPlateTypeListBean:emptyOrderBean.getCarPlateType_List()) {
            mlist.add(carPlateTypeListBean);
        }
        mhPlxselectBrand=mlist.get(0);
        mhPlxselectPosition=0;
        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext, mlist,mhPlxselectPosition,"????????????");
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarPlateTypeListBean changeCompanyBean, int selectPosition) {
                mhPlxselectBrand=changeCompanyBean;
                mhPlxselectPosition=selectPosition;
            }

            @Override
            public void onMRClick(CarPlateTypeListBean changeCompanyBean) {
            }
        });
        setListViewHeight(listview,10);
    }

    private int mzwsselectPosition =0;//?????????????????????????????????
    private CarPlateTypeListBean mzwsselectBrand ;//?????????????????????????????????
    private void zwsData() {
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        for (int i=mMin;i<=mMax;i++){
            mlist.add(new CarPlateTypeListBean(String.valueOf(i)));
        }
        mzwsselectBrand=mlist.get(0);
        mzwsselectPosition=0;


        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext,mlist,0,"?????????");
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarPlateTypeListBean changeCompanyBean, int selectPosition) {
                mzwsselectBrand=changeCompanyBean;
                mzwsselectPosition=selectPosition;
            }

            @Override
            public void onMRClick(CarPlateTypeListBean changeCompanyBean) {
            }
        });

        setListViewHeight(listview,10);
    }
    private void setListViewHeight(ListView listView,int count){
        ListAdapter listAdapter = listView.getAdapter(); //??????ListView ??????????????????
        if(listAdapter == null){
            return;
        }

        View itemView = listAdapter.getView(0, null, listView); //?????????????????????
        //??????????????????????????????????????????????????????????????????????????? https://www.jianshu.com/p/dbd6afb2c890????????????
        itemView.measure(0,0);
        int itemHeight = itemView.getMeasuredHeight(); //???????????????
        int itemCount = listAdapter.getCount();//??????????????????
        LinearLayout.LayoutParams layoutParams = null; //???????????????????????????
        if(itemCount < count){
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,itemHeight*itemCount);
        }else if(itemCount >= count){
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,itemHeight*count);
        }
        listView.setLayoutParams(layoutParams);
    }

    private int myclxselectPosition =0;//?????????????????????????????????
    private CarPlateTypeListBean myclxselectBrand ;//?????????????????????????????????
    private void yclxData() {
        EmptyOrderBean emptyOrderBean=new Gson().fromJson(mliststr, EmptyOrderBean.class);

        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        for (int i=0;i<emptyOrderBean.getCompanyUseCarType_List().size();i++){
            CarPlateTypeListBean carPlateTypeListBean=new CarPlateTypeListBean(emptyOrderBean.getCompanyUseCarType_List().get(i).getUseCarTypeName(),emptyOrderBean.getCompanyUseCarType_List().get(i).getUseCarTypeId(),emptyOrderBean.getCompanyUseCarType_List().get(i).getProcessId(),emptyOrderBean.getCompanyUseCarType_List().get(i).isIsWillCarPlate(),emptyOrderBean.getCompanyUseCarType_List().get(i).isIsUseMapPoint(),emptyOrderBean.getCompanyUseCarType_List().get(i).isIsDefault());
            mlist.add(carPlateTypeListBean);
            if(emptyOrderBean.getCompanyUseCarType_List().get(i).isIsDefault()==true&&myclxselectBrand==null){
                myclxselectBrand=carPlateTypeListBean;
                myclxselectPosition=i;
            }
        }
            if(myclxselectBrand==null){//??????????????????????????????
                myclxselectBrand=mlist.get(0);
            }

        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext, mlist,myclxselectPosition,"????????????");
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CarPlateTypeListBean changeCompanyBean, int selectPosition) {
                myclxselectBrand=changeCompanyBean;
                myclxselectPosition=selectPosition;
            }

            @Override
            public void onMRClick(CarPlateTypeListBean changeCompanyBean) {
                myclxselectBrand=changeCompanyBean;
                mControlPanelListener.onwc("????????????????????????",myclxselectBrand.getId(),"");
            }
        });
        setListViewHeight(listview,10);
    }

    private int mycrPosition =0;//?????????????????????????????????
    private CarPlateTypeListBean mycrBrand ;//?????????????????????????????????
    private void ycrData() {
        EmptyOrderBean emptyOrderBean=new Gson().fromJson(mliststr, EmptyOrderBean.class);
        if(emptyOrderBean!=null&&emptyOrderBean.getUseMode_List()!=null&&emptyOrderBean.getUseMode_List().size()>0){
            List<CarPlateTypeListBean> mlist=new ArrayList<>();
            for (EmptyOrderBean.UseModeListBean c:emptyOrderBean.getUseMode_List()) {
                mlist.add(new CarPlateTypeListBean(c.getText(),c.getValue()));
            }
            if(mycrBrand==null){
                mycrBrand=mlist.get(0);
            }
            NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext,mlist,mycrPosition,"?????????");
            listview.setAdapter(myAdapter);
            myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(CarPlateTypeListBean changeCompanyBean, int selectPosition) {
                    mycrBrand=changeCompanyBean;
                    mycrPosition=selectPosition;
                }

                @Override
                public void onMRClick(CarPlateTypeListBean changeCompanyBean) {
                }
            });
            setListViewHeight(listview,10);
        }else{
            ToastUtil.showToast("???????????????");
        }
    }


    private int mjsrPosition =0;//?????????????????????????????????
    private CarPlateTypeListBean mjsrBrand ;//?????????????????????????????????
    private void jsrData() {
        EmptyOrderBean emptyOrderBean=new Gson().fromJson(mliststr, EmptyOrderBean.class);
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        if(mycrvaule.equals("????????????")){
            mlist=new ArrayList<>();
            mjsrBrand=null;
            mjsrPosition =0;
            if(emptyOrderBean!=null&&emptyOrderBean.getDriverType_List_10()!=null&&emptyOrderBean.getDriverType_List_10().size()>0){
                for (EmptyOrderBean.DriverTypeList10Bean c:emptyOrderBean.getDriverType_List_10()) {
                    mlist.add(new CarPlateTypeListBean(c.getText(),String.valueOf(c.getValue())));
                }
            }
        }else{
            mlist=new ArrayList<>();
            mjsrBrand=null;
            mjsrPosition =0;
            if(emptyOrderBean!=null&&emptyOrderBean.getDriverType_List_20()!=null&&emptyOrderBean.getDriverType_List_20().size()>0){
                for (EmptyOrderBean.DriverTypeList20Bean c:emptyOrderBean.getDriverType_List_20()) {
                    mlist.add(new CarPlateTypeListBean(c.getText(),String.valueOf(c.getValue())));
                }
            }
        }

        if(mlist!=null&&mlist.size()>0){
            mjsrBrand=mlist.get(0);
            NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext,mlist,mjsrPosition,"?????????");
            listview.setAdapter(myAdapter);
            myAdapter.setOnItemClickListener(new NeedCarTabTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(CarPlateTypeListBean changeCompanyBean, int selectPosition) {
                    mjsrBrand=changeCompanyBean;
                    mjsrPosition=selectPosition;
                }

                @Override
                public void onMRClick(CarPlateTypeListBean changeCompanyBean) {
                }
            });
            setListViewHeight(listview,10);
        }else{
            ToastUtil.showToast("???????????????");
        }
    }

    public static OrderSelectBottomControlPanel newInstance(String liststr, Activity context, ControlPanelListener controlPanelListener) {
        if (instance == null) {
            instance = new OrderSelectBottomControlPanel(context);
        }
        mcontext=context;
        mliststr=liststr;
        instance.setmControlPanelListener(controlPanelListener);
        return instance;
    }
    public static OrderSelectBottomControlPanel newInstance(String liststr, int min, int max, Activity context, ControlPanelListener controlPanelListener) {
        if (instance == null) {
            instance = new OrderSelectBottomControlPanel(context);
        }
        mcontext=context;
        mliststr=liststr;
        mMin=min;
        mMax=max;
        instance.setmControlPanelListener(controlPanelListener);
        return instance;
    }
    public static int mMin,mMax;
    public void show(View contentView) {
        mPopupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);

        initview();
    }

    public void dissmiss() {
        mPopupWindow.dismiss();
    }

    public boolean isShow() {
        return mPopupWindow.isShowing();
    }

    private String ycsj_yclx="????????????";
    @OnClick({R.id.tv_wc,R.id.tv_qx,R.id.tv_ksyc,R.id.tv_jsyc,R.id.tv_xzyc,R.id.tv_yyyc,R.id.edt_dh,R.id.img_close,R.id.img_close2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_close:
                tv_ksyc.setText("");
                break;
            case R.id.img_close2:
                tv_jsyc.setText("");
                break;
            case R.id.tv_wc:
                switch (mtype){
                    case "????????????":
                        if(mpclxselectBrand==null){
                            ToastUtil.showToast("????????????????????????");
                            return;
                        }
                        mControlPanelListener.onTimeSelectWc(mtype,mpclxselectBrand);
                        break;
                    case "????????????":
                        if(ycsj_yclx.equals("????????????")){
                            if(tv_ksyc.getText().toString().trim().equals("")||tv_ksyc.getText().toString().trim().equals(mcontext.getResources().getString(R.string.needcar3_time_txt3))||tv_jsyc.getText().toString().trim().equals(mcontext.getResources().getString(R.string.needcar3_time_txt4))||tv_jsyc.getText().toString().trim().equals("")){
                                ToastUtil.showToast("???????????????????????????????????????????????????");
                                return;
                            }
                        }
                        mControlPanelListener.onwc(ycsj_yclx,tv_ksyc.getText().toString().trim(),tv_jsyc.getText().toString().trim());
                        break;
                    case "????????????":
                        if(myclxselectBrand==null){
                            ToastUtil.showToast("????????????????????????");
                            return;
                        }
                        mControlPanelListener.onTimeSelectWc(mtype,myclxselectBrand);
                        break;
                    case "????????????":
                        if(mhPlxselectBrand==null){
                            ToastUtil.showToast("????????????????????????");
                            return;
                        }
                        mControlPanelListener.onTabSelect(mtype,mhPlxselectBrand);
                        break;
                    case "?????????":
                        if(mzwsselectBrand==null){
                            ToastUtil.showToast("?????????????????????");
                            return;
                        }
                         mControlPanelListener.onTabSelect(mtype,mzwsselectBrand);
                        break;
                    case "?????????":
                        if(mjsrBrand==null){
                            ToastUtil.showToast("?????????????????????");
                            return;
                        }
                        mControlPanelListener.onTimeSelectWc(mtype,mjsrBrand);
                        break;
                    case "?????????":
                        if(mycrBrand==null){
                            ToastUtil.showToast("?????????????????????");
                            return;
                        }
                        mControlPanelListener.onTimeSelectWc(mtype,mycrBrand);
                        break;
                    default:
                        if(edt_xm.getText().toString().trim().equals("")||edt_dh.getText().toString().trim().equals("")){
                            ToastUtil.showToast("???????????????????????????");
                            return;
                        }
                        mControlPanelListener.onwc(mtype,edt_xm.getText().toString().trim(),edt_dh.getText().toString().trim());
                        break;
                }
                mPopupWindow.dismiss();
                break;
            case R.id.tv_qx:
                mPopupWindow.dismiss();
                break;
            case R.id.tv_ksyc:
                //showDatePicker("??????????????????", view);
                setTimeSelect("??????????????????",tv_ksyc);
                break;
            case R.id.tv_jsyc:
                //showDatePicker("??????????????????", view);
                setTimeSelect("??????????????????",tv_jsyc);
                break;
            case R.id.tv_xzyc:
                ycsj_yclx="????????????";
                img_close.setVisibility(View.GONE);
                tv_ksyc.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                 tv_jsyc.setText(R.string.needcar3_time_txt4);
                tv_xzyc.setBackgroundResource(R.drawable.blue_15_bg);
                tv_xzyc.setTextColor(mcontext.getResources().getColor(R.color.white));
                tv_yyyc.setBackgroundResource(R.drawable.gray_15_bg);
                tv_yyyc.setTextColor(mcontext.getResources().getColor(R.color.work_item_txt_color));
                break;
            case R.id.tv_yyyc:
                ycsj_yclx="????????????";
                img_close.setVisibility(View.VISIBLE);
                tv_ksyc.setText(R.string.needcar3_time_txt3);
                tv_jsyc.setText(R.string.needcar3_time_txt4);
                tv_yyyc.setBackgroundResource(R.drawable.blue_15_bg);
                tv_yyyc.setTextColor(mcontext.getResources().getColor(R.color.white));
                tv_xzyc.setBackgroundResource(R.drawable.gray_15_bg);
                tv_xzyc.setTextColor(mcontext.getResources().getColor(R.color.work_item_txt_color));
                break;
            case R.id.edt_dh:

                break;
        }
    }

    @Override
    public void onPicked(int what, String dateTimeStr) {

    }

    @Override
    public void onPicked(int what, long dateTimeLng) {

    }

    @Override
    public void onPicked(String what, long dateTimeLng, String dateTimeStr, View view) {
        if(what.equals("??????????????????")){
            try {
                boolean isreturn= false;
                String ksstr=tv_ksyc.getText().toString();
                if(ksstr.length()==19){
                    isreturn = DateTimePickerDialog.compare(dateTimeStr,ksstr);
                }else if(ksstr.length()==16){
                    ksstr=ksstr+":00";
                    isreturn = DateTimePickerDialog.compare(dateTimeStr,ksstr);
                }
                if(isreturn==true){
                    ToastUtil.showToast("?????????????????????????????????????????????????????????????????????");
                    ((TextView) view).setText(null);
                }else{
                    ((TextView) view).setText(dateTimeStr.substring(0,dateTimeStr.lastIndexOf(":")));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            if(ycsj_yclx.equals("????????????")){
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowdata=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                Date now = null;
                Date date= null;
                try {
                    now = df.parse(dateTimeStr);
                    date=df.parse(nowdata);
                    long l=now.getTime()-date.getTime();
                    long day=l/(24*60*60*1000);
                    long hour=(l/(60*60*1000)-day*24);
                    long min=((l/(60*1000))-day*24*60-hour*60);
                    long s=(l/1000-day*24*60*60-hour*60*60-min*60);
                    if(min<10){
                        ToastUtil.showToast("??????????????????????????????????????????10?????????");
                        ((TextView) view).setText(null);
                        tv_jsyc.setText("");
                    }else {
                        ((TextView) view).setText(dateTimeStr.substring(0,dateTimeStr.lastIndexOf(":")));
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                ((TextView) view).setText(dateTimeStr.substring(0,dateTimeStr.lastIndexOf(":")));
            }
        }
    }
    TimePickerView pvTime = null;
    private void setTimeSelect(String title, TextView textview) {
        AntFortuneLikePicker picker = new AntFortuneLikePicker(mcontext,ycsj_yclx);
        picker.setOnLinkagePickedListener(new OnLinkagePickedListener() {
            @Override
            public void onLinkagePicked(Object first, Object second, Object third) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    //?????????????????????????????????
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(new Date());
                    //???????????????????????????
                    String today = sdf.format(calendar.getTime());

                    //????????????????????????
                    calendar.add(calendar.DATE, +1);
                    String tomorrow = sdf.format(calendar.getTime());

                    //????????????????????????
                    calendar.add(calendar.DATE, +1);
                    String day_after_tomorrow= sdf.format(calendar.getTime());

                    String str="";
                    if(String.valueOf(first).contains("??????")){
                        str=today+" "+second.toString().split("???")[0]+":"+third.toString().split("???")[0]+":00";
                    }else if(String.valueOf(first).contains("??????")){
                        str=tomorrow+" "+second.toString().split("???")[0]+":"+third.toString().split("???")[0]+":00";
                    }else if(String.valueOf(first).contains("??????")){
                        str=day_after_tomorrow+" "+second.toString().split("???")[0]+":"+third.toString().split("???")[0]+":00";
                    }else{
                        SimpleDateFormat nowsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        //?????????????????????????????????
                        Calendar calendar2 = new GregorianCalendar();
                        calendar2.setTime(new Date());
                        str=nowsdf.format(calendar2.getTime());
                    }

                    if(title.equals("??????????????????")){
                        boolean isreturn= false;
                        try {
                            String ksstr=tv_ksyc.getText().toString();
                            if(ksstr.length()==19){
                                isreturn = DateTimePickerDialog.compare(str,ksstr);
                            }else if(ksstr.length()==16){
                                ksstr=ksstr+":00";
                                isreturn = DateTimePickerDialog.compare(str,ksstr);
                            }
                            Log.d("OrderSelectBottom",str+"---"+ksstr+"--"+isreturn);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (isreturn) {
                            ToastUtil.showToast("?????????????????????????????????");
                            textview.setText("");
                            return;
                        }

                        textview.setText(str.substring(0,str.lastIndexOf(":")));
                    }else{//??????????????????
                        boolean isreturn= false;
                        try {
                            String jsstr=tv_jsyc.getText().toString();
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
                        if(ycsj_yclx.equals("????????????")){
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = null;

                            try {
                                date = format.parse(str);
                            } catch (ParseException e) {
                            }
                            boolean isShuRu = DateTimePickerDialog.getDatePoor(date,new Date());

                            if(isShuRu==false){
                                ToastUtil.showToast("????????????????????????????????????10?????????");
                                textview.setText("");
                                return;
                            }
                        }
                        textview.setText(str.substring(0,str.lastIndexOf(":")));
                    }
            }
        });
        picker.show();
//        //???????????????
//        pvTime = new TimePickerBuilder(mcontext, new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String str=format.format(date);
//                if(title.equals("??????????????????")){
//                    boolean isreturn= false;
//                    try {
//                        String ksstr=tv_ksyc.getText().toString();
//                        if(ksstr.length()==19){
//                            isreturn = DateTimePickerDialog.compare(str,ksstr);
//                        }else if(ksstr.length()==16){
//                            ksstr=ksstr+":00";
//                            isreturn = DateTimePickerDialog.compare(str,ksstr);
//                        }
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }
//                    if (isreturn) {
//                        ToastUtil.showToast("?????????????????????????????????");
//                        textview.setText("");
//                        return;
//                    }
//                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                    textview.setText(format2.format(date));
//                }else{
//                        boolean isreturn= false;
//                        try {
//                            String jsstr=tv_jsyc.getText().toString();
//                            if(jsstr.length()==19){
//                                isreturn = DateTimePickerDialog.compare(jsstr,str);
//                            }else if(jsstr.length()==16){
//                                jsstr=jsstr+":00";
//                                isreturn = DateTimePickerDialog.compare(jsstr,str);
//                            }
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        if (isreturn) {
//                            ToastUtil.showToast("????????????????????????????????????");
//                            textview.setText("");
//                            return;
//                        }
//                    if(ycsj_yclx.equals("????????????")){
//                        boolean isShuRu = DateTimePickerDialog.getDatePoor(date,new Date());
//                        if(isShuRu==false){
//                            ToastUtil.showToast("????????????????????????????????????10?????????");
//                            textview.setText("");
//                            return;
//                        }
//                    }
//                        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//                        textview.setText(format2.format(date));
//                }
//            }
//        })
//                .setLayoutRes(R.layout.dialog_time_select, new CustomListener() {
//                    @Override
//                    public void customLayout(View v) {
//
//                        LinearLayout lin = (LinearLayout) v.findViewById(R.id.dialog_date_lin);
//                        TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
//                        TextView tvSure = (TextView) v.findViewById(R.id.tv_sure);
//                        tvTitle.setText(title);
//                        tvSure.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                pvTime.returnData();
//                                pvTime.dismiss();
//                            }
//                        });
//                        //??????????????????????????????
//                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lin.getLayoutParams();
//                        int width = mcontext.getResources().getDisplayMetrics().widthPixels;
//                        int height = mcontext.getResources().getDisplayMetrics().heightPixels;
//                        params.setMargins(20, 0, 20, 0);
//                        //???????????????
//                        params.width = width;
//                        //???????????????
//                        params.height = height / 3;
//                        //??????????????????????????????????????????
//                        lin.setLayoutParams(params);
//                    }
//                })
//                .setOutSideCancelable(false)//???????????????????????????????????????????????????????????????
//                .setType(new boolean[]{true, true, true, true, true, false})
//                .setLabel("", "", "", "", "", "")//?????????????????????????????????
//                .setDividerColor(Color.GRAY)
//                .setDividerType(WheelView.DividerType.WRAP)
//                .setTextXOffset(0, 0, 0, 0, 0, 0)//??????X???????????????[ -90 , 90??]
//                .isDialog(true)//??????????????????????????????
//                .setLineSpacingMultiplier(2.0f)//????????????????????????????????????
//                .setItemVisibleCount(3)
//                .setContentTextSize(18)
//                .build();
//        pvTime.show();
    }
    private void showDatePicker(String title, View view) {
        DateTimePickerDialog dateTimePickerDialog = new DateTimePickerDialog(1, mcontext, R.color.blue_color1, title, this, DateTimePickerDialog.YYYY_MM_DD_HH_MM_SS, view);
        dateTimePickerDialog.showPicker();
    }
}
