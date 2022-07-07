package com.ttce.vehiclemanage.ui.mine.model;

import com.google.gson.JsonParser;
import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.FenceListBean;
import com.ttce.vehiclemanage.ui.mine.constract.RailManageContract;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/9.
 */

public class RailManageModel implements RailManageContract.Model {
    @Override
    public Observable<List<FenceListBean>> getNewFenceList(int Page) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Page", Page);
        params.add("PageSize", AppConstant.PAGESIZE);
        return Api.getDefault(HostType.BASE_HOST).getNewFenceList(params.build())
                .compose(RxHelper.<List<FenceListBean>>handleResult());
    }

    @Override
    public Observable<Boolean> delRail(String id) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Id", id);
        return Api.getDefault(HostType.BASE_HOST).delOrEditCircle(params.build())
                .compose(RxHelper.<Boolean>handleResult());
    }

    @Override
    public Observable<Boolean> addDevice(String id, String devices) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        JsonParser parser = new JsonParser();
        params.add("Id", id);
        params.add("DeviceIds", parser.parse(devices).getAsJsonArray());
        return Api.getDefault(HostType.BASE_HOST).addFenceDevice(params.build())
                .compose(RxHelper.<Boolean>handleResult());
    }

}
