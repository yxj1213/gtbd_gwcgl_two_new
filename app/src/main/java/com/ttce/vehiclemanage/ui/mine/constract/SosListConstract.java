package com.ttce.vehiclemanage.ui.mine.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.SosBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/10.
 */

public interface SosListConstract {

    interface Model extends BaseModel {
        Observable<List<SosBean>> getSosList(int page);
    }

    interface View extends BaseView {
        void returnList(List<SosBean> sosBeans);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getSosList(int Page);
    }

}
