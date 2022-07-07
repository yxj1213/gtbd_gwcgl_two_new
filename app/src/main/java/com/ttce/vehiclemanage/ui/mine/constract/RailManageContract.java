package com.ttce.vehiclemanage.ui.mine.constract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.FenceListBean;

import java.util.List;

import rx.Observable;

/**
 * 围栏管理
 * Created by hk on 2019/7/9.
 */

public interface RailManageContract {

    interface Model extends BaseModel {
        Observable<List<FenceListBean>> getNewFenceList(int Page);

        Observable<Boolean> delRail(String id);

        Observable<Boolean> addDevice(String id,String devices);
    }

    interface View extends BaseView {
        void getNewFenceList(List<FenceListBean> dataList);

        void delRail(String id,Boolean isDel);

        void addDevice(String id,Boolean isAdd);

    }

    abstract static class RailManagePresenter extends BasePresenter<View, Model> {
        public abstract void getNewFenceList(int Page);

        public abstract void delRail(String id);

        public abstract void addDevice(String id,String devices);
    }

}
