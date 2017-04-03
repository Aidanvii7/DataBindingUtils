package com.aidanvii.databinding.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
/**
 * Created by aidan.vii@gmail.com on 30/03/17.
 */
public class AdapterNotifierPlugin<Binding extends ViewDataBinding, AdapterItem extends AdapterNotifierResourceProvider> extends AdapterPluginBase<Binding, AdapterItem> {

    @Override
    public void onViewHolderBindStart(@NonNull final Binding viewDataBinding, @NonNull final BindingAdapter<AdapterItem> bindingAdapter, @NonNull final AdapterItem adapterItem, final int position) {
        adapterItem.adapterBindStart(position);
    }

    @Override
    public void onInterceptPartialBind(@NonNull final Binding viewDataBinding, @NonNull final AdapterItem adapterItem, final int position, @NonNull final int[] changedProperties) {
        for (final int changedProperty : changedProperties) {
            adapterItem.notifyAdapterPropertyChanged(changedProperty);
        }
    }

    @Override
    public void onViewHolderBindEnd(@NonNull final Binding viewDataBinding, @NonNull final AdapterItem adapterItem, final int position) {
        adapterItem.adapterBindEnd();
    }

    @Override
    public void onViewHolderPartialBindEnd(@NonNull final Binding viewDataBinding, @NonNull final AdapterItem adapterItem, final int position, final int[] changedProperties) {
        adapterItem.adapterBindEnd();
    }

    @Override
    public void bind(@NonNull final AdapterItem adapterItem, @NonNull final BindingAdapter<AdapterItem> bindingAdapter) {
        adapterItem.bindAdapter(bindingAdapter);
    }

    @Override
    public void unbind(@NonNull final AdapterItem adapterItem, @NonNull final BindingAdapter<AdapterItem> bindingAdapter) {
        adapterItem.unbindAdapter(bindingAdapter);
    }
}