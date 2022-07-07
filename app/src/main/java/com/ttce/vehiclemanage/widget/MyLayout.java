package com.ttce.vehiclemanage.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * Created by Administrator on 2019/7/4.
 */

public class MyLayout extends RelativeLayout implements GestureDetector.OnGestureListener {

    private int verticalMinDistance = 20;

    private int minVelocity = 0;

    private MyLayoutCallBack myLayoutCallBack;
    private GestureDetector gestureDetector;
    Context context;

    public void setCallBack(MyLayoutCallBack myLayoutCallBack) {
        this.myLayoutCallBack = myLayoutCallBack;
    }

    public MyLayout(Context context) {
        super(context);
        gestureDetector = new GestureDetector(context, this);
        this.context = context;
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(context, this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("pingan", "onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//        Log.d("pingan", "onScroll" + distanceX + "distanceY:" + distanceY);
//
//        if (distanceX < -verticalMinDistance) {
//            Log.d("pingan", "向右手势");
////            myLayoutCallBack.scrollByX(20);
//
//        } else if (distanceX > verticalMinDistance) {
//
//            Log.d("pingan", "向左手势");
////            myLayoutCallBack.scrollByX(-20);
//
//        } else if (distanceY < -verticalMinDistance) {
//            Log.d("pingan", "向下手势");
//            myLayoutCallBack.scrollByY(20);
//
//        } else if (distanceY > verticalMinDistance) {
//
//            Log.d("pingan", "向上手势");
//            myLayoutCallBack.scrollByY(-20);
//
//        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("pingan", "onFling");

//        if (e1.getX()
//                - e2.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity) {
//            Log.d("pingan", "向左手势");
////            myLayoutCallBack.scrollByX(-20);
//
//        } else if ((e2.getX() - e1.getX() > verticalMinDistance && Math.abs(velocityX) > minVelocity)) {
//
//            Log.d("pingan", "向右手势");
////            myLayoutCallBack.scrollByX(20);
//
//        } else if (e1.getY()
//                - e2.getY() > verticalMinDistance && Math.abs(velocityY) > minVelocity) {
//            Log.d("pingan", "向上手势");
//            myLayoutCallBack.scrollByY(-20);
//
//        } else if ((e2.getY() - e1.getY() > verticalMinDistance && Math.abs(velocityY) > minVelocity)) {
//
//            Log.d("pingan", "向下手势");
//            myLayoutCallBack.scrollByY(20);
//
//        }
        if (myLayoutCallBack != null) {
            if (e1.getY() - e2.getY() > verticalMinDistance) {
                myLayoutCallBack.scrollByY(-20);
            } else if (e2.getY() - e1.getY() > verticalMinDistance) {
                myLayoutCallBack.scrollByY(20);
            }
        }
        return true;
    }

    public interface MyLayoutCallBack {
        void scrollByY(int m);
    }
}