package com.aidanvii.databinding.imageview;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.aidanvii.databinding.imageview.aspectratio.AspectRatioAppCompatImageView;
import com.aidanvii.databinding.imageview.aspectratio.AspectRatioImageView;
import com.aidanvii.databinding.imageview.aspectratio.AspectRatioView;

/**
 * Created by aidan.vii@gmail.com on 18/11/16.
 */
public final class GlideImageViewUrlBindingAdapter implements ImageViewUrlBindingAdapter {

    @Override
    public void loadImageCenterCrop(ImageView imageView, String imageUrl) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void loadImageCenterCrop(AspectRatioAppCompatImageView aspectImageView, String imageUrl, float aspectRatio) {
        loadImageCenterCropInternal(aspectImageView, imageUrl, aspectRatio);
    }

    @Override
    public void loadImageCenterCrop(AspectRatioImageView aspectImageView, String imageUrl, float aspectRatio) {
        loadImageCenterCropInternal(aspectImageView, imageUrl, aspectRatio);
    }

    private <AspectImageView extends ImageView & AspectRatioView>
    void loadImageCenterCropInternal(@NonNull AspectImageView aspectImageView, @NonNull String imageUrl, float aspectRatio) {
        aspectImageView.setAspectRatio(aspectRatio);
        Glide.with(aspectImageView.getContext())
                .load(imageUrl)
                .centerCrop()
                .into(aspectImageView);
    }
}
