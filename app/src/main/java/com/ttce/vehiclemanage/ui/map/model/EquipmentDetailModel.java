package com.ttce.vehiclemanage.ui.map.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.EquipmentDetailBean;
import com.ttce.vehiclemanage.ui.map.contract.EquipmentDetailContract;

import rx.Observable;

/**
 * Created by hk on 2019/7/5.
 */

public class EquipmentDetailModel implements EquipmentDetailContract.Model {
    @Override
    public Observable<EquipmentDetailBean> getDatail(String DeviceId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("DeviceId", DeviceId);
        return Api.getDefault(HostType.BASE_HOST).getEquipmentDetail(params.build())
                .compose(RxHelper.<EquipmentDetailBean>handleResult());
    }
}
