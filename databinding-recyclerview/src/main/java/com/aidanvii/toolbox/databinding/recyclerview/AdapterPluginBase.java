package com.aidanvii.toolbox.databinding.recyclerview;

/**
 * Created by aidan.vii@gmail.com on 10/03/17.
 */
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

public class AdapterPluginBase<Binding extends ViewDataBinding, AdapterItem extends ResourceIdProvider> implements AdapterPlugin<Binding, AdapterItem> {

    @Override
    public void onViewHolderCreated(@NonNull Binding viewDataBinding) {
    }

    @Override
    public void onViewHolderBindStart(@NonNull Binding viewDataBinding, @NonNull final BindingAdapter<AdapterItem> bindingAdapter, @NonNull AdapterItem adapterItem, int position) {
    }

    @Override
    public boolean onInterceptBind(@NonNull Binding viewDataBinding, @NonNull AdapterItem adapterItem, int position) {
        return false;
    }

    @Override
    public void onInterceptPartialBind(@NonNull Binding viewDataBinding, @NonNull AdapterItem adapterItem, int position, @NonNull int[] changedProperties) {
    }

    @Override
    public void onViewHolderBindEnd(@NonNull Binding viewDataBinding, @NonNull AdapterItem adapterItem, int position) {
    }

    @Override
    public void onViewHolderPartialBindEnd(@NonNull Binding viewDataBinding, @NonNull AdapterItem adapterItem, int position, int[] changedProperties) {
    }

    @Override
    public void onViewAttachedToWindow(@NonNull Binding viewDataBinding) {
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull Binding viewDataBinding) {
    }

    @Override
    public void onViewRecycled(@NonNull Binding viewDataBinding) {
    }

    @Override
    public boolean onFailedToRecycleView(@NonNull Binding viewDataBinding) {
        return false;
    }

    @Override
    public void bind(@NonNull final AdapterItem adapterItem, @NonNull BindingAdapter<AdapterItem> bindingAdapter) {

    }

    @Override
    public void unbind(@NonNull final AdapterItem adapterItem, @NonNull BindingAdapter<AdapterItem> bindingAdapter) {

    }
}

