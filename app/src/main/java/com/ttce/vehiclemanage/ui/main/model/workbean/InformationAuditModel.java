package com.ttce.vehiclemanage.ui.main.model.workbean;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.app.AppConstant;
import com.ttce.vehiclemanage.bean.InformationAuditBean;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.main.contract.workbean.InformationAuditContract;
import com.ttce.vehiclemanage.ui.usermanage.manager.UserManager;

import java.util.List;

import rx.Observable;

/**
 * 车辆管理
 * Created by hk on 2019/7/3.
 */

public class InformationAuditModel implements InformationAuditContract.Model {
    @Override
    public Observable<List<InformationAuditBean>> getBusinessStaffCheckListModel(String State, int page,String StartTime,String EndTime,String KeyWord,String IDNumber) {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        params.add("State",State);
        params.add("PageIndex", page);
        params.add("PageSize", AppConstant.PAGESIZE);
        params.add("StartTime",StartTime);
        params.add("EndTime",EndTime);
        params.add("KeyWord",KeyWord);
        params.add("IDNumber",IDNumber);
        return Api.getDefault(HostType.BASE_HOST).BusinessStaffCheckList(params.build())
                .compose(RxHelper.<List<InformationAuditBean>>handleResult());
    }
}
