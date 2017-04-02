package com.aidanvii.databinding.recyclerview;

import android.support.annotation.LayoutRes;
/**
 * Created by aidan.vii@gmail.com on 10/03/17.
 */

public interface ResourceIdProvider {

    /**
     * Called when the adapter is creating a new BindingViewHolder which requires a layout resource ID.
     *
     * @return A layout resource corresponding to the viewType.
     */
    @LayoutRes
    int getLayoutId();

    /**
     * Called when the adapter is creating a new BindingViewHolder which requires a viewDataBinding resource ID.
     *
     * @return A layout resource corresponding to the viewType.
     */
    int getBindingId();

}
