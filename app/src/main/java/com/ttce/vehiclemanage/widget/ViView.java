package com.ttce.vehiclemanage.widget;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.ttce.vehiclemanage.R;

public class ViView extends View {
    Context context;
    int mcircleradius = 0;//实际画圆的半径
    LinearLayout mmapView;
    int txtcircleradius= 0;//字体显示的半径

    //构造方法
    public ViView(Context context) {
        super(context);
    }

    //构造方法
    public ViView(Context context, int circleradius, LinearLayout mapView, int tag) {
        super(context);

        this.mcircleradius = circleradius;
        this.mmapView = mapView;
        this.txtcircleradius = tag;
    }
    //构造方法
    public ViView(Context context, int circleradius, LinearLayout mapView) {
        super(context);

        this.mcircleradius = circleradius;
        this.mmapView = mapView;
    }
    public ViView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ViView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

    }

    /*绘图*/
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //得到屏幕宽高
        int width = mmapView.getWidth();
        int height = mmapView.getHeight();
        int circle=mcircleradius;
        int StrokeWidth = 5;
        // 创建画笔
        Paint paint1 = new Paint();
        Paint paint2 = new Paint();
        // 消除锯齿
        paint1.setAntiAlias(true);
        paint2.setAntiAlias(true);
        //画笔颜色
        paint1.setColor(getResources().getColor(R.color.map_overlay_stroke));
        paint2.setColor(getResources().getColor(R.color.map_overlay));
        //设置圆为空心
        paint1.setStyle(Paint.Style.STROKE);

        //设置圆环形状和大小
        RectF oval = new RectF(width - circle, width - circle, width + circle, width + circle);
        paint1.setStrokeWidth(StrokeWidth);
        canvas.drawArc(oval, -90, 90, false, paint1);

        //设置图片和文字
        Paint paint3 = new Paint();
        paint3.setTextSize(40);
        paint3.setColor(getResources().getColor(R.color.black));
        canvas.drawText(txtcircleradius + "米", width / 2 - 50, height / 2 + 70, paint3);
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ico_weilan_car), width / 2 - 30, height / 2 - 50, paint1);

        canvas.drawCircle(width / 2, height / 2, circle, paint1);
        canvas.drawCircle(width / 2, height / 2, circle - StrokeWidth, paint2);
    }

//    /*绘图*/
//    public void onDraw(Canvas canvas, int circleradius, LinearLayout mapView) {
//        super.onDraw(canvas);
//        //得到屏幕宽高
//        int width = mapView.getWidth();
//        int height = mapView.getHeight();
//        int radius=circleradius;
//        int StrokeWidth=5;
//        // 创建画笔
//        Paint paint1 = new Paint();
//        Paint paint2 = new Paint();
//        // 消除锯齿
//        paint1.setAntiAlias(true);
//        paint2.setAntiAlias(true);
//        //画笔颜色
//        paint1.setColor(getResources().getColor(R.color.map_overlay_stroke));
//        paint2.setColor(getResources().getColor(R.color.map_overlay));
//        //设置圆为空心
//        paint1.setStyle(Paint.Style.STROKE);
//
//        //设置圆环形状和大小
//        RectF oval = new RectF(width-radius,width-radius,width+radius,width+radius);
//        paint1.setStrokeWidth(StrokeWidth);
//        canvas.drawArc(oval,-90,90,false,paint1);
//
//        //设置图片和文字
//        paint1.setTextSize(15);
//        paint2.setTextSize(15);
//        canvas.drawText("200米",width/2-50,height/2+50, paint1);
//        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ico_weilan_car),width/2-50,height/2-50,paint1);
//
//        canvas.drawCircle(width/2,height/2,radius,paint1);
//        canvas.drawCircle(width/2,height/2,radius-StrokeWidth,paint2);
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
} 