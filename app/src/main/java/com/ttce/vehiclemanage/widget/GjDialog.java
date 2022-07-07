package com.ttce.vehiclemanage.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.TravelItemBean;

public class GjDialog extends Dialog {

    private Context mContext;
    private TextView tvOne;
    private TextView tvOne1;
    private TextView tvTwo; // 剩余总距离
    private TextView tvThree;

    private TextView tvFour;
    private TextView tvFive;
    private TextView tvSix;

    private ImageView ivClose;

    private TravelItemBean travelItemBean;

    public GjDialog(Context context, TravelItemBean bean) {
        super(context);
        mContext = context;
        travelItemBean = bean;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater infalter = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = infalter.inflate(R.layout.gj_dialog, null);
        setContentView(layout);

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = 1000;
        lp.height = 800;
        window.setAttributes(lp);
        window.setGravity(Gravity.CENTER);

        tvOne = layout.findViewById(R.id.tv_one);
        tvOne1 = layout.findViewById(R.id.tv_one1);
        tvTwo = layout.findViewById(R.id.tv_two);
        tvThree = layout.findViewById(R.id.tv_three);

        tvFour = layout.findViewById(R.id.tv_four);
        tvFive = layout.findViewById(R.id.tv_five);
        tvSix = layout.findViewById(R.id.tv_six);

        ivClose = layout.findViewById(R.id.iv_close);


        tvOne.setText(travelItemBean.getGpsTimeFormat());
        tvOne1.setText(travelItemBean.getServiceTimeFormat());
        tvTwo.setText(travelItemBean.getAngleFormat());
        tvThree.setText(travelItemBean.getStatus()==0?"熄火":"启动"); //TODO  0"熄火" 1“启动”
        tvFour.setText( travelItemBean.getTotal()+ "km");//TODO  显示小数点后一位
        tvFive.setText(travelItemBean.getSpeed() + "km/h");

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

}