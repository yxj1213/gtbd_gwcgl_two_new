package com.ttce.vehiclemanage.ui.main.contract.workbean;

import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.InformationAuditBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/3.
 */

public interface InformationAuditContract {

    interface Model extends BaseModel {
        Observable<List<InformationAuditBean>> getBusinessStaffCheckListModel(String State,int page,String StartTime,String EndTime,String KeyWord,String IDNumber);
    }

    interface View extends BaseView {
        void returnBusinessStaffCheckList(List<InformationAuditBean> monitorResponseBeanList);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getBusinessStaffCheckList(String State,int page,String StartTime,String EndTime,String KeyWord,String IDNumber);
    }
}
