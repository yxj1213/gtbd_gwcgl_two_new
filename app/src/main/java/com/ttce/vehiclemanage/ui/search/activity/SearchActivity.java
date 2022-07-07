package com.ttce.vehiclemanage.ui.search.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonutils.ToastUitl;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.app.SPDefaultHelper;
import com.ttce.vehiclemanage.bean.MonitorResponseBean;
import com.ttce.vehiclemanage.ui.search.adapter.HistoryAdapter;
import com.ttce.vehiclemanage.ui.search.adapter.SearchAdapter;
import com.ttce.vehiclemanage.ui.search.constract.SearchConstract;
import com.ttce.vehiclemanage.ui.search.model.SearchModel;
import com.ttce.vehiclemanage.ui.search.presenter.SearchPresenter;
import com.ttce.vehiclemanage.utils.ToastUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends BaseActivity<SearchPresenter, SearchModel> implements SearchConstract.View, HistoryAdapter.OnClickListener, SearchAdapter.OnItemClickListener {

    @Bind(R.id.et_search)
    AutoCompleteTextView et_search;
    @Bind(R.id.tv_search)
    TextView tv_search;

    @Bind(R.id.ll_search)
    LinearLayout ll_search;

    @Bind(R.id.recycler_history)
    RecyclerView recycler_history;

    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;

    HistoryAdapter historyAdapter;
    SearchAdapter searchAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
//        historyAdapter = new HistoryAdapter(this);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        recycler_history.setLayoutManager(gridLayoutManager);
//        recycler_history.setAdapter(historyAdapter);
//        historyAdapter.setOnClickListener(this);
        historyAdapter = new HistoryAdapter(this);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_history.setLayoutManager(gridLayoutManager);
        recycler_history.setAdapter(historyAdapter);
        historyAdapter.setOnClickListener(this);
        //
        searchAdapter = new SearchAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setAdapter(searchAdapter);
        searchAdapter.setOnItemClickListener(this);
        //
        mPresenter.getSearchHistory();
    }

    @OnClick({R.id.iv_back, R.id.tv_search, R.id.iv_clear})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_search:
                mPresenter.searchVehicles(et_search.getText().toString());
                if (!et_search.getText().toString().isEmpty()) {
                    String record = SPDefaultHelper.getString(SPDefaultHelper.SEARCH_RECORD);
                    if (record.contains(et_search.getText().toString())){
                        return;
                    }
                    if (!record.isEmpty()) {
                        record  = et_search.getText().toString() + ";"+ record;
                    }else{
                        record = et_search.getText().toString();
                    }
                    SPDefaultHelper.saveString(SPDefaultHelper.SEARCH_RECORD, record);
                }
                break;
            case R.id.iv_clear:
                SPDefaultHelper.saveString(SPDefaultHelper.SEARCH_RECORD, "");
                mPresenter.getSearchHistory();
                break;
        }
    }


    @Override
    public void searchVehicles(List<MonitorResponseBean> monitorResponseBeanList) {
        if (monitorResponseBeanList.size() > 0) {
            ll_search.setVisibility(View.GONE);
            recyclerview.setVisibility(View.VISIBLE);
            searchAdapter.setBeanList(monitorResponseBeanList);
            searchAdapter.notifyDataSetChanged();
        } else {
            mPresenter.getSearchHistory();
            ll_search.setVisibility(View.VISIBLE);
            recyclerview.setVisibility(View.GONE);
            ToastUtil.showToast("没有可监控车辆");
        }
    }

    @Override
    public void onHistory(String[] historyKeys) {
        ll_search.setVisibility(View.VISIBLE);
        recyclerview.setVisibility(View.GONE);
        historyAdapter.setStringList(Arrays.asList(historyKeys));
        historyAdapter.notifyDataSetChanged();

    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {
        ToastUitl.showToastWithImg(msg, R.drawable.ic_wrong);
    }

    /**
     * @param activity
     * @param requestCode
     */
    public static void go2Page(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, SearchActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onClick(String str) {
        et_search.setText(str);
        tv_search.performClick();
    }

    @Override
    public void onItemClick(MonitorResponseBean responseBean) {
        Intent intent = new Intent();
        intent.putExtra("bean", responseBean);
        setResult(RESULT_OK, intent);
        finish();
    }
}
