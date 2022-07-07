package com.ttce.vehiclemanage.ui.main.adapter.needcar;

import android.content.Context;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.baidu.mapapi.search.core.PoiChildrenInfo;
import com.baidu.mapapi.search.core.PoiDetailInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.ttce.vehiclemanage.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PoiListAdapter extends BaseAdapter implements PoiChidrenAdapter.OnGetChildrenLocationListener {

    private Context mcontext;
    private String search;
    private List<PoiInfo> mPoilist;
    private OnGetChildrenLocationListener mOnGetChildrenLocationListener = null;

    public PoiListAdapter(Context mcontext, List<PoiInfo> mPoilist,String search) {
        this.mcontext = mcontext;
        this.mPoilist = mPoilist;
        this.search = search;
    }

    @Override
    public int getCount() {
        return mPoilist.size();
    }

    @Override
    public Object getItem(int position) {
        return mPoilist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mcontext, R.layout.poi_item, null);
            viewHolder.poiName = (TextView) convertView.findViewById(R.id.poi_name);
            viewHolder.poiAddress = (TextView) convertView.findViewById(R.id.poi_address);
            viewHolder.poiChilderList = (GridView) convertView.findViewById(R.id.childer_poi_gridview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        viewHolder.poiName.setText(mPoilist.get(position).getName());
        changeText(viewHolder.poiName,mPoilist.get(position).getName(),search);
        if(mPoilist.get(position).getAddress().equals("")){
            viewHolder.poiAddress.setVisibility(View.GONE);
        }else{
            viewHolder.poiAddress.setVisibility(View.VISIBLE);
            viewHolder.poiAddress.setText(mPoilist.get(position).getAddress());
        }
        PoiDetailInfo poiDetailInfo = mPoilist.get(position).getPoiDetailInfo();

        if (poiDetailInfo != null && null != poiDetailInfo.getPoiChildrenInfoList() &&
                poiDetailInfo.getPoiChildrenInfoList().size() > 0) {

            viewHolder.poiChilderList.setVisibility(View.VISIBLE);
            List<PoiChildrenInfo> poiChildrenInfoList = poiDetailInfo.getPoiChildrenInfoList();
            PoiChidrenAdapter poiChidrenAdapter = new PoiChidrenAdapter(mcontext, poiChildrenInfoList);
            poiChidrenAdapter.setOnGetChildrenLocationListener(this);
            viewHolder.poiChilderList.setAdapter(poiChidrenAdapter);

        } else {
            viewHolder.poiChilderList.setVisibility(View.GONE);
        }

        return convertView;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void changeText(TextView txt, String context, String tag){ //context 长字符串  tag 要改变颜色的字符串的集合
            //创建 SpannableString 对象
            SpannableString mStyledText = new SpannableString(context);
            //对字符串   "系统开小差，请尝试刷新一下"  进行处理，将“刷新”两个字设置为蓝色的  且可点击的
        if(context.length()>tag.length()){
            mStyledText.setSpan(new ForegroundColorSpan(mcontext.getColor(R.color.app_main_colors)),0, tag.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            Pattern mPattern = Pattern.compile(search);
            Matcher mMatcher = mPattern.matcher(context);
            if(mMatcher.find()){
                txt.getPaint().setFakeBoldText(true);
                txt.setText(mStyledText);
            }else{
                txt.setText(context);
                txt.setTextColor(mcontext.getColor(R.color.work_item_txt_color));
            }
        }else{
            txt.setText(context);
            txt.setTextColor(mcontext.getColor(R.color.work_item_txt_color));
        }
    }

    @Override
    public void getChildrenLocation(PoiChildrenInfo childrenLocation) {
        mOnGetChildrenLocationListener.getChildrenLocation(childrenLocation);
    }

    private static class ViewHolder {
        TextView poiName;
        TextView poiAddress;
        GridView poiChilderList;
    }


    public interface OnGetChildrenLocationListener {
        void getChildrenLocation(PoiChildrenInfo childrenLocation);
    }

    public void setOnGetChildrenLocationListener(OnGetChildrenLocationListener onGetChildrenLocationListener) {
        this.mOnGetChildrenLocationListener = onGetChildrenLocationListener;
    }

}
