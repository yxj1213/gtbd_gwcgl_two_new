package com.ttce.vehiclemanage.ui.map.model;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.CompanysItemBean;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.ui.map.contract.FenceListContract;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/9.
 */

public class FenceListModel implements FenceListContract.Model {
    @Override
    public Observable<List<FenceListBean>> getFenceList(String DeviceId, int Page) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("DeviceId", DeviceId);
        params.add("Page", Page);
        params.add("PageSize", AppConstant.PAGESIZE);
        return Api.getDefault(HostType.BASE_HOST).getNewFenceList(params.build())
                .compose(RxHelper.<List<FenceListBean>>handleResult());
    }

    @Override
    public Observable<Boolean> updateFenceAlarmType(String id, String DeviceId, String alarmTypes) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        JsonParser parser = new JsonParser();
        params.add("Id", id);
        params.add("DeviceId", DeviceId);
        params.add("AlarmTypes",parser.parse(alarmTypes).getAsJsonArray());
        return Api.getDefault(HostType.BASE_HOST).setAlarmType(params.build())
                .compose(RxHelper.<Boolean>handleResult());
    }
}
