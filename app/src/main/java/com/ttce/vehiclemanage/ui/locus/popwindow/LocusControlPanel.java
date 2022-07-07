package com.ttce.vehiclemanage.ui.locus.popwindow;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.widget.MyLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LocusControlPanel implements MyLayout.MyLayoutCallBack, OnGetGeoCoderResultListener {

    @Bind(R.id.my_layout)
    MyLayout my_layout;

    private PopupWindow mPopupWindow;
    private static LocusControlPanel instance;
    private ControlPanelListener mControlPanelListener;
    private Activity mContext;

    private LocusControlPanel(Context context) {
        mContext = (Activity) context;
        View view = LayoutInflater.from(context).inflate(
                R.layout.layout_locus_control_panel, null);
        ButterKnife.bind(this, view);
        mPopupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        my_layout.setCallBack(this);
        //点击外部消失
        mPopupWindow.setOutsideTouchable(false);
    }

    public static LocusControlPanel newInstance(Context context, ControlPanelListener controlPanelListener) {
        if (instance == null) {
            instance = new LocusControlPanel(context);
        }
        instance.setmControlPanelListener(controlPanelListener);
        return instance;
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }

    @Override
    public void scrollByY(int m) {

    }

    public void show(View contentView) {
        mPopupWindow.showAtLocation(contentView, Gravity.BOTTOM, 0, 0);
    }

    public void setmControlPanelListener(ControlPanelListener mControlPanelListener) {
        this.mControlPanelListener = mControlPanelListener;
    }

    public interface ControlPanelListener {
        void onCancel();

        void onConfirm(String deviceId,String startTime,String endTime);
    }
}
