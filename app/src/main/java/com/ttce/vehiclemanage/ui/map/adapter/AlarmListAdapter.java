package com.ttce.vehiclemanage.ui.map.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.aspsine.irecyclerview.universaladapter.ViewHolderHelper;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemRecycleViewAdapter;
import com.aspsine.irecyclerview.universaladapter.recyclerview.MultiItemTypeSupport;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlarmListBean;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.utils.BDMapUtils;
import com.ttce.vehiclemanage.utils.MapUtil;

import java.util.List;

/**
 * Created by hk on 2019/7/8.
 */

public class AlarmListAdapter extends MultiItemRecycleViewAdapter<AlarmListBean> {

    private AlarmListenter alarmListenter;

    public AlarmListAdapter(Context context, final List<AlarmListBean> datas, AlarmListenter alarmListenter) {
        super(context, datas, new MultiItemTypeSupport<AlarmListBean>() {
            @Override
            public int getLayoutId(int type) {
                return R.layout.adapter_alarm_list;
            }

            @Override
            public int getItemViewType(int position, AlarmListBean msg) {
                return 0;
            }
        });
        this.alarmListenter = alarmListenter;
    }

    @Override
    public void convert(ViewHolderHelper holder, AlarmListBean newsSummary) {
        switch (holder.getLayoutId()) {
            case R.layout.adapter_alarm_list:
                setItemValues(holder, newsSummary, getPosition(holder));
                break;
        }
    }

    /**
     * 普通样式
     *
     * @param holder
     * @param newsSummary
     * @param position
     */
    private void setItemValues(final ViewHolderHelper holder, final AlarmListBean newsSummary, final int position) {
        holder.setText(R.id.tv_cph, newsSummary.getPlateNumber());
        holder.setText(R.id.tv_time, newsSummary.getAlarmTimeStr());
        TextView tv_car_location=holder.getView(R.id.tv_car_location);
        tv_car_location.setVisibility(View.VISIBLE);
        setAds(BDMapUtils.convert(new LatLng(newsSummary.getLat(),newsSummary.getLng())),tv_car_location);
        holder.setText(R.id.tv_content, newsSummary.getName() + "（" + newsSummary.getDeviceId() + ")");
        holder.setText(R.id.tv_cx, "车型：" + newsSummary.getCarTypeStr() + "    颜色：" + newsSummary.getCarColorStr());
        if (newsSummary.getIsHandled() == 0) {
            holder.setText(R.id.tv_state, "未处理");
            holder.setOnClickListener(R.id.tv_state, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alarmListenter.updateAlarmType(newsSummary);
                }
            });
        } else {
            holder.setText(R.id.tv_state, "已处理");
        }
    }
    private void setAds(LatLng latLng, TextView txt) {
        // 逆地址解析
        MapUtil.geoCoderResult(latLng, new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                if (reverseGeoCodeResult == null || reverseGeoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
                    // 没有找到检索结果
                    return;
                } else {
                    // 详细地址
                    // 详细地址
                    String address="";
                    if(reverseGeoCodeResult.getAddress()!=null&&reverseGeoCodeResult.getSematicDescription()!=null){
                        address = reverseGeoCodeResult.getAddress()+","+reverseGeoCodeResult.getSematicDescription();
                    }
                    txt.setText("车辆位置："+address);
                }
            }
        });
    }
    public interface AlarmListenter {
        void updateAlarmType(AlarmListBean alarmListBean);
    }
}
