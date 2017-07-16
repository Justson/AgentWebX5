package com.lcodecore.tkrefreshlayout.header.bezierlayout;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * cjj
 */
public class RippleView extends View {
    private Paint mPaint;
    private int r;
    private OnRippleEndListener listener;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public RippleView(Context context) {
        this(context, null, 0);
    }

    public RippleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    ValueAnimator va;

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(0xffffffff);
        mPaint.setStyle(Paint.Style.FILL);
    }

    public void setRippleColor(@ColorInt int color) {
        if (mPaint != null) mPaint.setColor(color);
    }

    public void startReveal() {
        setVisibility(VISIBLE);
        if (va == null) {
            int bigRadius = (int) (Math.sqrt(Math.pow(getHeight(), 2) + Math.pow(getWidth(), 2)));
            va = ValueAnimator.ofInt(0, bigRadius / 2);
            va.setDuration(bigRadius);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    r = (int) animation.getAnimatedValue() * 2;
                    invalidate();
                }
            });
            va.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    if (listener != null) {
                        listener.onRippleEnd();
                    }
                }
            });
        }
        va.start();
    }

    public void stopAnim() {
        if (va != null && va.isRunning()) va.cancel();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, r, mPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (va != null) va.cancel();
    }

    public void setRippleEndListener(OnRippleEndListener listener) {
        this.listener = listener;
    }

    public interface OnRippleEndListener {
        void onRippleEnd();
    }
}
