package com.aidanvii.toolbox.databinding.imageview.aspectratio;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by aidan.vii@gmail.com on 22/11/16.
 */
public final class AspectRatioAppCompatImageView extends AppCompatImageView implements AspectRatioView {

    private final AspectRatioViewHelper aspectRatioViewHelper = new AspectRatioViewHelper();

    public AspectRatioAppCompatImageView(Context context) {
        super(context);
    }

    public AspectRatioAppCompatImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioAppCompatImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        aspectRatioViewHelper.onMeasure(this, widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setAspectRatio(float aspectRation) {
        aspectRatioViewHelper.setAspectRatio(aspectRation);
    }

    @Override
    public void setViewMeasuredDimension(int measuredWidth, int measuredHeight) {
        if (aspectRatioViewHelper.inMeasure()) {
            setMeasuredDimension(measuredWidth, measuredHeight);
        }
    }
}
