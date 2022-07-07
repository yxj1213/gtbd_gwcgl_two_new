package com.ttce.vehiclemanage.ui.map.model;

import com.google.gson.JsonParser;
import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.ui.map.contract.AddFenceContract;
import com.ttce.vehiclemanage.ui.map.contract.FenceListContract;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/9.
 */

public class AddFenceModel implements AddFenceContract.Model {

    @Override
    public Observable<Boolean> addFence(String name,int radius,double lat,double lng,String ads,String alarmTypes,String deviceIds) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        JsonParser parser = new JsonParser();
        params.add("Name", name);
        params.add("Radius", radius);
        params.add("Lat", lat);
        params.add("Lng", lng);
        params.add("Address", ads);
        params.add("AlarmTypes",parser.parse(alarmTypes).getAsJsonArray());
        params.add("DeviceIds", parser.parse(deviceIds).getAsJsonArray());
        return Api.getDefault(HostType.BASE_HOST).addOrEditCircle(params.build())
                .compose(RxHelper.<Boolean>handleResult());
    }

    @Override
    public Observable<Boolean> updateFence(String id, String name, int radius, double lat, double lng, String ads, String alarmTypes, String deviceIds) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        JsonParser parser = new JsonParser();
        params.add("Id", id);
        params.add("Name", name);
        params.add("Radius", radius);
        params.add("Lat", lat);
        params.add("Lng", lng);
        params.add("Address", ads);
        params.add("AlarmTypes",parser.parse(alarmTypes).getAsJsonArray());
        params.add("DeviceIds", parser.parse(deviceIds).getAsJsonArray());
        return Api.getDefault(HostType.BASE_HOST).addOrEditCircle(params.build())
                .compose(RxHelper.<Boolean>handleResult());
    }
}
