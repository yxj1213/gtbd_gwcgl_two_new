package com.ttce.vehiclemanage.ui.locus.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.ui.locus.contract.DeptContract;
import com.ttce.vehiclemanage.ui.locus.model.DeptModel;
import com.ttce.vehiclemanage.ui.locus.presenter.DeptPresenter;
import com.ttce.vehiclemanage.utils.ToastUtil;
import com.ttce.vehiclemanage.widget.tree.Dept;
import com.ttce.vehiclemanage.widget.tree.NodeHelper;
import com.ttce.vehiclemanage.widget.tree.NodeTreeAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class DeptActivity extends BaseActivity<DeptPresenter, DeptModel> implements DeptContract.View, NodeTreeAdapter.OnItemClickListener {
    private final String TAG = "DeptActivity";

    @Bind(R.id.title_bar_title)
    TextView titleBarTitle;
    @Bind(R.id.et_search)
    EditText etSearch;
    @Bind(R.id.dept_tree)
    ListView deptTreeLV;
    @Bind(R.id.tv_select_all)
    TextView selectAllTV;
    @Bind(R.id.tv_confirm)
    TextView confirmTV;
    @Bind(R.id.multSelectLL)
    LinearLayout multSelectLL;
    private boolean isMultSelect;

    private NodeTreeAdapter mAdapter;
    private LinkedList<Dept> mLinkedList = new LinkedList<>();
    private List<CompanyItemBean> companyBeanList;
    private List<Dept> data = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_dept;
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public void initView() {
        titleBarTitle.setText("选择设备");
        isMultSelect = getIntent().getBooleanExtra("isMultSelect", false);
        if (isMultSelect) {
            multSelectLL.setVisibility(View.VISIBLE);
        } else {
            multSelectLL.setVisibility(View.GONE);
        }
        initData();
    }

    private void initData() {
        mAdapter = new NodeTreeAdapter(this, deptTreeLV,/* mLinkedList,*/ -1);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setSelectMode(isMultSelect);
//        deptTreeLV.setAdapter(mAdapter);
        mPresenter.getDeptData();
    }

    @Override
    public void onItemClick(Dept dept) {
        if (!isMultSelect) {
            if (dept.getCompanyItemBean().getType() == 0) {
                Intent intent = getIntent();
                intent.putExtra("DeviceId", dept.getCompanyItemBean().getDeviceId());
                intent.putExtra("CarNumber", dept.getCompanyItemBean().getCarNumber());
                intent.putExtra("DeviceStatus", dept.getCompanyItemBean().getCarDisplayColorFormat());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    @Override
    public void buildDeptTree(List<CompanyItemBean> deptListData) {
        this.companyBeanList = deptListData;
        filterCompanyData();
    }

    /**
     * 过滤数据
     */
    private void filterCompanyData() {
        data.clear();
        mLinkedList.clear();
        String status = "-1";
        int level = 1;
        setList(companyBeanList, level, Integer.parseInt(status));
        mLinkedList.addAll(NodeHelper.sortDepts(data));

        //TODO 2021.11.30
        mAdapter.setLinkedList(mLinkedList);
        deptTreeLV.setAdapter(mAdapter);

     //   mAdapter.notifyDataSetChanged();
    }

    /**
     * 遍历
     *
     * @param dataList
     */
    private void setList(List<CompanyItemBean> dataList, int level, int status) {
        for (int m = 0; m < dataList.size(); m++) {
            if (status == -1 || dataList.get(m).getStatus() == status) {
                Dept dept = null;
                if(dataList.get(m).getPid()==null){
                    dept = new Dept(dataList.get(m).getId(),"", dataList.get(m).getCarNumber(), dataList.get(m));
                }else {
                    dept = new Dept(dataList.get(m).getId(), dataList.get(m).getPid(), dataList.get(m).getCarNumber(), dataList.get(m));
                }
                dept.set_level(level);
                data.add(dept);
            }
            int levels = level + 1;
            setList(dataList.get(m).getChildren(), levels, status);
        }
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }

    @OnClick({R.id.title_bar_back, R.id.tv_select_all, R.id.tv_confirm, R.id.tv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                finish();
                break;
            case R.id.tv_select_all:
                if (selectAllTV.getTag() == null || (Integer) selectAllTV.getTag() == 0) {
                    mAdapter.selectAllItems();
                    selectAllTV.setText("取消全选");
                    selectAllTV.setTag(1);
                } else {
                    filterCompanyData();
                    mAdapter.getSelectedItems().clear();
                    selectAllTV.setText("全选");
                    selectAllTV.setTag(0);
                }
                break;
            case R.id.tv_confirm:
                List<CompanyItemBean> selectedItems = mAdapter.getSelectedItems();
                if (selectedItems == null || selectedItems.size() == 0) {
                    ToastUtil.showToast("请选择至少一个设备");
                    return;
                }

                Intent intent = getIntent();
                intent.putExtra("datas", (Serializable) selectedItems);

                setResult(RESULT_OK, intent);
                finish();
                break;
            case R.id.tv_search:
                String ss = etSearch.getText().toString();
                if (TextUtils.isEmpty(ss)) {
                    filterCompanyData();
                } else {
                    data.clear();
                    mLinkedList.clear();
                    String status = "-1";
                    int level = 1;
                    setList(companyBeanList, level, Integer.parseInt(status));
                    mLinkedList.addAll(NodeHelper.sortDeptss(data, ss));


                    mAdapter.setLinkedList(mLinkedList);
                    deptTreeLV.setAdapter(mAdapter);
                   // mAdapter.notifyDataSetChanged();
                }
                break;
        }
    }



    private void setData(String ss) {

    }
}
