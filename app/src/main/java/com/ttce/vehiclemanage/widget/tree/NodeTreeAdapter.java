package com.ttce.vehiclemanage.widget.tree;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.CompanyItemBean;
import com.ttce.vehiclemanage.utils.CarStateFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by HQOCSHheqing on 2016/8/3.
 *
 * @description 适配器类，就是listview最常见的适配器写法
 */
public class NodeTreeAdapter extends BaseAdapter {

    // 大家经常用ArrayList，但是这里为什么要使用LinkedList
    // ，后面大家会发现因为这个list会随着用户展开、收缩某一项而频繁的进行增加、删除元素操作，
    // 因为ArrayList是数组实现的，频繁的增删性能低下，而LinkedList是链表实现的，对于频繁的增删
    // 操作性能要比ArrayList好。
    private LinkedList<Dept> nodeLinkedList;
    private LayoutInflater inflater;
    private int retract;// 缩进值
    private Context context;
    private int type;
    private boolean selectMode;
    private List<CompanyItemBean> selectedItems=new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public boolean isSelectMode() {
        return selectMode;
    }

    public void setSelectMode(boolean selectMode) {
        this.selectMode = selectMode;
    }

    public List<CompanyItemBean> getSelectedItems() {
        return selectedItems;
    }

    public NodeTreeAdapter(Context context, ListView listView, LinkedList<Dept> linkedList) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        nodeLinkedList = linkedList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expandOrCollapse(position);
            }
        });
        // 缩进值，大家可以将它配置在资源文件中，从而实现适配
        retract = (int) (context.getResources().getDisplayMetrics().density * 10 + 0.5f);
    }
    @SuppressWarnings("unchecked")
    public void setLinkedList(LinkedList<Dept> linkedList) {
        if (linkedList != null) {
            nodeLinkedList = (LinkedList<Dept>) linkedList.clone();
            notifyDataSetChanged();
        }
    }
    public NodeTreeAdapter(Context context, ListView listView, int type) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.type = type;

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expandOrCollapse(position);
            }
        });
        // 缩进值，大家可以将它配置在资源文件中，从而实现适配
        retract = (int) (context.getResources().getDisplayMetrics().density * 10 + 0.5f);
    }
    public NodeTreeAdapter(Context context, ListView listView, LinkedList<Dept> linkedList, int type) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.type = type;
        nodeLinkedList = linkedList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                expandOrCollapse(position);
            }
        });
        // 缩进值，大家可以将它配置在资源文件中，从而实现适配
        retract = (int) (context.getResources().getDisplayMetrics().density * 10 + 0.5f);
    }

    /**
     * 展开或收缩用户点击的条目
     *
     * @param position
     */
    private void expandOrCollapse(int position) {
        Node node = nodeLinkedList.get(position);
        if (node != null && !node.isLeaf()) {
            boolean old = node.isExpand();
            if (old) {
                List<Node> nodeList = node.get_childrenList();
                int size = nodeList.size();
                Node tmp = null;
                for (int i = 0; i < size; i++) {
                    tmp = nodeList.get(i);
                    if (tmp.isExpand()) {
                        collapse(tmp, position + 1);
                    }
                    nodeLinkedList.remove(position + 1);
                }
            } else {
                /*boolean isLeaf=false;
                for(int i=0;i<node.get_childrenList().size();i++){
                    Dept dept= (Dept)node.get_childrenList().get(i);
                    if(dept.isLeaf()){
                        isLeaf=true;
                        break;
                    }
                }
                if(isLeaf){
                    EventBus.getDefault().post(new MessageEvent(AppConstant.MESSAGE_INIT_DEPT_DATA));
                }*/
                nodeLinkedList.addAll(position + 1, node.get_childrenList());
            }
            node.setIsExpand(!old);
            notifyDataSetChanged();
        } else {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(nodeLinkedList.get(position));
            }
        }
    }

    public void expanAll() {
        for (int i = 0; i < nodeLinkedList.size(); i++) {
            Node node = nodeLinkedList.get(i);
            boolean old = node.isExpand();
            if (!old) {
                nodeLinkedList.addAll(i + 1, node.get_childrenList());
            }
            node.setIsExpand(!old);
        }
        notifyDataSetChanged();
    }

    /**
     * 递归收缩用户点击的条目
     * 因为此中实现思路是：当用户展开某一条时，就将该条对应的所有子节点加入到nodeLinkedList
     * ，同时控制缩进，当用户收缩某一条时，就将该条所对应的子节点全部删除，而当用户跨级缩进时
     * ，就需要递归缩进其所有的孩子节点，这样才能保持整个nodeLinkedList的正确性，同时这种实
     * 现方式避免了每次对所有数据进行处理然后插入到一个list，最后显示出来，当数据量一大，就会卡顿，
     * 所以这种只改变局部数据的方式性能大大提高。
     *
     * @param position
     */
    private void collapse(Node node, int position) {
        node.setIsExpand(false);
        List<Node> nodes = node.get_childrenList();
        int size = nodes.size();
        Node tmp = null;
        for (int i = 0; i < size; i++) {
            tmp = nodes.get(i);
            if (tmp.isExpand()) {
                collapse(tmp, position + 1);
            }
            nodeLinkedList.remove(position + 1);
        }
    }

    public void selectAllItems(){
        expanAll();
        for(int i = 0; i < nodeLinkedList.size(); i++){
            final Dept node = nodeLinkedList.get(i);
            if (node.getCompanyItemBean().getType() == 0) {
                if(!selectedItems.contains(node.getCompanyItemBean())){
                    selectedItems.add(node.getCompanyItemBean());
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {

        return nodeLinkedList.size();
    }

    @Override
    public Object getItem(int position) {
        return nodeLinkedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.tree_listview_item, null);
            holder = new ViewHolder();
            holder.tv_number = (TextView) convertView.findViewById(R.id.tv_number);
            holder.imageView = (ImageView) convertView.findViewById(R.id.id_treenode_icon);
            holder.img = (ImageView) convertView.findViewById(R.id.id_treenode_img);
            holder.label = (TextView) convertView.findViewById(R.id.id_treenode_label);
            holder.checkBox=(CheckBox) convertView.findViewById(R.id.cb);
            holder.rel_all=(RelativeLayout) convertView.findViewById(R.id.rel_all);
            holder.rel=(RelativeLayout) convertView.findViewById(R.id.rel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Dept node = nodeLinkedList.get(position);
        holder.label.setText(String.valueOf(node.getCompanyItemBean().getCarNumber()));
        // type = 0 代表是车
        if (node.getCompanyItemBean().getType() == 0) {
            if(type!=-1){
                //解决item隐藏占位问题，隐藏item设置其高度为0
                convertView.setVisibility(View.VISIBLE);
                AbsListView.LayoutParams param = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT); //设置item的weidth和height都为0
                convertView.setLayoutParams(param);
            }
            if(selectMode){
                CompanyItemBean selectedItem=node.getCompanyItemBean();
                holder.checkBox.setVisibility(View.VISIBLE);
                if(selectedItems.contains(selectedItem)){
                    holder.checkBox.setChecked(true);
                }else{
                    holder.checkBox.setChecked(false);
                }
                holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            if(!selectedItems.contains(selectedItem)){
                                selectedItems.add(selectedItem);
                            }
                        }else{
                            if(selectedItems.contains(selectedItem)){
                                selectedItems.remove(selectedItem);
                            }
                        }
                    }
                });
            }else{
                holder.checkBox.setVisibility(View.GONE);
            }
            holder.imageView.setVisibility(View.INVISIBLE);
            holder.tv_number.setText("");
            holder.img.setImageResource(CarStateFactory
                    .getCarColorByStatus(node.getCompanyItemBean().getCarDisplayColorFormat()));
            holder.label.setTextColor(context.getResources().getColor(CarStateFactory
                    .getCarTextColorByStatus(node.getCompanyItemBean().getCarDisplayColorFormat())));
        } else {
            holder.checkBox.setVisibility(View.GONE);
            if(type!=-1){
                if(getNumber(node).equals("0")){
                    convertView.setVisibility(View.GONE);
                    AbsListView.LayoutParams param = new AbsListView.LayoutParams(0,1); //设置item的weidth和height都为0
                    convertView.setLayoutParams(param);
                }else{
                    //解决item隐藏占位问题，隐藏item设置其高度为0
                    convertView.setVisibility(View.VISIBLE);
                    AbsListView.LayoutParams param = new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT); //设置item的weidth和height都为0
                    convertView.setLayoutParams(param);
                }
            }
            holder.tv_number.setText(getNumber(node));
            holder.img.setImageResource(CarStateFactory.getDeptIconByLevel(node.get_level()));
            holder.label.setTextColor(context.getResources().getColor(R.color.common_black));
            holder.imageView.setVisibility(View.VISIBLE);
            holder.imageView.setImageResource(node.get_icon());
        }
        holder.rel_all.setPadding(node.get_level() * retract, 5, 5, 5);
        return convertView;
    }

    private String getNumber(Dept dept) {
        if(type!=-1){
            if(leafCount(dept)==0){
                return "0";
            }
        }
        return leafCount(dept) + "/" + leafCount(dept.getCompanyItemBean());
    }

    public int leafCount(Dept dept) {
        int count = 0;
        if (dept.getCompanyItemBean().getType() == 0) {
            if (type == -1) {// 表示选择的是全部（排除状态为离线的数量  dept.getCompanyItemBean().getCarStatus() == 30 || dept.getCompanyItemBean().getCarStatus() == 255）
                if (dept.getCompanyItemBean().getCarStatus() ==10 ||dept.getCompanyItemBean().getCarStatus() ==20 ||dept.getCompanyItemBean().getCarStatus() ==40||dept.getCompanyItemBean().getCarStatus() ==51||dept.getCompanyItemBean().getCarStatus() ==52||dept.getCompanyItemBean().getCarStatus() ==60) {//
                    count = 1;
                } else {
                    count = 0;
                }
            } else {
                count = 1;
            }

        } else {
            for (Node node : dept.get_childrenList()) {
                Dept mDept = (Dept) node;
                count += leafCount(mDept);
            }

        }
        return count;
    }

    public int leafCount(CompanyItemBean itemBean) {
        int count = 0;
        if (itemBean.getType() == 0) {
            count = 1;
        } else {
            for (CompanyItemBean bean : itemBean.getChildren()) {
                count += leafCount(bean);
            }
        }
        return count;
    }

    static class ViewHolder {
        public TextView tv_number;
        public ImageView imageView;
        public TextView label;
        public ImageView img;
        public CheckBox checkBox;
        public RelativeLayout rel_all,rel;
    }

    public void setType(int type) {
        this.type = type;
    }

    /**
     *
     */
    public interface OnItemClickListener {
        void onItemClick(Dept dept);
    }

}
