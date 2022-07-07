package com.ttce.vehiclemanage.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ttce.vehiclemanage.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/9/23.
 */

public class ListDialog extends Dialog {

    private RelativeLayout rlBottom;
    private Context context;
    private List<String> beanList = new ArrayList<>();

    private RecyclerView recyclerView;

    private ListAdapter mAdapter;

    public ListDialog(Context context, List<String> dicModelBeanList) {
        super(context, R.style.MyDialogStyles);
        this.context = context;
        this.beanList = dicModelBeanList;
//        Window window = getWindow();
//        window.getDecorView().setPadding(100, 200, 100, 200);
//        WindowManager.LayoutParams lp = window.getAttributes();
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        window.setAttributes(lp);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.select_nurse_type);

        rlBottom = findViewById(R.id.rl_bottom);
        rlBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        recyclerView = findViewById(R.id.recycleview);

        mAdapter = new ListAdapter(R.layout.adapter_select, beanList);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(mAdapter);
    }
}
