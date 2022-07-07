package com.ttce.vehiclemanage.ui.mine.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.ExSysNoticeSetBean;
import com.ttce.vehiclemanage.ui.mine.constract.MessageSetConstract;

import rx.Observable;

/**
 * Created by hk on 2019/7/11.
 */

public class MessageSetModel implements MessageSetConstract.Model {

    @Override
    public Observable<ExSysNoticeSetBean> getMessageSet() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).getNoticeDetail(params.build())
                .compose(RxHelper.<ExSysNoticeSetBean>handleResult());
    }

    @Override
    public Observable<String> updateMeassage(String NoticeKey, int NoticeValue) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("NoticeKey", NoticeKey);
        params.add("NoticeValue", NoticeValue);
        return Api.getDefault(HostType.BASE_HOST).updateNoticeDetail(params.build())
                .compose(RxHelper.<String>handleResult());
    }


}
