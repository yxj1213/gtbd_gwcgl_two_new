package com.ttce.vehiclemanage.utils;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jaydenxiao.common.baseapp.BaseApplication;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.AppApplication;

public class ToastUtil {
    private static Toast toast;
    public static void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        try {
            View toastRoot = LayoutInflater.from(AppApplication.getAppContext()).inflate(
                    R.layout.toast, null);
            TextView message = (TextView) toastRoot.findViewById(R.id.message);
            message.setText(msg);

//            if (toast == null) {
//                toast = new Toast(AppApplication.getAppContext());
//            }
            toast = new Toast(AppApplication.getAppContext());
            toast.setGravity(Gravity.CENTER, 0, 130);
            if (msg.length() > 10) {
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setView(toastRoot);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void showToast(String msg,int size) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        try {
            View toastRoot = LayoutInflater.from(AppApplication.getAppContext()).inflate(
                    R.layout.toast, null);
            TextView message = (TextView) toastRoot.findViewById(R.id.message);
            message.setText(msg);
            if(size==0){
                message.setTextSize(20);
            }else{
                message.setTextSize(size);
            }
//            if (toast == null) {
//                toast = new Toast(AppApplication.getAppContext());
//            }
            toast = new Toast(AppApplication.getAppContext());
            toast.setGravity(Gravity.CENTER, 0, 130);
            if (msg.length() > 10) {
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setView(toastRoot);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(SpannableString spanMsg, int position, int xP, int yP){
        if (TextUtils.isEmpty(spanMsg)) {
            return;
        }

        SpannableStringBuilder spb = new SpannableStringBuilder();
        spb.append(spanMsg);

        try {
            View toastRoot = LayoutInflater.from(AppApplication.getAppContext()).inflate(
                    R.layout.toast, null);
            TextView message = (TextView) toastRoot.findViewById(R.id.message);
            message.setText(spb);

//            if (toast == null) {
//                toast = new Toast(AppApplication.getAppContext());
//            }
            toast = new Toast(AppApplication.getAppContext());
            switch(position){
                case 0:
                    toast.setGravity(Gravity.LEFT, xP, yP);
                    break;
                case 1:
                    toast.setGravity(Gravity.TOP, xP, yP);
                    break;
                case 2:
                    toast.setGravity(Gravity.RIGHT, xP, yP);
                    break;
                case 3:
                    toast.setGravity(Gravity.BOTTOM, xP, yP);
                    break;
            }
            if (spanMsg.length() > 10) {
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setView(toastRoot);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(String msg,int position,int xP,int yP) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        try {
            View toastRoot = LayoutInflater.from(AppApplication.getAppContext()).inflate(
                    R.layout.toast, null);
            TextView message = (TextView) toastRoot.findViewById(R.id.message);
            message.setText(msg);

//            if (toast == null) {
//                toast = new Toast(AppApplication.getAppContext());
//            }
            toast = new Toast(AppApplication.getAppContext());
            switch(position){
                case 0:
                    toast.setGravity(Gravity.LEFT, xP, yP);
                    break;
                case 1:
                    toast.setGravity(Gravity.TOP, xP, yP);
                    break;
                case 2:
                    toast.setGravity(Gravity.RIGHT, xP, yP);
                    break;
                case 3:
                    toast.setGravity(Gravity.BOTTOM, xP, yP);
                    break;
            }
            if (msg.length() > 10) {
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setView(toastRoot);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToastByOffset(String msg,int offset) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }

        try {
            View toastRoot = LayoutInflater.from(AppApplication.getAppContext()).inflate(
                    R.layout.toast, null);
            TextView message = (TextView) toastRoot.findViewById(R.id.message);
            message.setText(msg);

//            if (toast == null) {
//                toast = new Toast(AppApplication.getAppContext());
//            }
            toast = new Toast(AppApplication.getAppContext());
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, offset);
            if (msg.length() > 10) {
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast.setDuration(Toast.LENGTH_SHORT);
            }
            toast.setView(toastRoot);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeToast() {
        if (toast != null) {
            toast.cancel();
        }
    }
}
