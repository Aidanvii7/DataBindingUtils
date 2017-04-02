package com.aidanvii.databinding.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.aidanvii.databinding.utils.suppliers.Late;

/**
 * Created by aidan.vii@gmail.com on 27/03/17.
 */
final class PluginBindingAdapterDelegate<AdapterItem extends ResourceIdProvider> implements BindingAdapter.BindingAdapterDelegate<AdapterItem> {

    private final AdapterPluginManager<AdapterItem> adapterPluginManager;
    private final Late<BindingAdapter<AdapterItem>> lateBindingAdapter = Late.create();

    PluginBindingAdapterDelegate(final AdapterPluginManager<AdapterItem> adapterPluginManager) {
        this.adapterPluginManager = adapterPluginManager;
    }

    @Override
    public void initBindingAdapter(@NonNull final BindingAdapter<AdapterItem> bindingAdapter) {
        lateBindingAdapter.set(bindingAdapter);
    }

    @NonNull
    @Override
    public BindingViewHolder<?> onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        final BindingViewHolder<?> viewHolder = lateBindingAdapter.get().createBindingViewHolder(parent, viewType);
        adapterPluginManager.onViewHolderCreated(viewHolder.viewDataBinding);
        return viewHolder;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(@NonNull final BindingViewHolder<?> viewHolder, final int position) {
        final BindingAdapter<AdapterItem> bindingAdapter = lateBindingAdapter.get();
        final AdapterItem adapterItem = bindingAdapter.getAdapterItem(position);
        final AdapterPlugin adapterPlugin = adapterPluginManager.getPlugin(viewHolder.viewDataBinding);
        adapterPlugin.bind(adapterItem, bindingAdapter);
        adapterPlugin.onViewHolderBindStart(viewHolder.viewDataBinding, bindingAdapter, adapterItem, position);
        if (!adapterPlugin.onInterceptBind(viewHolder.viewDataBinding, adapterItem, position)) {
            viewHolder.viewDataBinding.setVariable(viewHolder.bindingResourceId, adapterItem);
        }
        viewHolder.viewDataBinding.executePendingBindings();
        adapterPlugin.onViewHolderBindEnd(viewHolder.viewDataBinding, adapterItem, position);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onPartialBindViewHolder(@NonNull final BindingViewHolder<?> viewHolder, final int position, @NonNull final int[] changedProperties) {
        final BindingAdapter<AdapterItem> bindingAdapter = lateBindingAdapter.get();
        final AdapterItem adapterItem = bindingAdapter.getAdapterItem(position);
        final AdapterPlugin adapterPlugin = adapterPluginManager.getPlugin(viewHolder.viewDataBinding);
        adapterPlugin.onViewHolderBindStart(viewHolder.viewDataBinding, bindingAdapter, adapterItem, position);
        adapterPlugin.onInterceptPartialBind(viewHolder.viewDataBinding, adapterItem, position, changedProperties);
        viewHolder.viewDataBinding.executePendingBindings();
        adapterPlugin.onViewHolderPartialBindEnd(viewHolder.viewDataBinding, adapterItem, position, changedProperties);
    }

    @Override
    public final void onViewAttachedToWindow(@NonNull ViewDataBinding viewDataBinding) {
        adapterPluginManager.onViewAttachedToWindow(viewDataBinding);
    }

    @Override
    public final void onViewDetachedFromWindow(@NonNull ViewDataBinding viewDataBinding) {
        adapterPluginManager.onViewDetachedFromWindow(viewDataBinding);
    }

    @Override
    public final void onViewRecycled(@NonNull ViewDataBinding viewDataBinding) {
        adapterPluginManager.onViewRecycled(viewDataBinding);
    }

    @Override
    public final boolean onFailedToRecycleView(@NonNull ViewDataBinding viewDataBinding) {
        return adapterPluginManager.onFailedToRecycleView(viewDataBinding);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void unbind() {
        for (AdapterItem adapterItem : lateBindingAdapter.get().getAdapterItems()) {
            adapterPluginManager.getPlugin(adapterItem).unbind(adapterItem, lateBindingAdapter.get());
        }
    }
}
