package com.jaydenxiao.common.baserx;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.jaydenxiao.common.R;
import com.jaydenxiao.common.baseapp.BaseApplication;
import com.jaydenxiao.common.commonutils.NetWorkUtils;
import com.jaydenxiao.common.commonwidget.LoadingDialog;
import com.jaydenxiao.common.security.LoginUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import okhttp3.ResponseBody;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * des:订阅封装
 * Created by xsf
 * on 2016.09.10:16
 */

/********************使用例子********************/
/*_apiService.login(mobile, verifyCode)
        .//省略
        .subscribe(new RxSubscriber<User user>(mContext,false) {
@Override
public void _onNext(User user) {
        // 处理user
        }

@Override
public void _onError(String msg) {
        ToastUtil.showShort(mActivity, msg);
        });*/
public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public RxSubscriber(Context context) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading), true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading), showDialog);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onCompleted() {
            if (showDialog&&!((Activity)mContext).isDestroyed()&&!((Activity)mContext).isFinishing())
                LoadingDialog.cancelDialogForLoading();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onStart() {
        super.onStart();
        if (showDialog&&!((Activity)mContext).isDestroyed()&&!((Activity)mContext).isFinishing()) {

            try {
                LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
            } catch (Exception e) {
                LoadingDialog.cancelDialogForLoading();
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onError(Throwable e) {

        if (showDialog&&!((Activity)mContext).isDestroyed()&&!((Activity)mContext).isFinishing())
            LoadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            _onError(BaseApplication.getAppContext().getString(R.string.no_net));
        }
        Log.e("错误信息：", "onFailure: ==="+e);
        //服务器
         if (e instanceof ServerException) {
            _onError(e.getMessage());
        }else if (e instanceof LoginException) {
            _onLogin(e.getMessage());
        }else if (e instanceof ConnectException) {
             if (NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                 if(LoginUtil.isConnet==0){
                     _onError(BaseApplication.getAppContext().getString(R.string.error_isconnet));
                 }else{
                     _onError(mContext.getResources().getString(R.string.error_ip));
                 }
             }
         }else if (e instanceof SocketTimeoutException) {
             if (NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
                 if(LoginUtil.isConnet==0){
                     _onError(BaseApplication.getAppContext().getString(R.string.error_isconnet));
                 }else{
                     if(e.getMessage().trim().contains("timeout")){
                         _onError(mContext.getResources().getString(R.string.error_jiekou_networktimeout));
                     }else{
                         _onError(mContext.getResources().getString(R.string.error_networktimeout));
                     }
                 }
             }

         }else if (e instanceof HttpException) {
             Log.e("错误信息：", "onFailure: ===");
             ResponseBody body = ((HttpException) e).response().errorBody();
             int code = ((HttpException) e).response().code();
             try {
                 Log.e("错误信息：", "onFailure: "+body.string()+"------"+code);
             } catch (IOException e1) {
                 Log.e("错误信息：", "111onFailure: "+e.getMessage());
                 e1.printStackTrace();
             }

             if(LoginUtil.isConnet==0){
                 _onError(BaseApplication.getAppContext().getString(R.string.error_isconnet));
             }else{
                 _onError(BaseApplication.getAppContext().getString(R.string.other_error));
             }
//             switch (codeFirstNum){
//                 case 4:
//                     _onError(BaseApplication.getAppContext().getString(R.string.other_error));
//                     break;
//                 case 5:
//                     _onError(BaseApplication.getAppContext().getString(R.string.other_error));
//                     break;
//                 default:
//                     _onError(BaseApplication.getAppContext().getString(R.string.other_error));
//                     break;
//             }

         }
    }
    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

    protected void _onLogin(String message) {
        _onError(message);
//        Intent intent = new Intent();
//        intent.setAction("com.ttce.vehiclemanage.login");
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
        EventBus.getDefault().post(new LoginException(message));
    }

}
