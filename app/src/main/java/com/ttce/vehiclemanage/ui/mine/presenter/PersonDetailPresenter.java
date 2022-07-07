package com.ttce.vehiclemanage.ui.mine.presenter;


import com.google.gson.JsonObject;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.AlipayBean;
import com.ttce.vehiclemanage.bean.DaiBanListBean;
import com.ttce.vehiclemanage.bean.DepartmentByCompanyIdBean;
import com.ttce.vehiclemanage.bean.DictTypeListBean;
import com.ttce.vehiclemanage.bean.HomeOrderBean;
import com.ttce.vehiclemanage.bean.IdCareBean;
import com.ttce.vehiclemanage.bean.IsHasWorkBeanchBean;
import com.ttce.vehiclemanage.bean.Message2Bean;
import com.ttce.vehiclemanage.bean.MessageBean;
import com.ttce.vehiclemanage.bean.NewUserInfoBean;
import com.ttce.vehiclemanage.bean.UserInfoBean;
import com.ttce.vehiclemanage.ui.mine.constract.PersonDetailConstract;
import com.ttce.vehiclemanage.utils.ToastUtil;

import java.util.List;


/**
 * Created by hk on 2019/7/12.
 */

public class PersonDetailPresenter extends PersonDetailConstract.Presenter {
    @Override
    public void CarFlowOrder_Index_StatisticsPresenter(String DateTimeType) {
        mRxManage.add(mModel.CarFlowOrder_Index_StatisticsModel(DateTimeType).subscribe(new RxSubscriber<HomeOrderBean>(mContext) {
            @Override
            protected void _onNext(HomeOrderBean homeOrderBean) {
                mView.returnCarFlowOrder_Index_Statistics(homeOrderBean);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void getUserInfo() {
        mRxManage.add(mModel.getUserInfo().subscribe(new RxSubscriber<UserInfoBean>(mContext) {
            @Override
            protected void _onNext(UserInfoBean userInfoBean) {
                mView.getUserInfo(userInfoBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void uploadImg(String filepath,String fileName,String type) {
        mRxManage.add(mModel.uploadImg(filepath,fileName,type).subscribe(new RxSubscriber<String>(mContext, false) {
            @Override
            protected void _onNext(String uploadImgResponseBean) {
                mView.getImgUrl(uploadImgResponseBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void IsHasPrivilegePresenter(String OrderModule, int position,String type) {
        mRxManage.add(mModel.IsHasPrivilegeModel(OrderModule).subscribe(new RxSubscriber<IsHasWorkBeanchBean>(mContext, false) {
            @Override
            protected void _onNext(IsHasWorkBeanchBean uploadImgResponseBean) {
                mView.returnIsHasPrivilege(uploadImgResponseBean,position,type);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void StaffMagStatePresenter() {
        mRxManage.add(mModel.StaffMagStateModel().subscribe(new RxSubscriber<Message2Bean>(mContext, false) {
            @Override
            protected void _onNext(Message2Bean messageBean) {
                mView.returnStaffMagState(messageBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void getBusinessStaffPresenter(String staffid) {
        mRxManage.add(mModel.getBusinessStaffModel( staffid).subscribe(new RxSubscriber<NewUserInfoBean>(mContext, false) {
            @Override
            protected void _onNext(NewUserInfoBean messageBean) {
                mView.returnBusinessStaff(messageBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }
    @Override
    public void BusinessStaffAddPresenter(String type,String staffid,String PositionId,String DepartmentId,String UserId,String xb,String nl,String rzrq,String jjlxr,String jjlxdh,String sfcyjz,String xzz,String ygbhCode) {
        mRxManage.add(mModel.BusinessStaffAddModel( type, staffid,PositionId, DepartmentId, UserId, xb, nl, rzrq,jjlxr, jjlxdh, sfcyjz, xzz, ygbhCode).subscribe(new RxSubscriber<String>(mContext, false) {
            @Override
            protected void _onNext(String messageBean) {
                mView.returnBusinessStaffAdd(messageBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void PostDictTypeListPresenter() {
        mRxManage.add(mModel.PostDictTypeListModel().subscribe(new RxSubscriber<List<DictTypeListBean>>(mContext, false) {
            @Override
            protected void _onNext(List<DictTypeListBean>  messageBean) {
                mView.returnPostDictTypeList(messageBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void PostDepartmentByCompanyIdPresenter() {
        mRxManage.add(mModel.PostDepartmentByCompanyIdModel().subscribe(new RxSubscriber<List<DepartmentByCompanyIdBean>>(mContext, false) {
            @Override
            protected void _onNext(List<DepartmentByCompanyIdBean>  messageBean) {
                mView.returnPostDepartmentByCompanyId(messageBean);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void CarFlowOrderNeedToDoPresenter() {
        mRxManage.add(mModel.CarFlowOrderNeedToDoModel().subscribe(new RxSubscriber<List<DaiBanListBean>>(mContext, false) {
            @Override
            protected void _onNext(List<DaiBanListBean>  daiBanListBeans) {
                mView.returnCarFlowOrderNeedToDo(daiBanListBeans);
            }

            @Override
            protected void _onError(String message) {
                ToastUitl.showToastWithImg(message, R.drawable.ic_wrong);
            }
        }));
    }

    @Override
    public void PostPreconsultPresenter(JsonObject jsonObject) {
        mRxManage.add(mModel.PostPreconsultModel(jsonObject).subscribe(new RxSubscriber<AlipayBean>(mContext) {
            @Override
            protected void _onNext(AlipayBean str) {
                mView.returnPostPreconsult(str);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }

    @Override
    public void PostConsultPresenter(String verify_id, String auth_code,JsonObject jsonObject) {
        mRxManage.add(mModel.PostConsultModel(verify_id,auth_code,jsonObject).subscribe(new RxSubscriber<IdCareBean>(mContext) {
            @Override
            protected void _onNext(IdCareBean str) {
                mView.returnPostConsult(str);
            }

            @Override
            protected void _onError(String message) {
                mView.returnErrPostConsult(message);
            }
        }));
    }
}
