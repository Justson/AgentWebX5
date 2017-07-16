package com.lcodecore.tkrefreshlayout.header.bezierlayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.lcodecore.tkrefreshlayout.R;
import com.lcodecore.tkrefreshlayout.IHeaderView;

/**
 * Created by lcodecore on 2016/10/2.
 */

public class BezierLayout extends FrameLayout implements IHeaderView {
    public BezierLayout(Context context) {
        this(context, null);
    }

    public BezierLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    View headView;
    WaveView waveView;
    RippleView rippleView;
    RoundDotView r1;
    RoundProgressView r2;

    private void init(AttributeSet attrs) {
        /**
         * attrs  需要在xml设置什么属性  自己自定义吧  啊哈哈
         */

        /**
         * 初始化headView
         */
        headView = LayoutInflater.from(getContext()).inflate(R.layout.view_bezier, null);
        waveView = (WaveView) headView.findViewById(R.id.draweeView);
        rippleView = (RippleView) headView.findViewById(R.id.ripple);
        r1 = (RoundDotView) headView.findViewById(R.id.round1);
        r2 = (RoundProgressView) headView.findViewById(R.id.round2);
        r2.setVisibility(View.GONE);

        addView(headView);
    }

    public void setWaveColor(@ColorInt int color) {
        waveView.setWaveColor(color);
    }

    public void setRippleColor(@ColorInt int color) {
        rippleView.setRippleColor(color);
    }

    /**
     * 限定值
     *
     * @param a
     * @param b
     * @return
     */
    public float limitValue(float a, float b) {
        float valve = 0;
        final float min = Math.min(a, b);
        final float max = Math.max(a, b);
        valve = valve > min ? valve : min;
        valve = valve < max ? valve : max;
        return valve;
    }

    private ValueAnimator waveAnimator, circleAnimator;

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (waveAnimator != null) waveAnimator.cancel();
        if (circleAnimator != null) circleAnimator.cancel();
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        if (rippleView.getVisibility() == VISIBLE) rippleView.setVisibility(GONE);
        waveView.setHeadHeight((int) (headHeight * limitValue(1, fraction)));
        waveView.setWaveHeight((int) (maxHeadHeight * Math.max(0, fraction - 1)));
        waveView.invalidate();

        /*处理圈圈**/
        r1.setCir_x((int) (30 * limitValue(1, fraction)));
        r1.setVisibility(View.VISIBLE);
        r1.invalidate();

        r2.setVisibility(View.GONE);
        r2.animate().scaleX((float) 0.1);
        r2.animate().scaleY((float) 0.1);
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        waveView.setHeadHeight((int) (headHeight * limitValue(1, fraction)));
        waveView.setWaveHeight((int) (maxHeadHeight * Math.max(0, fraction - 1)));
        waveView.invalidate();

        r1.setCir_x((int) (30 * limitValue(1, fraction)));
        r1.invalidate();
    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        waveView.setHeadHeight((int) headHeight);
        waveAnimator = ValueAnimator.ofInt(waveView.getWaveHeight(), 0, -300, 0, -100, 0);
        waveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                waveView.setWaveHeight((int) animation.getAnimatedValue() / 2);
                waveView.invalidate();

            }
        });
        waveAnimator.setInterpolator(new DecelerateInterpolator());
        waveAnimator.setDuration(800);
        waveAnimator.start();
        /*处理圈圈进度条**/
        circleAnimator = ValueAnimator.ofFloat(1, 0);
        circleAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                r1.setVisibility(GONE);
                r2.setVisibility(View.VISIBLE);
                r2.animate().scaleX((float) 1.0);
                r2.animate().scaleY((float) 1.0);
                r2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        r2.startAnim();
                    }
                }, 200);
            }
        });

        circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                r1.setCir_x((int) (-value * 40));
                r1.invalidate();
            }

        });
        circleAnimator.setInterpolator(new DecelerateInterpolator());
        circleAnimator.setDuration(300);
        circleAnimator.start();
    }

    @Override
    public void onFinish(final OnAnimEndListener animEndListener) {
        r2.stopAnim();
        r2.animate().scaleX((float) 0.0);
        r2.animate().scaleY((float) 0.0);
        rippleView.setRippleEndListener(new RippleView.OnRippleEndListener() {
            @Override
            public void onRippleEnd() {
                animEndListener.onAnimEnd();
            }
        });
        rippleView.startReveal();
    }

    @Override
    public void reset() {
        if (waveAnimator != null && waveAnimator.isRunning()) waveAnimator.cancel();
        waveView.setWaveHeight(0);
        if (circleAnimator != null && circleAnimator.isRunning()) circleAnimator.cancel();
        r1.setVisibility(VISIBLE);
        r2.stopAnim();
        r2.setScaleX(0);
        r2.setScaleY(0);
        r2.setVisibility(View.GONE);
        rippleView.stopAnim();
        rippleView.setVisibility(GONE);
    }
}
