package com.ttce.vehiclemanage.ui.mine.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.ui.mine.constract.AddSosConstract;

import java.util.stream.Stream;

import rx.Observable;

/**
 * Created by hk on 2019/7/10.
 */

public class AddSosModel implements AddSosConstract.Model {
    @Override
    public Observable<String> addSos(String Title, String Content, String LinkMan, String LinkPhone, String Password, String CompanyId,String Address) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Title", Title);
        params.add("Content", Content);
        params.add("LinkMan", LinkMan);
        params.add("LinkPhone", LinkPhone);
        params.add("Password", Password);
        //params.add("CompanyId", CompanyId);
        params.add("Address", Address);
        return Api.getDefault(HostType.BASE_HOST).addSos(params.build())
                .compose(RxHelper.<String>handleResult());
    }
}
