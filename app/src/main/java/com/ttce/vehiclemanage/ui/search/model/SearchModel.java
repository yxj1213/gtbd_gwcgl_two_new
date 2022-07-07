package com.ttce.vehiclemanage.ui.search.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.search.constract.SearchConstract;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class SearchModel implements SearchConstract.Model {
    @Override
    public Observable<List<MonitorResponseBean>> searchVehicles(String SearchKey) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("SearchKey", SearchKey);
        return Api.getDefault(HostType.BASE_HOST).searchVehicles(params.build())
                .compose(RxHelper.<List<MonitorResponseBean>>handleResult());
    }

    @Override
    public Observable<String[]> getSearchHistory() {
        return Observable.create(new Observable.OnSubscribe<String[]>() {
            @Override
            public void call(Subscriber<? super String[]> subscriber) {
                String historys = SPDefaultHelper.getString(SPDefaultHelper.SEARCH_RECORD);
                subscriber.onNext(historys.split(";"));
            }
        });
    }
}
