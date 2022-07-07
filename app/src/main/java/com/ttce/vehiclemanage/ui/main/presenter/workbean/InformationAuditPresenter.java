package com.ttce.vehiclemanage.ui.main.presenter.workbean;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.InformationAuditBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.main.contract.VehicalMonitoringContract;
import com.ttce.vehiclemanage.ui.main.contract.workbean.InformationAuditContract;

import java.util.List;

/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class InformationAuditPresenter extends InformationAuditContract.Presenter {

    @Override
    public void getBusinessStaffCheckList(String State,int page,String StartTime,String EndTime,String KeyWord,String IDNumber) {
        mRxManage.add(mModel.getBusinessStaffCheckListModel(State,page, StartTime, EndTime, KeyWord, IDNumber).subscribe(new RxSubscriber<List<InformationAuditBean>>(mContext) {
            @Override
            protected void _onNext(List<InformationAuditBean> monitorResponseBeanList) {
                mView.returnBusinessStaffCheckList(monitorResponseBeanList);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

}
