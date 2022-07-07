package com.ttce.vehiclemanage.ui.mine.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.SosBean;
import com.ttce.vehiclemanage.ui.mine.constract.SosListConstract;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/10.
 */

public class SosModel implements SosListConstract.Model {

    @Override
    public Observable<List<SosBean>> getSosList(int page) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("Page", page);
        params.add("PageSize", AppConstant.PAGESIZE);
        return Api.getDefault(HostType.BASE_HOST).getSosList(params.build())
                .compose(RxHelper.<List<SosBean>>handleResult());
    }

}
