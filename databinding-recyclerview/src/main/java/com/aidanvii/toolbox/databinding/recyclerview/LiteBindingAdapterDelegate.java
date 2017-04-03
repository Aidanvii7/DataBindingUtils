package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.aidanvii.toolbox.databinding.utils.suppliers.Late;
/**
 * Created by aidan.vii@gmail.com on 27/03/17.
 */
class LiteBindingAdapterDelegate<AdapterItem extends ResourceIdProvider> implements BindingAdapter.BindingAdapterDelegate<AdapterItem> {

    private final Late<BindingAdapter<AdapterItem>> lateBindingAdapter = Late.create();

    @Override
    public void initBindingAdapter(@NonNull final BindingAdapter<AdapterItem> bindingAdapter) {
        lateBindingAdapter.set(bindingAdapter);
    }

    @NonNull
    @Override
    public BindingViewHolder<?> onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        return lateBindingAdapter.get().createBindingViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final BindingViewHolder<?> viewHolder, final int position) {
        final BindingAdapter<AdapterItem> bindingAdapter = lateBindingAdapter.get();
        final AdapterItem adapterItem = bindingAdapter.getAdapterItem(position);
        viewHolder.viewDataBinding.setVariable(viewHolder.bindingResourceId, adapterItem);
        viewHolder.viewDataBinding.executePendingBindings();
    }

    @Override
    public void onPartialBindViewHolder(@NonNull final BindingViewHolder<?> viewHolder, final int position, @NonNull final int[] changedProperties) {
        // TODO add support for partial binding?
        onBindViewHolder(viewHolder, position);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull final ViewDataBinding viewDataBinding) {

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull final ViewDataBinding viewDataBinding) {

    }

    @Override
    public void onViewRecycled(@NonNull final ViewDataBinding viewDataBinding) {

    }

    @Override
    public boolean onFailedToRecycleView(@NonNull final ViewDataBinding viewDataBinding) {
        return false;
    }

    @Override
    public void unbind() {

    }
}
