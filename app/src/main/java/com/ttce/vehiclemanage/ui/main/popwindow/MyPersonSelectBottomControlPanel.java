package com.ttce.vehiclemanage.ui.main.popwindow;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.DepartmentByCompanyIdBean;
import com.ttce.vehiclemanage.bean.DictTypeListBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.bean.MessageBean;
import com.ttce.vehiclemanage.ui.main.adapter.me.MyPersonSelectStringAdapter;
import com.ttce.vehiclemanage.ui.main.adapter.workbean.jcxx.ygxx.PersonDepartOrDictDetailsAdapter;
import com.ttce.vehiclemanage.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPersonSelectBottomControlPanel{

    private static MyPersonSelectBottomControlPanel instance;
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
    private ControlPanelListener mControlPanelListener;
    public static Activity mcontext;
    public static String mtype;
    public static String mstr;
    public static String mliststr,mycrvaule;
    public void setmControlPanelListener(ControlPanelListener mControlPanelListener) {
        this.mControlPanelListener = mControlPanelListener;
    }
    public interface ControlPanelListener {
        void onwc(String neir1,String mtype);
        void onwc(MessageBean messageBean,String mtype);
    }

    private MyPersonSelectBottomControlPanel(Context context) {
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
        lin_ycsj.setVisibility(View.GONE);
        lin_yclx.setVisibility(View.VISIBLE);
        lin_ryxx.setVisibility(View.GONE);
        List<String> list=new ArrayList<>();
        switch (mtype){
            case "性别":
                list=new ArrayList<>();
                list.add("男");
                list.add("女");
                pclxData(list,mtype);
                break;
            case "是否有驾照":
                list=new ArrayList<>();
                list.add("有");
                list.add("无");
                pclxData(list,mtype);
                break;
            case "证件类型":
                list=new ArrayList<>();
                list.add("身份证");
                list.add("驾驶证");
                list.add("学生证");
                list.add("工作证");
                pclxData(list,mtype);
                break;
            case "选择岗位":
                List<DictTypeListBean> mDictTypelist = new Gson().fromJson(mstr, new TypeToken<List<DictTypeListBean>>() {}.getType());
                dictTypeData(mDictTypelist);
                break;
            case "选择部门":
                List<DepartmentByCompanyIdBean> mDepartmentlist = new Gson().fromJson(mstr, new TypeToken<List<DepartmentByCompanyIdBean>>() {}.getType());
                DepartmentData(mDepartmentlist);
                break;
        }

    }
    MessageBean selectDictTypeBean;
    private void dictTypeData(List<DictTypeListBean> mlist) {
        List<MessageBean> messageBeanList=new ArrayList<>();
        for (DictTypeListBean dic:mlist) {
            messageBeanList.add(new MessageBean(dic.getName(),dic.getID()));
        }
        PersonDepartOrDictDetailsAdapter myAdapter = new PersonDepartOrDictDetailsAdapter(mcontext,messageBeanList,mtype);
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new PersonDepartOrDictDetailsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MessageBean changeCompanyBean) {
                selectDictTypeBean=changeCompanyBean;
            }
        });

        setListViewHeight(listview,10);
    }
    MessageBean selectDepartmentBean;
    private void DepartmentData(List<DepartmentByCompanyIdBean> mlist) {
        List<MessageBean> messageBeanList=new ArrayList<>();
        for (DepartmentByCompanyIdBean dic:mlist) {
            messageBeanList.add(new MessageBean(dic.getName(),dic.getID()));
        }
        PersonDepartOrDictDetailsAdapter myAdapter = new PersonDepartOrDictDetailsAdapter(mcontext,messageBeanList,mtype);
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new PersonDepartOrDictDetailsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MessageBean changeCompanyBean) {
                selectDepartmentBean=changeCompanyBean;
            }
        });

        setListViewHeight(listview,10);
    }

    String selectName="",selectType="";
    private void pclxData(List<String> mlist,String mtype) {
        MyPersonSelectStringAdapter myAdapter = new MyPersonSelectStringAdapter(mcontext,mlist,mtype);
        listview.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyPersonSelectStringAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String changeCompanyBean,String type) {
                selectName=changeCompanyBean;
                selectType=type;
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

    public static MyPersonSelectBottomControlPanel newInstance(Activity context, ControlPanelListener controlPanelListener,String str) {
        if (instance == null) {
            instance = new MyPersonSelectBottomControlPanel(context);
        }
        mcontext=context;
        mstr=str;
        instance.setmControlPanelListener(controlPanelListener);
        return instance;
    }

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
    @OnClick({R.id.tv_wc,R.id.tv_qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_wc:
                switch (mtype){
                    case "选择岗位":
                        if(selectDictTypeBean==null){
                            ToastUtil.showToast("请选择岗位。");
                            return;
                        }
                        mControlPanelListener.onwc(selectDictTypeBean,mtype);
                        break;
                    case "选择部门":
                        if(selectDepartmentBean==null){
                            ToastUtil.showToast("请选择部门。");
                            return;
                        }
                        mControlPanelListener.onwc(selectDepartmentBean,mtype);
                        break;
                    default:
                        mControlPanelListener.onwc(selectName,selectType);
                        break;
                }
                mPopupWindow.dismiss();
                break;
            case R.id.tv_qx:
                mPopupWindow.dismiss();
                break;
        }
    }

}
