package com.ttce.vehiclemanage.ui.locus.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.TravelItemBean;
import com.ttce.vehiclemanage.ui.locus.contract.TrackContract;

import java.util.List;

import rx.Observable;

public class TrackModel implements TrackContract.Model {

    @Override
    public Observable<TravelItemBean> getTrackData(String deviceId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("DeviceId",deviceId);
        return Api.getDefault(HostType.BASE_HOST).getTrackInfo(params.build())
                .compose(RxHelper.<TravelItemBean>handleResult());
    }

    @Override
    public Observable<List<CompanyItemBean>> getDeptData() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).getCompanyList(params.build())
                .compose(RxHelper.<List<CompanyItemBean>>handleResult());
    }
}
