package com.ttce.vehiclemanage.ui.map.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.EquipmentDetailBean;
import com.ttce.vehiclemanage.ui.map.contract.EquipmentDetailContract;

/**
 * Created by Administrator on 2019/7/5.
 */

public class EquipmentDetailPresenter extends EquipmentDetailContract.EquipmentPresenter {
    @Override
    public void getDatail(String DeviceId) {
        mRxManage.add(mModel.getDatail(DeviceId).subscribe(new RxSubscriber<EquipmentDetailBean>(mContext) {
            @Override
            protected void _onNext(EquipmentDetailBean equipmentDetailBean) {
                mView.returnDatail(equipmentDetailBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
}
