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
        //点击外部消失
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setFocusable(true);
        //软键盘不会挡着popupwindow
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        my_layout.getBackground().setAlpha(40);

    }
    //根据不同的标题显示不同的内容
    private void initview() {
        tv_title.setText(mtype);
        switch (mtype){
            case "用车人":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                ycrData();
                break;
            case "驾驶人":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                jsrData();
                break;
            case "用车时间":
                img_close.setVisibility(View.GONE);
                tv_ksyc.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                lin_ycsj.setVisibility(View.VISIBLE);
                lin_yclx.setVisibility(View.GONE);
                lin_ryxx.setVisibility(View.GONE);
                break;
            case "用车类型":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                yclxData();
                break;
            case "他人用车":
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
            case "带队组长":
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
            case "随车人员":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.GONE);
                lin_ryxx.setVisibility(View.VISIBLE);
                edt_xm.setText("");
                edt_dh.setText("");
                break;
             case "号牌类型":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                hPlxData();
                break;
            case "座位数":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                zwsData();
            break;
            case "派车类型":
                lin_ycsj.setVisibility(View.GONE);
                lin_yclx.setVisibility(View.VISIBLE);
                lin_ryxx.setVisibility(View.GONE);
                pclxData();
            break;

        }
    }
    private int mpclxselectPosition =0;//用于记录用户选择的变量
    private CarPlateTypeListBean mpclxselectBrand ;//用于记录用户选择的对象
    private void pclxData() {
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        mlist.add(new CarPlateTypeListBean("指派","20"));
        mlist.add(new CarPlateTypeListBean("抢单","10"));

        if(myclxselectBrand==null){//判断用户是否有默认值
            mpclxselectBrand=mlist.get(0);
        }

        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext,mlist,mpclxselectPosition,"派车类型");
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


    public static int mhPlxselectPosition = 0;//用于记录用户选择的变量
    public CarPlateTypeListBean mhPlxselectBrand ;//用于记录用户选择的对象
    private void hPlxData() {
        EmptyOrderBean emptyOrderBean=new Gson().fromJson(mliststr, EmptyOrderBean.class);
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        for (CarPlateTypeListBean carPlateTypeListBean:emptyOrderBean.getCarPlateType_List()) {
            mlist.add(carPlateTypeListBean);
        }
        mhPlxselectBrand=mlist.get(0);
        mhPlxselectPosition=0;
        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext, mlist,mhPlxselectPosition,"号牌类型");
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

    private int mzwsselectPosition =0;//用于记录用户选择的变量
    private CarPlateTypeListBean mzwsselectBrand ;//用于记录用户选择的对象
    private void zwsData() {
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        for (int i=mMin;i<=mMax;i++){
            mlist.add(new CarPlateTypeListBean(String.valueOf(i)));
        }
        mzwsselectBrand=mlist.get(0);
        mzwsselectPosition=0;


        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext,mlist,0,"座位数");
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
        ListAdapter listAdapter = listView.getAdapter(); //得到ListView 添加的适配器
        if(listAdapter == null){
            return;
        }

        View itemView = listAdapter.getView(0, null, listView); //获取其中的一项
        //进行这一项的测量，为什么加这一步，具体分析可以参考 https://www.jianshu.com/p/dbd6afb2c890这篇文章
        itemView.measure(0,0);
        int itemHeight = itemView.getMeasuredHeight(); //一项的高度
        int itemCount = listAdapter.getCount();//得到总的项数
        LinearLayout.LayoutParams layoutParams = null; //进行布局参数的设置
        if(itemCount < count){
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,itemHeight*itemCount);
        }else if(itemCount >= count){
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT ,itemHeight*count);
        }
        listView.setLayoutParams(layoutParams);
    }

    private int myclxselectPosition =0;//用于记录用户选择的变量
    private CarPlateTypeListBean myclxselectBrand ;//用于记录用户选择的对象
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
            if(myclxselectBrand==null){//判断用户是否有默认值
                myclxselectBrand=mlist.get(0);
            }

        NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext, mlist,myclxselectPosition,"用车类型");
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
                mControlPanelListener.onwc("设置默认用车类型",myclxselectBrand.getId(),"");
            }
        });
        setListViewHeight(listview,10);
    }

    private int mycrPosition =0;//用于记录用户选择的变量
    private CarPlateTypeListBean mycrBrand ;//用于记录用户选择的对象
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
            NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext,mlist,mycrPosition,"用车人");
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
            ToastUtil.showToast("暂无用车人");
        }
    }


    private int mjsrPosition =0;//用于记录用户选择的变量
    private CarPlateTypeListBean mjsrBrand ;//用于记录用户选择的对象
    private void jsrData() {
        EmptyOrderBean emptyOrderBean=new Gson().fromJson(mliststr, EmptyOrderBean.class);
        List<CarPlateTypeListBean> mlist=new ArrayList<>();
        if(mycrvaule.equals("本人用车")){
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
            NeedCarTabTypeAdapter myAdapter = new NeedCarTabTypeAdapter(mcontext,mlist,mjsrPosition,"驾驶人");
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
            ToastUtil.showToast("暂无驾驶人");
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

    private String ycsj_yclx="现在用车";
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
                    case "派车类型":
                        if(mpclxselectBrand==null){
                            ToastUtil.showToast("请选择派车类型。");
                            return;
                        }
                        mControlPanelListener.onTimeSelectWc(mtype,mpclxselectBrand);
                        break;
                    case "用车时间":
                        if(ycsj_yclx.equals("预约用车")){
                            if(tv_ksyc.getText().toString().trim().equals("")||tv_ksyc.getText().toString().trim().equals(mcontext.getResources().getString(R.string.needcar3_time_txt3))||tv_jsyc.getText().toString().trim().equals(mcontext.getResources().getString(R.string.needcar3_time_txt4))||tv_jsyc.getText().toString().trim().equals("")){
                                ToastUtil.showToast("请选择开始用车时间和结束用车时间。");
                                return;
                            }
                        }
                        mControlPanelListener.onwc(ycsj_yclx,tv_ksyc.getText().toString().trim(),tv_jsyc.getText().toString().trim());
                        break;
                    case "用车类型":
                        if(myclxselectBrand==null){
                            ToastUtil.showToast("请选择用车类型。");
                            return;
                        }
                        mControlPanelListener.onTimeSelectWc(mtype,myclxselectBrand);
                        break;
                    case "号牌类型":
                        if(mhPlxselectBrand==null){
                            ToastUtil.showToast("请选择号牌类型。");
                            return;
                        }
                        mControlPanelListener.onTabSelect(mtype,mhPlxselectBrand);
                        break;
                    case "座位数":
                        if(mzwsselectBrand==null){
                            ToastUtil.showToast("请选择座位数。");
                            return;
                        }
                         mControlPanelListener.onTabSelect(mtype,mzwsselectBrand);
                        break;
                    case "驾驶人":
                        if(mjsrBrand==null){
                            ToastUtil.showToast("请选择驾驶人。");
                            return;
                        }
                        mControlPanelListener.onTimeSelectWc(mtype,mjsrBrand);
                        break;
                    case "用车人":
                        if(mycrBrand==null){
                            ToastUtil.showToast("请选择用车人。");
                            return;
                        }
                        mControlPanelListener.onTimeSelectWc(mtype,mycrBrand);
                        break;
                    default:
                        if(edt_xm.getText().toString().trim().equals("")||edt_dh.getText().toString().trim().equals("")){
                            ToastUtil.showToast("请输入姓名或电话。");
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
                //showDatePicker("开始用车时间", view);
                setTimeSelect("开始用车时间",tv_ksyc);
                break;
            case R.id.tv_jsyc:
                //showDatePicker("结束用车时间", view);
                setTimeSelect("结束用车时间",tv_jsyc);
                break;
            case R.id.tv_xzyc:
                ycsj_yclx="现在用车";
                img_close.setVisibility(View.GONE);
                tv_ksyc.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                 tv_jsyc.setText(R.string.needcar3_time_txt4);
                tv_xzyc.setBackgroundResource(R.drawable.blue_15_bg);
                tv_xzyc.setTextColor(mcontext.getResources().getColor(R.color.white));
                tv_yyyc.setBackgroundResource(R.drawable.gray_15_bg);
                tv_yyyc.setTextColor(mcontext.getResources().getColor(R.color.work_item_txt_color));
                break;
            case R.id.tv_yyyc:
                ycsj_yclx="预约用车";
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
        if(what.equals("结束用车时间")){
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
                    ToastUtil.showToast("结束用车时间不能早于开始用车时间，请重新选择。");
                    ((TextView) view).setText(null);
                }else{
                    ((TextView) view).setText(dateTimeStr.substring(0,dateTimeStr.lastIndexOf(":")));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            if(ycsj_yclx.equals("预约用车")){
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
                        ToastUtil.showToast("预约开始时间必须大于当前时间10分钟。");
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
                    //先取得今天的日历日时间
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(new Date());
                    //转换得到今天的日期
                    String today = sdf.format(calendar.getTime());

                    //转换得明天的日期
                    calendar.add(calendar.DATE, +1);
                    String tomorrow = sdf.format(calendar.getTime());

                    //转换得后天的日期
                    calendar.add(calendar.DATE, +1);
                    String day_after_tomorrow= sdf.format(calendar.getTime());

                    String str="";
                    if(String.valueOf(first).contains("今天")){
                        str=today+" "+second.toString().split("点")[0]+":"+third.toString().split("分")[0]+":00";
                    }else if(String.valueOf(first).contains("明天")){
                        str=tomorrow+" "+second.toString().split("点")[0]+":"+third.toString().split("分")[0]+":00";
                    }else if(String.valueOf(first).contains("后天")){
                        str=day_after_tomorrow+" "+second.toString().split("点")[0]+":"+third.toString().split("分")[0]+":00";
                    }else{
                        SimpleDateFormat nowsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                        //先取得今天的日历日时间
                        Calendar calendar2 = new GregorianCalendar();
                        calendar2.setTime(new Date());
                        str=nowsdf.format(calendar2.getTime());
                    }

                    if(title.equals("结束用车时间")){
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
                            ToastUtil.showToast("结束时间需大于开始时间");
                            textview.setText("");
                            return;
                        }

                        textview.setText(str.substring(0,str.lastIndexOf(":")));
                    }else{//开始用车时间
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
                            ToastUtil.showToast("开始时间不能大于结束时间");
                            textview.setText("");
                            return;
                        }
                        if(ycsj_yclx.equals("预约用车")){
                            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = null;

                            try {
                                date = format.parse(str);
                            } catch (ParseException e) {
                            }
                            boolean isShuRu = DateTimePickerDialog.getDatePoor(date,new Date());

                            if(isShuRu==false){
                                ToastUtil.showToast("开始时间必须大于当前时间10分钟。");
                                textview.setText("");
                                return;
                            }
                        }
                        textview.setText(str.substring(0,str.lastIndexOf(":")));
                    }
            }
        });
        picker.show();
//        //时间选择器
//        pvTime = new TimePickerBuilder(mcontext, new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {
//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String str=format.format(date);
//                if(title.equals("结束用车时间")){
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
//                        ToastUtil.showToast("结束时间需大于开始时间");
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
//                            ToastUtil.showToast("开始时间不能大于结束时间");
//                            textview.setText("");
//                            return;
//                        }
//                    if(ycsj_yclx.equals("预约用车")){
//                        boolean isShuRu = DateTimePickerDialog.getDatePoor(date,new Date());
//                        if(isShuRu==false){
//                            ToastUtil.showToast("开始时间必须大于当前时间10分钟。");
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
//                        //取控件当前的布局参数
//                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) lin.getLayoutParams();
//                        int width = mcontext.getResources().getDisplayMetrics().widthPixels;
//                        int height = mcontext.getResources().getDisplayMetrics().heightPixels;
//                        params.setMargins(20, 0, 20, 0);
//                        //设置宽度值
//                        params.width = width;
//                        //设置高度值
//                        params.height = height / 3;
//                        //使设置好的布局参数应用到控件
//                        lin.setLayoutParams(params);
//                    }
//                })
//                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
//                .setType(new boolean[]{true, true, true, true, true, false})
//                .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
//                .setDividerColor(Color.GRAY)
//                .setDividerType(WheelView.DividerType.WRAP)
//                .setTextXOffset(0, 0, 0, 0, 0, 0)//设置X轴倾斜角度[ -90 , 90°]
//                .isDialog(true)//是否显示为对话框样式
//                .setLineSpacingMultiplier(2.0f)//设置两横线之间的间隔倍数
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
