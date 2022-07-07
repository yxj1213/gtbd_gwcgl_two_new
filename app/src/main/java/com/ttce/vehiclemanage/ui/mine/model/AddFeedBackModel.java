package com.ttce.vehiclemanage.ui.mine.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.ui.mine.constract.AddFeedBackConstract;

import rx.Observable;

/**
 * Created by hk on 2019/7/10.
 */

public class AddFeedBackModel implements AddFeedBackConstract.Model {
    @Override
    public Observable<String> addFeedBack(String ContentType, String Content, String LinkMan, String LinkPhone, String CompanyId) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("ContentType", ContentType);
        params.add("Content", Content);
        params.add("LinkMan", LinkMan);
        params.add("LinkPhone", LinkPhone);
        params.add("CompanyId", CompanyId);
        return Api.getDefault(HostType.BASE_HOST).addFeedBack(params.build())
                .compose(RxHelper.<String>handleResult());
    }
}
