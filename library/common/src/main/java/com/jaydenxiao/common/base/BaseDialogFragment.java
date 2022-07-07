package com.jaydenxiao.common.base;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;


public class BaseDialogFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,
                android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initDatas();
        installListeners();
    }

    public void initViews() {

    }

    public void initDatas() {

    }

    public void installListeners() {

    }

}
