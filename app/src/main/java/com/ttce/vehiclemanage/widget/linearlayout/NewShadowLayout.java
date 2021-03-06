package com.ttce.vehiclemanage.widget.linearlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import com.ttce.vehiclemanage.R;

public class NewShadowLayout extends LinearLayout {

    /**
     * 阴影颜色
     */
    private int mShadowColor;
    /**
     * 阴影的扩散范围(也可以理解为扩散程度)
     */
    private float mShadowLimit;
    /**
     * 阴影的圆角大小
     */
    private float mCornerRadius;
    /**
     * x轴的偏移量
     */
    private float mDx;
    /**
     * y轴的偏移量
     */
    private float mDy;
    /**
     * 左边是否显示阴影
     */
    private boolean leftShow;
    /**
     * 右边是否显示阴影
     */
    private boolean rightShow;
    /**
     * 上边是否显示阴影
     */
    private boolean topShow;
    /**
     * 下面是否显示阴影
     */
    private boolean bottomShow;


    private boolean mInvalidateShadowOnSizeChanged = true;
    private boolean mForceInvalidateShadow = false;

    public NewShadowLayout(Context context) {
        super(context);
        initView(context, null);
    }

    public NewShadowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public NewShadowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return 0;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return 0;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0 && h > 0 && (getBackground() == null || mInvalidateShadowOnSizeChanged
                || mForceInvalidateShadow)) {
            mForceInvalidateShadow = false;
            setBackgroundCompat(w, h);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mForceInvalidateShadow) {
            mForceInvalidateShadow = false;
            setBackgroundCompat(right - left, bottom - top);
        }
    }

    public void setInvalidateShadowOnSizeChanged(boolean invalidateShadowOnSizeChanged) {
        mInvalidateShadowOnSizeChanged = invalidateShadowOnSizeChanged;
    }

    public void invalidateShadow() {
        mForceInvalidateShadow = true;
        requestLayout();
        invalidate();
    }

    private void initView(Context context, AttributeSet attrs) {
        initAttributes(context, attrs);

        int xPadding = (int) (mShadowLimit + Math.abs(mDx));
        int yPadding = (int) (mShadowLimit + Math.abs(mDy));
        int left;
        int right;
        int top;
        int bottom;
        if (leftShow) {
            left = xPadding;
        } else {
            left = 0;
        }

        if (topShow) {
            top = yPadding;
        } else {
            top = 0;
        }


        if (rightShow) {
            right = xPadding;
        } else {
            right = 0;
        }

        if (bottomShow) {
            bottom = yPadding;
        } else {
            bottom = 0;
        }

        setPadding(left, top, right, bottom);
    }

    @SuppressWarnings("deprecation")
    private void setBackgroundCompat(int w, int h) {
        Bitmap bitmap = createShadowBitmap(w, h, mCornerRadius, mShadowLimit, mDx,
                mDy, mShadowColor, Color.WHITE);
        BitmapDrawable drawable = new BitmapDrawable(getResources(), bitmap);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable(drawable);
        } else {
            setBackground(drawable);
        }
    }


    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray attr = getTypedArray(context, attrs, R.styleable.NewShadowLayout);
        if (attr == null) {
            return;
        }

        try {
            //默认是显示
            leftShow = attr.getBoolean(R.styleable.NewShadowLayout_yc_leftShow, true);
            rightShow = attr.getBoolean(R.styleable.NewShadowLayout_yc_rightShow, true);
            bottomShow = attr.getBoolean(R.styleable.NewShadowLayout_yc_bottomShow, true);
            topShow = attr.getBoolean(R.styleable.NewShadowLayout_yc_topShow, true);

            mCornerRadius = attr.getDimension(R.styleable.NewShadowLayout_yc_cornerRadius, 0);
            mShadowLimit = attr.getDimension(R.styleable.NewShadowLayout_yc_shadowLimit, 0);
            mDx = attr.getDimension(R.styleable.NewShadowLayout_yc_dx, 0);
            mDy = attr.getDimension(R.styleable.NewShadowLayout_yc_dy, 0);
            mShadowColor = attr.getColor(R.styleable.NewShadowLayout_yc_shadowColor,
                    getResources().getColor(R.color.gray));
        } finally {
            attr.recycle();
        }
    }

    private TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }

    private Bitmap createShadowBitmap(int shadowWidth, int shadowHeight, float cornerRadius,
                                      float shadowRadius, float dx, float dy,
                                      int shadowColor, int fillColor) {

        //根据宽高创建bitmap背景
        Bitmap output = Bitmap.createBitmap(shadowWidth, shadowHeight, Bitmap.Config.ARGB_8888);
        //用画板canvas进行绘制
        Canvas canvas = new Canvas(output);
        RectF shadowRect = new RectF(shadowRadius, shadowRadius,
                shadowWidth - shadowRadius, shadowHeight - shadowRadius);

        if (dy > 0) {
            shadowRect.top += dy;
            shadowRect.bottom -= dy;
        } else if (dy < 0) {
            shadowRect.top += Math.abs(dy);
            shadowRect.bottom -= Math.abs(dy);
        }

        if (dx > 0) {
            shadowRect.left += dx;
            shadowRect.right -= dx;
        } else if (dx < 0) {
            shadowRect.left += Math.abs(dx);
            shadowRect.right -= Math.abs(dx);
        }

        Paint shadowPaint = new Paint();
        shadowPaint.setAntiAlias(true);
        shadowPaint.setColor(fillColor);
        shadowPaint.setStyle(Paint.Style.FILL);
        if (!isInEditMode()) {
            shadowPaint.setShadowLayer(shadowRadius, dx, dy, shadowColor);
        }
        canvas.drawRoundRect(shadowRect, cornerRadius, cornerRadius, shadowPaint);
        return output;
    }
}

