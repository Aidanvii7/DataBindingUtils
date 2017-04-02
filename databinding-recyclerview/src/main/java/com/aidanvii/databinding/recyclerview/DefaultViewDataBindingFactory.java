package com.aidanvii.databinding.recyclerview;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by aidan.vii@gmail.com on 07/12/16.
 */

final class DefaultViewDataBindingFactory implements BindingAdapter.ViewDataBindingFactory<ViewDataBinding> {

    @NonNull
    @Override
    public ViewDataBinding createViewDataBinding(@NonNull LayoutInflater inflater, int layoutId, @NonNull ViewGroup parent) {
        return DataBindingUtil.inflate(inflater, layoutId, parent, false);
    }
}
