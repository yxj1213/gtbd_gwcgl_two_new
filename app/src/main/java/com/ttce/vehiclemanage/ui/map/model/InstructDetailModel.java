package com.ttce.vehiclemanage.ui.map.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.InstructDetailBean;
import com.ttce.vehiclemanage.ui.map.contract.InstructDetailContract;

import rx.Observable;

/**
 * Created by hk on 2019/7/5.
 */

public class InstructDetailModel implements InstructDetailContract.Model {

    @Override
    public Observable<InstructDetailBean> getDetail(String InstructId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("InstructId", InstructId);
        return Api.getDefault(HostType.BASE_HOST).getInstructDetail(params.build())
                .compose(RxHelper.<InstructDetailBean>handleResult());
    }

}
