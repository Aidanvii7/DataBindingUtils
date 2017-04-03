package com.aidanvii.toolbox.databinding.imageview;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.aidanvii.toolbox.databinding.imageview.aspectratio.AspectRatioAppCompatImageView;
import com.aidanvii.toolbox.databinding.imageview.aspectratio.AspectRatioImageView;

/**
 * Created by aidan.vii@gmail.com on 18/10/16.
 * <p> TODO perhaps change the name of this class as it clashes with {@link android.databinding.adapters.ImageViewBindingAdapter}</p>
 */
public interface ImageViewUrlBindingAdapter {

    String BIND_SRC_URL_CENTER_CROP = "bind_srcUrlCenterCrop";
    String BIND_ASPECT_RATIO = "bind_aspectRatio";

    @BindingAdapter({BIND_SRC_URL_CENTER_CROP})
    void loadImageCenterCrop(ImageView imageView, String imageUrl);

    /**
     * Loads an image into an {@link ImageView} and center crops it
     *
     * @param aspectImageView the {@link AspectRatioAppCompatImageView} where the image will be loaded
     * @param imageUrl        the url of the image
     * @param aspectRatio     the aspectRatio of the image
     */
    @BindingAdapter({BIND_SRC_URL_CENTER_CROP, BIND_ASPECT_RATIO})
    void loadImageCenterCrop(AspectRatioAppCompatImageView aspectImageView, String imageUrl, float aspectRatio);


    /**
     * Loads an image into an {@link ImageView} and center crops it
     *
     * @param aspectImageView the {@link AspectRatioImageView} where the image will be loaded
     * @param imageUrl        the url of the image
     * @param aspectRatio     the aspectRatio of the image
     */
    @BindingAdapter({BIND_SRC_URL_CENTER_CROP, BIND_ASPECT_RATIO})
    void loadImageCenterCrop(AspectRatioImageView aspectImageView, String imageUrl, float aspectRatio);

}