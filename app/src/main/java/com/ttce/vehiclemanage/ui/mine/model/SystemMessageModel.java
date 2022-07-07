package com.ttce.vehiclemanage.ui.mine.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.InstructDetailBean;
import com.ttce.vehiclemanage.bean.SystemMessageBean;
import com.ttce.vehiclemanage.ui.mine.constract.SystemMessageConstract;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/19.
 */

public class SystemMessageModel implements SystemMessageConstract.Model {
    @Override
    public Observable<List<SystemMessageBean>> getMessageList(int Page) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Page", Page);
        params.add("PageSize", AppConstant.PAGESIZE);
        return Api.getDefault(HostType.BASE_HOST).getSystemMessageList(params.build())
                .compose(RxHelper.<List<SystemMessageBean>>handleResult());
    }
}
