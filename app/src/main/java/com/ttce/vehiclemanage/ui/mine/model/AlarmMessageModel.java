package com.ttce.vehiclemanage.ui.mine.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.AlarmListBean;
import com.ttce.vehiclemanage.ui.mine.constract.AlarmMessageConstract;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/19.
 */

public class AlarmMessageModel implements AlarmMessageConstract.Model {
    @Override
    public Observable<List<AlarmListBean>> getAlarmList(int type, String DeviceId, int Page) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("type", type)
                .add("DeviceId", DeviceId)
                .add("Page", Page)
                .add("PageSize", AppConstant.PAGESIZE);
        return Api.getDefault(HostType.BASE_HOST).getAlarmList(params.build())
                .compose(RxHelper.<List<AlarmListBean>>handleResult());
    }
}
