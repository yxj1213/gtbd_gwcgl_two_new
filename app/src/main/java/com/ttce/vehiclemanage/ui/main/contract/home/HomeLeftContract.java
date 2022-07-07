package com.ttce.vehiclemanage.ui.main.contract.home;

import com.google.gson.JsonObject;
import com.jaydenxiao.common.base.BaseModel;
import com.jaydenxiao.common.base.BasePresenter;
import com.jaydenxiao.common.base.BaseView;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CompanyTypeListBean;

import java.util.List;

import rx.Observable;

/**
 * Created by hk on 2019/7/3.
 */

public interface HomeLeftContract {

    interface Model extends BaseModel {
        Observable<List<CompanyTypeListBean>> companyTypeListModel();
        Observable<ChangeCompanyBean> businessComPanyAddModel(String CompanyName,String ContactPerson,String ContactPhone,String ContactEmail,String CompanyType);
        Observable<String> PostDepartmentAddModel(String CompanyId,String Name);
        Observable<ChangeCompanySaveReturnBean> getSaveChangeCompany(String companyId);
    }

    interface View extends BaseView {
        void returnCompanyTypeList(List<CompanyTypeListBean> companyTypeListBeans);
        void returnbusinessComPanyAdd(String str);
        void getSaveChangeCompanys(ChangeCompanySaveReturnBean str);
        void returnbusinessComPanyAdd(ChangeCompanyBean str);
    }

    abstract static class Presenter extends BasePresenter<View, Model> {
        public abstract void getcompanyTypeListPresenter();
        public abstract void getbusinessComPanyAddModelPresenter(String CompanyName,String ContactPerson,String ContactPhone,String ContactEmail,String CompanyType);
        public abstract void PostDepartmentAddPresenter(String CompanyId,String Name);
        public abstract void getSaveChangeCompany(String companyId);
    }
}
