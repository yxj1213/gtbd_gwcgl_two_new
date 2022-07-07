package com.ttce.vehiclemanage.ui.main.adapter.home;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.commonutils.DisplayUtil;
import com.ttce.vehiclemanage.R;
import com.ttce.vehiclemanage.bean.ChangeCompanyBean;
import com.ttce.vehiclemanage.ui.main.activity.home.CreateBusiness2Activity;
import com.ttce.vehiclemanage.widget.linearlayout.ShadowLayout;

import java.util.ArrayList;
import java.util.List;


public class BusinessBeanAdapter extends BaseAdapter {
    Context context;
    Activity mactivity;
    List<ChangeCompanyBean> brandsList = new ArrayList<ChangeCompanyBean>();
    LayoutInflater mInflater;
    private int selectPosition = -1;//用于记录用户选择的变量

    public BusinessBeanAdapter(Activity activity,Context context, List<ChangeCompanyBean> mList, int selectPosition) {
        this.context = context;
        this.brandsList = mList;
        this.mactivity = activity;
        this.selectPosition = selectPosition;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (brandsList == null) {
            return 0;
        }
        return brandsList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.mainleft_business_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.rel_bg = (RelativeLayout) convertView.findViewById(R.id.rel_bg);
            viewHolder.id_select = (RadioButton) convertView.findViewById(R.id.id_select);
            viewHolder.img_left = (ImageView) convertView.findViewById(R.id.img_left);
            viewHolder.tv_business = (TextView) convertView.findViewById(R.id.tv_business);
            viewHolder.tv_select = (TextView) convertView.findViewById(R.id.tv_select);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_business.setText(brandsList.get(position).getCompanyName());
        if (position == selectPosition) {
            viewHolder.id_select.setChecked(true);
            viewHolder.tv_select.setVisibility(View.VISIBLE);
            if(brandsList.get(position).isCreateDepartment()==true){
                viewHolder.id_select.setClickable(true);
                viewHolder.id_select.setButtonDrawable(R.mipmap.select_add);
            }else{
                viewHolder.id_select.setClickable(false);
                viewHolder.id_select.setButtonDrawable(R.color.transparent);
            }
            viewHolder.rel_bg.setBackgroundResource(R.drawable.business_sel_bg);
            if (brandsList.get(position).getCompanyId().contains("00000000-0000-0000-0000-000000000000") || brandsList.get(position).getCreateUser().contains("00000000-0000-0000-0000-000000000000")) {
                viewHolder.img_left.setImageResource(R.mipmap.icon_bottom_five_sel);
            } else {
                viewHolder.img_left.setImageResource(R.mipmap.icon_business_sel);
            }
            viewHolder.img_left.setBackgroundResource(R.drawable.white_6_bg);
        } else {
            viewHolder.tv_select.setVisibility(View.GONE);
            viewHolder.id_select.setChecked(false);
            viewHolder.id_select.setClickable(false);
            viewHolder.id_select.setButtonDrawable(R.color.transparent);
            viewHolder.rel_bg.setBackground(null);
            if (brandsList.get(position).getCompanyId().contains("00000000-0000-0000-0000-000000000000") || brandsList.get(position).getCreateUser().contains("00000000-0000-0000-0000-000000000000")) {
                viewHolder.img_left.setImageResource(R.mipmap.icon_personal_unsel);
            } else {
                viewHolder.img_left.setImageResource(R.mipmap.icon_business);
            }
            viewHolder.img_left.setBackgroundResource(R.drawable.c1_6_bg);
        }

        ViewHolder finalViewHolder = viewHolder;
        viewHolder.id_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建PopupWindow实例,200,LayoutParams.MATCH_PARENT分别是宽度和高度
                View popupWindow_view = LinearLayout.inflate(context, R.layout.popwindow_create_business, null);
                PopupWindow popupWindow = new PopupWindow(popupWindow_view, DisplayUtil.dip2px(136), DisplayUtil.dip2px(46), true);
                // 获取自定义布局文件activity_popupwindow_left.xml的视图
                ShadowLayout add_fenzu=popupWindow_view.findViewById(R.id.add_fenzu);
                add_fenzu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        CreateBusiness2Activity.goToPage(mactivity,brandsList.get(position).getCompanyId(),brandsList.get(position).getCompanyName(), "分组");
                    }
                });

                //点击外部消失
                popupWindow.setOutsideTouchable(true);
                popupWindow.setFocusable(true);
                //软键盘不会挡着popupwindow
                popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                View main_view = LinearLayout.inflate(context, R.layout.main_left, null);
                int[] windowPos=calculatePopWindowPos(finalViewHolder.id_select,popupWindow_view,main_view);
                popupWindow.showAtLocation(finalViewHolder.id_select,Gravity.TOP |Gravity.START,windowPos[0],windowPos[1]);
            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                selectPosition = position;
//                BusinessBeanAdapter.this.notifyDataSetChanged();
                mSelectListener.onItemClick(brandsList.get(position), position);
            }
        });
        return convertView;
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * <p>
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */

    public static int[] calculatePopWindowPos(final View anchorView, final View contentView, final View mainView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = DisplayUtil.getScreenHeight(anchorView.getContext());

        final int screenWidth =DisplayUtil.getScreenWidth(anchorView.getContext());

        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        // 计算contentView的高宽

        final int windowHeight = contentView.getMeasuredHeight();

        final int windowWidth = contentView.getMeasuredWidth();

        // 判断需要向上弹出还是向下弹出显示

        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);

        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;

            windowPos[1] = anchorLoc[1] - windowHeight;

        } else {
            windowPos[0] = screenWidth - windowWidth - (screenWidth/4+DisplayUtil.dip2px(10));

            windowPos[1] = anchorLoc[1] + anchorHeight+DisplayUtil.dip2px(4);

        }

        return windowPos;

    }






    private OnItemClickListener mSelectListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mSelectListener = listener;
    }

    public class ViewHolder {
        TextView tv_business, tv_select;
        RadioButton id_select;
        ImageView img_left;
        RelativeLayout rel_bg;
    }

    public interface OnItemClickListener {
        void onItemClick(ChangeCompanyBean changeCompanyBean, int selectPosition);
    }

    public int getSelectPosition() {
        return selectPosition;
    }

    public void setSelectPosition(int selectPosition) {
        this.selectPosition = selectPosition;
        this.notifyDataSetChanged();
    }
}

