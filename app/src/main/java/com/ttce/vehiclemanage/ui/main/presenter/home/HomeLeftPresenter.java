package com.ttce.vehiclemanage.ui.main.presenter.home;

import android.util.Log;

import com.google.gson.JsonObject;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.bean.ChangeCompanySaveReturnBean;
import com.ttce.vehiclemanage.bean.CompanyTypeListBean;
import com.ttce.vehiclemanage.ui.main.contract.home.HomeLeftContract;

import java.util.List;

/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class HomeLeftPresenter extends HomeLeftContract.Presenter {
    @Override
    public void getcompanyTypeListPresenter() {
        mRxManage.add(mModel.companyTypeListModel().subscribe(new RxSubscriber<List<CompanyTypeListBean>>(mContext) {
            @Override
            protected void _onNext(List<CompanyTypeListBean> companyTypeListBeans) {
                mView.returnCompanyTypeList(companyTypeListBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void getbusinessComPanyAddModelPresenter(String CompanyName, String ContactPerson, String ContactPhone, String ContactEmail, String CompanyType) {
        mRxManage.add(mModel.businessComPanyAddModel(CompanyName,ContactPerson,ContactPhone,ContactEmail,CompanyType).subscribe(new RxSubscriber<ChangeCompanyBean>(mContext) {
            @Override
            protected void _onNext(ChangeCompanyBean changeCompanyBean) {
                mView.returnbusinessComPanyAdd(changeCompanyBean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void getSaveChangeCompany(String companyId) {
        mRxManage.add(mModel.getSaveChangeCompany(companyId).subscribe(new RxSubscriber<ChangeCompanySaveReturnBean>(mContext) {
            @Override
            protected void _onNext(ChangeCompanySaveReturnBean code) {
                mView.getSaveChangeCompanys(code);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void PostDepartmentAddPresenter(String CompanyId, String Name) {
        mRxManage.add(mModel.PostDepartmentAddModel(CompanyId,Name).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String str) {
                mView.returnbusinessComPanyAdd(str);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

}
