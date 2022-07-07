package com.ttce.vehiclemanage.ui.usermanage.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jaydenxiao.common.base.BaseDialogFragment;
import com.ttce.vehiclemanage.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisteDialog extends BaseDialogFragment {

    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.tv_content)
    TextView tvContent;
    @Bind(R.id.cb_select)
    CheckBox ivSelect;
    @Bind(R.id.tv_comfirm1)
    TextView tv_comfirm1;

    private boolean isSure;
    private SureListener sureListener;

    public static RegisteDialog newInstance() {
        Bundle args = new Bundle();
        RegisteDialog fragment = new RegisteDialog();
        args.putBoolean("isSure", false);
        fragment.setArguments(args);
        return fragment;
    }

    public void setSureListener(SureListener sureListener) {
        this.sureListener = sureListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_registe, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initDatas() {
        super.initDatas();
        isSure = getArguments().getBoolean("isSure");
        ivSelect.setChecked(isSure);
        ivSelect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    tv_comfirm1.setBackgroundResource(R.drawable.blue_round_tv_bg);
                } else {
                    tv_comfirm1.setBackgroundResource(R.drawable.gray30_round_tv_bg);
                }
            }
        });
    }

    @OnClick({R.id.tv_comfirm1, R.id.img_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_comfirm1:
                if (ivSelect.isChecked()) {
                    sureListener.sure();
                } else {
                    return;
                }
                dismissAllowingStateLoss();
                break;
            case R.id.img_close:
                dismissAllowingStateLoss();
                break;
        }
    }

    public interface SureListener {
        void sure();
    }
}
