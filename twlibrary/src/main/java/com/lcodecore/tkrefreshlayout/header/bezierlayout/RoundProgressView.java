package com.lcodecore.tkrefreshlayout.header.bezierlayout;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by Administrator on 2015/8/27.
 */
public class RoundProgressView extends View {
    private Paint mPath;
    private Paint mPantR;
    private float r = 40;
    private int num = 7;
    private int stratAngle = 270;
    private int endAngle = 0;
    private int outCir_value = 15;

    public void setCir_x(int cir_x) {
        this.cir_x = cir_x;
    }

    private int cir_x;

    public RoundProgressView(Context context) {
        this(context, null, 0);
    }

    public RoundProgressView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    ValueAnimator va;

    private void init() {
        mPath = new Paint();
        mPantR = new Paint();
        mPantR.setColor(Color.WHITE);
        mPantR.setAntiAlias(true);
        mPath.setAntiAlias(true);
        mPath.setColor(Color.rgb(114, 114, 114));

        va = ValueAnimator.ofInt(0, 360);
        va.setDuration(720);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                endAngle = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        va.setRepeatCount(ValueAnimator.INFINITE);
        va.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    private RectF oval, oval2;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getMeasuredWidth() / num - 10;
        mPath.setStyle(Paint.Style.FILL);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r, mPath);
        canvas.save();
        mPath.setStyle(Paint.Style.STROKE);//设置为空心
        mPath.setStrokeWidth(6);
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r + 15, mPath);
        canvas.restore();

        mPantR.setStyle(Paint.Style.FILL);
        if (oval == null) oval = new RectF();
        oval.set(getMeasuredWidth() / 2 - r, getMeasuredHeight() / 2 - r, getMeasuredWidth() / 2 + r, getMeasuredHeight() / 2 + r);// 设置个新的长方形，扫描测量
        canvas.drawArc(oval, stratAngle, endAngle, true, mPantR);
        canvas.save();
        mPantR.setStrokeWidth(6);
        mPantR.setStyle(Paint.Style.STROKE);
        if (oval2 == null) oval2 = new RectF();
        oval2.set(getMeasuredWidth() / 2 - r - outCir_value, getMeasuredHeight() / 2 - r - outCir_value, getMeasuredWidth() / 2 + r + outCir_value, getMeasuredHeight() / 2 + r + outCir_value);// 设置个新的长方形，扫描测量
        canvas.drawArc(oval2, stratAngle, endAngle, false, mPantR);
        canvas.restore();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (va != null) va.cancel();
    }

    public void startAnim() {
        if (va != null) va.start();
    }

    public void stopAnim() {
        if (va != null && va.isRunning()) va.cancel();
    }

}
