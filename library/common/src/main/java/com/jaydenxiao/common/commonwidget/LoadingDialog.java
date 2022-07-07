package com.jaydenxiao.common.commonwidget;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.jaydenxiao.common.R;


/**
 * description:弹窗浮动加载进度条
 * Created by xsf
 * on 2016.07.17:22
 */
public class LoadingDialog {
    /**
     * 加载数据对话框
     */
    private static Dialog mLoadingDialog;
    private static Activity mcontext;

    /**
     * 显示加载对话框
     *
     * @param context    上下文
     * @param msg        对话框显示内容
     * @param cancelable 对话框是否可以取消
     */
    public static Dialog showDialogForLoading(Activity context, String msg, boolean cancelable) {
        mcontext=context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        TextView loadingText = (TextView) view.findViewById(R.id.id_tv_loading_dialog_text);
        loadingText.setText(msg);

        if (mLoadingDialog != null) {
            return mLoadingDialog;
        }
        mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
        mLoadingDialog.setCancelable(cancelable);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        if(mcontext!=null&&!mcontext.isFinishing()){
            mLoadingDialog.show();
        }
        return mLoadingDialog;
    }

    public static Dialog showDialogForLoading(Activity context) {
        mcontext=context;
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_loading, null);
        TextView loadingText = (TextView) view.findViewById(R.id.id_tv_loading_dialog_text);
        loadingText.setText("加载中...");

//        if (mLoadingDialog == null) {
        mLoadingDialog = new Dialog(context, R.style.CustomProgressDialog);
//        }
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setCanceledOnTouchOutside(false);
        mLoadingDialog.setContentView(view, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        if(mcontext!=null&&!mcontext.isFinishing()){
            mLoadingDialog.show();
        }
        return mLoadingDialog;
    }

    /**
     * 关闭加载对话框
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void cancelDialogForLoading() {
        if(mcontext!=null&&!mcontext.isFinishing()&&!mcontext.isDestroyed()){
            if (mLoadingDialog != null&&mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
        }
    }
}
