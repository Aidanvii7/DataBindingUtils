package com.aidanvii.databinding.imageview.aspectratio;

import android.view.View;

/**
 * Created by aidan.vii@gmail.com on 22/11/16.
 */

final class AspectRatioViewHelper {
    private float aspectRatio = 1;
    private boolean inMeasure = false;

    void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    void onMeasure(AspectRatioView aspectRatioView, int widthMeasureSpec, int heightMeasureSpec) {
        inMeasure = true;
        final int width = View.MeasureSpec.getSize(widthMeasureSpec);
        final int height = (int) (width / aspectRatio);
        aspectRatioView.setViewMeasuredDimension(width, height);
        inMeasure = false;
    }

    boolean inMeasure() {
        return inMeasure;
    }
}
