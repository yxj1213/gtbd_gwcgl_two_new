package com.ttce.vehiclemanage.ui.mine.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.ExSysNoticeSetBean;

import rx.Observable;


/**
 * Created by hk on 2019/7/10.
 */

public interface MessageSetConstract {

    interface Model extends BaseModel {
        Observable<ExSysNoticeSetBean> getMessageSet();

        Observable<String> updateMeassage(String NoticeKey, int NoticeValue);
    }

    interface View extends BaseView {
        void getMessageSet(ExSysNoticeSetBean exSysNoticeSetBean);

        void updateMessage(String message);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getMessageSet();

        public abstract void updateMessage(String NoticeKey, int NoticeValue);
    }

}
