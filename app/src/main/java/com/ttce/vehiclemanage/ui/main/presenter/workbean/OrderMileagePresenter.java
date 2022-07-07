package com.ttce.vehiclemanage.ui.main.presenter.workbean;

import com.google.gson.JsonArray;
import com.jaydenxiao.common.baserx.RxSubscriber;
import com.ttce.vehiclemanage.ui.main.contract.workbean.OrderMileageContract;



public class OrderMileagePresenter extends OrderMileageContract.Presenter {

    @Override
    public void getCarFlowOrder_statePresenter(String type,String OrderId, String Mileage,JsonArray CarFlow_Order_Mileage_Image_List) {
        mRxManage.add(mModel.getCarFlowOrder_stateModel(type,OrderId, Mileage, CarFlow_Order_Mileage_Image_List).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String str) {
                mView.returnCarFlowOrder_stateView(type);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
    @Override
    public void getOrderMarkAddPresenter(String type,String OrderId, String NeedMarkTitle, String RealMarkFullAddress, String RealMarkLng, String RealMarkLat,String RemarkTitle, String RemarkContent, String MarkLevel, String MarkType, String CarFlow_Order_MarkId, JsonArray MarkImgUrlList,boolean ismark,String NeedMarkSimpleAddress,String NeedMarkFullAddress,String NeedMarkLng,String NeedMarkLat,String LinkName,String LinkPhone,boolean IsFinishMark) {
        mRxManage.add(mModel.getOrderMarkAddModel( OrderId,  NeedMarkTitle,  RealMarkFullAddress,  RealMarkLng,  RealMarkLat, RemarkTitle,  RemarkContent,  MarkLevel,  MarkType,  CarFlow_Order_MarkId,  MarkImgUrlList, ismark, NeedMarkSimpleAddress, NeedMarkFullAddress, NeedMarkLng, NeedMarkLat, LinkName, LinkPhone, IsFinishMark).subscribe(new RxSubscriber<String>(mContext) {
            @Override
            protected void _onNext(String str) {
                mView.returnOrderMarkAddBean(type);
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}

