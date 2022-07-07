package com.ttce.vehiclemanage.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.widget.linearlayout.ShadowLayout;

import static com.ttce.vehiclemanage.widget.SeekBar.Utils.dp2px;

/**
 * Created by Administrator on 2016/8/5.
 */
public class NoPerson_NoCreatebusinessAlertDialogUtils {

    public static DialogDataListener mControlPanelListener;

    public interface DialogDataListener {
        void ondialogwc(String mtype);
    }

	/**
     * 弹出自定义样式的AlertDialog
     * @param context  上下文
     */
    public static void showAlertDialog(Context context,String type, DialogDataListener controlPanelListener){
        NoPerson_NoCreatebusinessAlertDialogUtils.mControlPanelListener=controlPanelListener;
        View view=LayoutInflater.from(context).inflate(R.layout.dialog_needcar_noperson_nocreatebusiness,null);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        alertDialog.show();
        WindowManager.LayoutParams  lp= alertDialog.getWindow().getAttributes();
        lp.width= DisplayUtil.getScreenWidth(context)-dp2px(context,36);//定义宽度
//        lp.height= DisplayUtil.getScreenWidth(context)-UiUtils.dp2px(context,36);//定义宽度
        lp.gravity = Gravity.CENTER;
        alertDialog.getWindow().setBackgroundDrawable(null);
        alertDialog.getWindow().setAttributes(lp);


        TextView tv_dismiss=view.findViewById(R.id.tv_dismiss);
        TextView tv_next=view.findViewById(R.id.tv_next);
        TextView tv_qy_1=view.findViewById(R.id.tv_qy_1);
        TextView txt_title=view.findViewById(R.id.txt_title);
        TextView txt_message=view.findViewById(R.id.txt_message);
        ShadowLayout tv_bottom=view.findViewById(R.id.tv_bottom);
        LinearLayout lin_no_createbusiness=view.findViewById(R.id.lin_no_createbusiness);
        LinearLayout lin_no_person=view.findViewById(R.id.lin_no_person);
        LinearLayout lin_bottom=view.findViewById(R.id.lin_bottom);
        FrameLayout fra=view.findViewById(R.id.fra);
        /**
         * 0  待完善
         * 1 审核中
         * */
        switch (type){
            case "未完善员工信息未创建流程":
                tv_qy_1.setText("该企业无用车流程,请联系管理员.");
                break;
            case "已完善员工信息未创建流程":
                tv_qy_1.setText("该企业无用车流程,请联系管理员.");
                lin_no_person.setVisibility(View.GONE);
                tv_bottom.setVisibility(View.GONE);
                tv_dismiss.setGravity(Gravity.CENTER);
                break;
            case "有流程未完善员工信息":
                lin_no_createbusiness.setVisibility(View.GONE);
                lin_no_person.setVisibility(View.VISIBLE);
                break;
            case "个人":
                break;
            case "个人未实名":
                txt_title.setText("欢迎使用“公务用车管理”");
                txt_message.setText("为了方便您使用“用车管理”相关功能，请先完善您的身份信息。");
                txt_message.setTextSize(13);
                tv_next.setText("实名认证");
                lin_no_createbusiness.setVisibility(View.GONE);
                lin_no_person.setVisibility(View.GONE);
                fra.setBackgroundResource(R.mipmap.main_no_sm);
                txt_title.setGravity(Gravity.CENTER);
                txt_message.setGravity(Gravity.CENTER);
                break;
            case "有进行中的订单":
                txt_title.setText("提示");
                txt_message.setText("当前您有正在进行中的订单，暂无法使用此功能。");
                txt_message.setTextSize(13);
                tv_next.setText("查看订单");
                tv_dismiss.setText("回首页");
                lin_no_createbusiness.setVisibility(View.GONE);
                lin_no_person.setVisibility(View.GONE);
                fra.setBackgroundResource(R.mipmap.main_no_sm);
                txt_title.setGravity(Gravity.CENTER);
                txt_message.setGravity(Gravity.CENTER);
                break;
        }
        tv_dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlPanelListener.ondialogwc("消失");
                alertDialog.dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlPanelListener.ondialogwc(type);
                alertDialog.dismiss();
            }
        });
    }
}
