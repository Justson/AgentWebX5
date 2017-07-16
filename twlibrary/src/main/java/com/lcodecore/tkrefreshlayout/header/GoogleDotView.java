package com.lcodecore.tkrefreshlayout.header;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.lcodecore.tkrefreshlayout.R;
import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.utils.DensityUtil;

/**
 * Created by lcodecore on 2016/10/2.
 */

public class GoogleDotView extends View implements IHeaderView {
    private Paint mPath;
    private float r;
    private int num = 5;

    public void setCir_x(int cir_x) {
        this.cir_x = cir_x;
    }

    private int cir_x;

    float fraction1;
    float fraction2;
    boolean animating = false;

    public GoogleDotView(Context context) {
        this(context, null, 0);
    }

    public GoogleDotView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoogleDotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    ValueAnimator animator1, animator2;

    private void init() {
        r = DensityUtil.dp2px(getContext(), 4);

        mPath = new Paint();
        mPath.setAntiAlias(true);
        mPath.setColor(Color.rgb(114, 114, 114));

        animator1 = ValueAnimator.ofFloat(1f, 1.2f, 1f, 0.8f);
        animator1.setDuration(800);
        animator1.setInterpolator(new DecelerateInterpolator());
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction1 = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator1.setRepeatCount(ValueAnimator.INFINITE);
        animator1.setRepeatMode(ValueAnimator.REVERSE);

        animator2 = ValueAnimator.ofFloat(1f, 0.8f, 1f, 1.2f);
        animator2.setDuration(800);
        animator2.setInterpolator(new DecelerateInterpolator());
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction2 = (float) animation.getAnimatedValue();
            }
        });
        animator2.setRepeatCount(ValueAnimator.INFINITE);
        animator2.setRepeatMode(ValueAnimator.REVERSE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getMeasuredWidth() / num - 10;
        for (int i = 0; i < num; i++) {
            if (animating) {
                switch (i) {
                    //                    case 0:
                    //                        mPath.setAlpha(35);
                    //                        mPath.setColor(getResources().getColor(R.color.Orange));
                    //                        canvas.drawCircle(getMeasuredWidth() / 2 - cir_x * 3 - 3 * w / 3 * 2, getMeasuredHeight() / 2, r*fraction2, mPath);
                    //                        break;
                    case 0:
                        mPath.setAlpha(105);
                        mPath.setColor(getResources().getColor(R.color.Yellow));
                        canvas.drawCircle(getMeasuredWidth() / 2 - cir_x * 2 - 2 * w / 3 * 2, getMeasuredHeight() / 2, r * fraction2, mPath);
                        break;
                    case 1:
                        mPath.setAlpha(145);
                        mPath.setColor(getResources().getColor(R.color.Green));
                        canvas.drawCircle(getMeasuredWidth() / 2 - cir_x * 1 - w / 3 * 2, getMeasuredHeight() / 2, r * fraction2, mPath);
                        break;
                    case 2:
                        mPath.setAlpha(255);
                        mPath.setColor(getResources().getColor(R.color.Blue));
                        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r * fraction1, mPath);
                        break;
                    case 3:
                        mPath.setAlpha(145);
                        mPath.setColor(getResources().getColor(R.color.Orange));
                        canvas.drawCircle(getMeasuredWidth() / 2 + cir_x * 1 + w / 3 * 2, getMeasuredHeight() / 2, r * fraction2, mPath);
                        break;
                    case 4:
                        mPath.setAlpha(105);
                        mPath.setColor(getResources().getColor(R.color.Yellow));
                        canvas.drawCircle(getMeasuredWidth() / 2 + cir_x * 2 + 2 * w / 3 * 2, getMeasuredHeight() / 2, r * fraction2, mPath);
                        break;
                    //                    case 6:
                    //                        mPath.setAlpha(35);
                    //                        mPath.setColor(getResources().getColor(R.color.Green));
                    //                        canvas.drawCircle(getMeasuredWidth() / 2 + cir_x * 3 + 3 * w / 3 * 2, getMeasuredHeight() / 2, r*fraction2, mPath);
                    //                        break;
                }
            } else {
                switch (i) {
                    //                    case 0:
                    //                        mPath.setAlpha(35);
                    //                        mPath.setColor(getResources().getColor(R.color.Orange));
                    //                        canvas.drawCircle(getMeasuredWidth() / 2 - cir_x * 3 - 3 * w / 3 * 2, getMeasuredHeight() / 2, r, mPath);
                    //                        break;
                    case 0:
                        mPath.setAlpha(105);
                        mPath.setColor(getResources().getColor(R.color.Yellow));
                        canvas.drawCircle(getMeasuredWidth() / 2 - cir_x * 2 - 2 * w / 3 * 2, getMeasuredHeight() / 2, r, mPath);
                        break;
                    case 1:
                        mPath.setAlpha(145);
                        mPath.setColor(getResources().getColor(R.color.Green));
                        canvas.drawCircle(getMeasuredWidth() / 2 - cir_x * 1 - w / 3 * 2, getMeasuredHeight() / 2, r, mPath);
                        break;
                    case 2:
                        mPath.setAlpha(255);
                        mPath.setColor(getResources().getColor(R.color.Blue));
                        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, r, mPath);
                        break;
                    case 3:
                        mPath.setAlpha(145);
                        mPath.setColor(getResources().getColor(R.color.Orange));
                        canvas.drawCircle(getMeasuredWidth() / 2 + cir_x * 1 + w / 3 * 2, getMeasuredHeight() / 2, r, mPath);
                        break;
                    case 4:
                        mPath.setAlpha(105);
                        mPath.setColor(getResources().getColor(R.color.Yellow));
                        canvas.drawCircle(getMeasuredWidth() / 2 + cir_x * 2 + 2 * w / 3 * 2, getMeasuredHeight() / 2, r, mPath);
                        break;
                    //                    case 6:
                    //                        mPath.setAlpha(35);
                    //                        mPath.setColor(getResources().getColor(R.color.Green));
                    //                        canvas.drawCircle(getMeasuredWidth() / 2 + cir_x * 3 + 3 * w / 3 * 2, getMeasuredHeight() / 2, r, mPath);
                    //                        break;
                }
            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (animator1 != null) animator1.cancel();
        if (animator2 != null) animator2.cancel();
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        setScaleX(1 + fraction / 2);
        setScaleY(1 + fraction / 2);
        animating = false;
        if (animator1.isRunning()) {
            animator1.cancel();
            invalidate();
        }
        if (animator2.isRunning()) animator2.cancel();
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        setScaleX(1 + fraction / 2);
        setScaleY(1 + fraction / 2);
        if (fraction < 1.0f) {
            animating = false;
            if (animator1.isRunning()) {
                animator1.cancel();
                invalidate();
            }
            if (animator2.isRunning()) animator2.cancel();
        }
    }


    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        animating = true;
        animator1.start();
        animator2.start();
    }

    @Override
    public void onFinish(OnAnimEndListener listener) {
        listener.onAnimEnd();
    }

    @Override
    public void reset() {
        animating = false;
        if (animator1.isRunning()) animator1.cancel();
        if (animator2.isRunning()) animator2.cancel();
        invalidate();
    }
}
