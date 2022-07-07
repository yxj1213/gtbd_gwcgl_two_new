package com.ttce.vehiclemanage.ui.mine.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.SystemMessageBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/19.
 */

public interface SystemMessageConstract {

    interface Model extends BaseModel {
        Observable<List<SystemMessageBean>> getMessageList(int Page);
    }

    interface View extends BaseView {
        void returnData(List<SystemMessageBean> dataList);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getMessageList(int Page);
    }

}
