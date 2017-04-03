package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

/**
 * Created by Aidan on 30/12/2016.
 */
public interface AdapterPluginManager<AdapterItem extends ResourceIdProvider> {

    @NonNull
    AdapterPlugin getPlugin(ViewDataBinding viewDataBinding);

    @NonNull
    AdapterPlugin getPlugin(AdapterItem adapterItem);

    void onViewHolderCreated(ViewDataBinding viewDataBinding);

    void onViewAttachedToWindow(ViewDataBinding viewDataBinding);

    void onViewDetachedFromWindow(ViewDataBinding viewDataBinding);

    void onViewRecycled(ViewDataBinding viewDataBinding);

    boolean onFailedToRecycleView(ViewDataBinding viewDataBinding);
}
