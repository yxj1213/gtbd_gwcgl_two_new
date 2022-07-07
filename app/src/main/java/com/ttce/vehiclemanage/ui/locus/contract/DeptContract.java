package com.ttce.vehiclemanage.ui.locus.contract;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.widget.tree.Dept;

import java.util.List;

import rx.Observable;

public interface DeptContract {

    interface Model extends BaseModel {
        Observable<List<CompanyItemBean>> getDeptData();
    }

    interface View extends BaseView {
        void buildDeptTree(List<CompanyItemBean> deptListData);
    }

    abstract static class Presenter extends BasePresenter<DeptContract.View, DeptContract.Model> {
        public abstract void getDeptData();
    }

}
