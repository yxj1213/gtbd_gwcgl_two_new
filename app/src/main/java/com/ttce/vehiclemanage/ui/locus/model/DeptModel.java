package com.ttce.vehiclemanage.ui.locus.model;

import com.jaydenxiao.common.baserx.RxHelper;
import com.ttce.vehiclemanage.api.Api;
import com.ttce.vehiclemanage.api.HostType;
import com.ttce.vehiclemanage.api.StringBodyParamBuilder;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.bean.TravelListBean;
import com.ttce.vehiclemanage.ui.locus.contract.DeptContract;
import com.ttce.vehiclemanage.ui.locus.contract.LocusContract;
import com.ttce.vehiclemanage.widget.tree.Dept;

import java.util.List;

import rx.Observable;

public class DeptModel implements DeptContract.Model {

    @Override
    public Observable<List<CompanyItemBean>> getDeptData() {
        StringBodyParamBuilder params = StringBodyParamBuilder.create();
        return Api.getDefault(HostType.BASE_HOST).getCompanyList(params.build())
                .compose(RxHelper.<List<CompanyItemBean>>handleResult());
    }
}
