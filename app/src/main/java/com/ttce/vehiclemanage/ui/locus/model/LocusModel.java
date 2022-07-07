package com.ttce.vehiclemanage.ui.locus.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.ui.locus.contract.LocusContract;

import java.util.List;

import rx.Observable;

public class LocusModel implements LocusContract.Model {

    @Override
    public Observable<TravelListBean> getTravelData(String deviceId, String startTime, String endTime) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("DeviceId",deviceId);
        params.add("StartTime",startTime);
        params.add("EndTime",endTime);
        return Api.getDefault(HostType.BASE_HOST).getTravelList(params.build())
                .compose(RxHelper.<TravelListBean>handleResult());
    }
}
