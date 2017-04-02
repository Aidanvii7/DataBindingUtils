package com.aidanvii.databinding.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by aidan.vii@gmail.com on 18/10/16.
 */
public final class BindingViewHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public final int bindingResourceId;
    public final Binding viewDataBinding;

    BindingViewHolder(int bindingResourceId, Binding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.bindingResourceId = bindingResourceId;
        this.viewDataBinding = viewDataBinding;
    }
}