package com.ttce.vehiclemanage.utils.dialog_photo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;
import com.google.gson.Gson;
import com.rk.datetimepicker.DateTimePickerDialog;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CarPlateTypeListBean;
import com.ttce.vehiclemanage.bean.EmptyOrderBean;
import com.ttce.vehiclemanage.ui.main.adapter.needcar.NeedCarTabTypeAdapter;
import com.ttce.vehiclemanage.utils.ToastUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPhotoBottomControlPanel{

    private static MyPhotoBottomControlPanel instance;
    private PopupWindow mPopupWindow;

    @Bind(R.id.my_layout)
    RelativeLayout my_layout;
    private ControlPanelListener mControlPanelListener;
    public static Activity mcontext;
    public String mtype;
    public void setmControlPanelListener(ControlPanelListener mControlPanelListener) {
        this.mControlPanelListener = mControlPanelListener;
    }
    public interface ControlPanelListener {
        void onPhotoSelectWc(String mtype);
    }

    private MyPhotoBottomControlPanel(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_my_photo, null);
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

    public static MyPhotoBottomControlPanel newInstance(Activity context, ControlPanelListener controlPanelListener) {
        if (instance == null) {
            instance = new MyPhotoBottomControlPanel(context);
        }
        mcontext=context;
        instance.setmControlPanelListener(controlPanelListener);
        return instance;
    }

    public void show(View contentView) {
        mPopupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    public void dissmiss() {
        mPopupWindow.dismiss();
    }

    public boolean isShow() {
        return mPopupWindow.isShowing();
    }

    @OnClick({R.id.tv_avatar_photograph,R.id.tv_avatar_photo,R.id.tv_avatar_cancel,R.id.tv_qx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_avatar_photograph:
                mtype="1";
                break;
            case R.id.tv_avatar_photo:
                mtype="2";
                break;
            case R.id.tv_avatar_cancel:
                mtype="3";
                break;
            case R.id.tv_qx:
                mtype="4";
                break;
        }
        mControlPanelListener.onPhotoSelectWc(mtype);
        dissmiss();
    }
}
