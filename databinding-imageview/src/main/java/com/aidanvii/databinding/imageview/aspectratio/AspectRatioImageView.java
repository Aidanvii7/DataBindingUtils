package com.aidanvii.databinding.imageview.aspectratio;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by aidan.vii@gmail.com on 22/11/16.
 */
public final class AspectRatioImageView extends ImageView implements AspectRatioView {

    private final AspectRatioViewHelper aspectRatioViewHelper = new AspectRatioViewHelper();

    public AspectRatioImageView(Context context) {
        super(context);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AspectRatioImageView(Context context, AttributeSet attrs, int defStyleAttr) {
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
