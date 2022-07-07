package com.ttce.vehiclemanage.ui.mine.presenter;

import com.jaydenxiao.common.baserx.RxSubscriber;
import com.ttce.vehiclemanage.bean.InstructDetailBean;
import com.ttce.vehiclemanage.bean.SystemMessageBean;
import com.ttce.vehiclemanage.ui.mine.constract.SystemMessageConstract;

import java.util.List;

/**
 * Created by Administrator on 2019/7/19.
 */

public class SystemMessagePresenter extends SystemMessageConstract.Presenter {
    @Override
    public void getMessageList(int Page) {
        mRxManage.add(mModel.getMessageList(Page).subscribe(new RxSubscriber<List<SystemMessageBean>>(mContext, false) {
            @Override
            protected void _onNext(List<SystemMessageBean> instructDetailBeans) {
                mView.returnData(instructDetailBeans);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
