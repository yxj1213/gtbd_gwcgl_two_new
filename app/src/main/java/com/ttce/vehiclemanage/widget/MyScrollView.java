package com.ttce.vehiclemanage.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * 类：
 * ：2018/10/17 17:56
 * 功能描述:ScrollView（Listview）和Viewpager（banner）的冲突解决办法
 */
public class MyScrollView extends ScrollView {

    private float xLast,yLast,xDistance,yDistance;

    // ScrollView的子View， 也是ScrollView的唯一一个子View
    private View contentView;

    // 用于记录正常的布局位置
    private Rect originalRect = new Rect();


    public MyScrollView(Context context) {
        super(context);
    }


    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onFinishInflate() {
        if (getChildCount() > 0) {
            contentView = getChildAt(0);
        }
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);


        if (contentView == null)
            return;

        // ScrollView中的唯一子控件的位置信息, 这个位置信息在整个控件的生命周期中保持不变
        originalRect.set(contentView.getLeft(), contentView.getTop(),
                contentView.getRight(), contentView.getBottom());
    }

    /**
     * 在这里解决滑动上下滑动，左右滑动冲突
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;
                //判断有上下滑动的意向（用于字VIew是上下，parent是水平的）
                long atime = (ev.getEventTime() - ev.getDownTime()) / 100;
              if(atime>1){
                   return false; //表示向下传递事件  传递给子控件 (长按拖动)
                }else   if (xDistance > yDistance) {
                  return false;  // true不传给子控件（垂直滑动）
              }
                break;
        }

        return super.onInterceptTouchEvent(ev);
    }

}
