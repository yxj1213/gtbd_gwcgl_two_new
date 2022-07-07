package com.ttce.vehiclemanage.ui.mine.model;

import android.util.Log;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.InstructDetailBean;
import com.ttce.vehiclemanage.ui.mine.constract.InstructListConstract;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/12.
 */

public class InstructListModel implements InstructListConstract.Model {
    @Override
    public Observable<List<InstructDetailBean>> getInstrustList(String Type, String DeviceId, String PlateNumber, int Page) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Type", Type);
        params.add("DeviceId", DeviceId);
        params.add("PlateNumber", PlateNumber);
        params.add("Page", Page);
        params.add("PageSize", AppConstant.PAGESIZE);
        return Api.getDefault(HostType.BASE_HOST).getInstructList(params.build())
                .compose(RxHelper.<List<InstructDetailBean>>handleResult());
    }
}

