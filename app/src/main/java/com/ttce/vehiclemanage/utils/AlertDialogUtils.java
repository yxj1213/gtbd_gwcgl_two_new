package com.ttce.vehiclemanage.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.ttce.vehiclemanage.R;

import static com.ttce.vehiclemanage.widget.SeekBar.Utils.dp2px;

/**
 * Created by Administrator on 2016/8/5.
 */
public class AlertDialogUtils {

    public static DialogDataListener mControlPanelListener;

    public interface DialogDataListener {
        void ondialogwc(String mtype,String neir1,String neir2);
    }

	/**
     * 弹出自定义样式的AlertDialog
     * @param context  上下文
     */
    public static void showAlertDialog(Context context, String message,int type, DialogDataListener controlPanelListener){
        AlertDialogUtils.mControlPanelListener=controlPanelListener;
        View view=LayoutInflater.from(context).inflate(R.layout.dialog_sub,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        WindowManager.LayoutParams  lp= alertDialog.getWindow().getAttributes();
        lp.width= DisplayUtil.getScreenWidth(context)-dp2px(context,50);//定义宽度
        lp.gravity = Gravity.CENTER;
        alertDialog.getWindow().setBackgroundDrawable(null);
        alertDialog.getWindow().setAttributes(lp);

        LinearLayout linearLayout=view.findViewById(R.id.lin_dialog_all);
        RelativeLayout rel_bhyy=view.findViewById(R.id.rel_bhyy);
        EditText edt_bh=view.findViewById(R.id.edt_bh);
        TextView txt_num=view.findViewById(R.id.txt_num);
        TextView txt=view.findViewById(R.id.txt);
        TextView tv_title=view.findViewById(R.id.tv_title);
        TextView txt_left=view.findViewById(R.id.txt_left);
        TextView txt_right=view.findViewById(R.id.txt_right);
        EditText edt=view.findViewById(R.id.edt);
        View views=(View) view.findViewById(R.id.view);
        linearLayout.setBackgroundResource(R.drawable.white_12_bg);

        switch (type){
            case 0:
                txt.setText(context.getString(R.string.dialog_ts1));
                txt_left.setVisibility(View.VISIBLE);
                views.setVisibility(View.VISIBLE);
                txt.setVisibility(View.VISIBLE);
                edt.setVisibility(View.GONE);
                txt_left.setText(context.getString(R.string.dialog_ts2));
                txt_right.setText(context.getString(R.string.dialog_ts3));
                txt_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                txt_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                break;
            case 1:
                txt.setText(message);
                txt_left.setVisibility(View.GONE);
                views.setVisibility(View.GONE);
                txt.setVisibility(View.VISIBLE);
                edt.setVisibility(View.GONE);
                txt_right.setText(context.getString(R.string.dialog_ts2));
                txt_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                break;
            case 2:
                txt.setText(message);
                txt_left.setVisibility(View.VISIBLE);
                views.setVisibility(View.VISIBLE);
                txt.setVisibility(View.VISIBLE);
                edt.setVisibility(View.GONE);

                txt_left.setText(context.getString(R.string.dialog_ts6));
                txt_right.setText(context.getString(R.string.dialog_ts7));
                txt_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                txt_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        controlPanelListener.ondialogwc(message,message,message);
                        alertDialog.dismiss();
                    }
                });
                break;
            case 3:
                tv_title.setText(context.getString(R.string.dialog_ts8));
                txt.setVisibility(View.GONE);
                edt.setVisibility(View.VISIBLE);
                txt_left.setVisibility(View.VISIBLE);
                views.setVisibility(View.VISIBLE);
                txt_left.setText(context.getString(R.string.dialog_ts6));
                txt_right.setText(context.getString(R.string.dialog_ts9));
                txt_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                txt_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        controlPanelListener.ondialogwc("111","111",edt.getText().toString().trim());
                    }
                });
                break;
            case 4:
                rel_bhyy.setVisibility(View.VISIBLE);
                tv_title.setText(context.getString(R.string.dialog_ts10));
                txt_left.setText(context.getString(R.string.dialog_ts6));
                txt_right.setText(context.getString(R.string.dialog_ts9));
                txt_left.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
                txt_right.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        controlPanelListener.ondialogwc(message,"111",edt_bh.getText().toString().trim());
                    }
                });
                //输入的文字
                edt_bh.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        int number=s.length();
                        //把剩余的文字赋值给textview
                        if(number<=100){
                            txt_num.setText(number+"/100字");
                        }
                    }
                });
                break;
        }

    }
}
