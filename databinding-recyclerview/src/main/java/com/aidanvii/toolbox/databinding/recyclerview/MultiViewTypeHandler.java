package com.aidanvii.toolbox.databinding.recyclerview;

import android.support.annotation.NonNull;
import android.util.SparseIntArray;

import com.aidanvii.toolbox.databinding.utils.suppliers.Late;
/**
 * Created by aidan.vii@gmail.com on 27/03/17.
 */
class MultiViewTypeHandler<AdapterItem extends ResourceIdProvider> implements BindingAdapter.ViewTypeHandler<AdapterItem> {

    private final SparseIntArray cachedBindingIds = new SparseIntArray();
    private final Late<BindingAdapter<AdapterItem>> lateBindingAdapter = Late.create();

    @Override
    public void initBindingAdapter(@NonNull BindingAdapter<AdapterItem> bindingAdapter) {
        this.lateBindingAdapter.set(bindingAdapter);
    }

    @Override
    public int getItemViewType(int position) {
        // RecyclerView will call through here a lot. We have to consider that first we are doing a lookup
        // inside a collection of unknown size followed by a binary search in the SparseIntArray
        final AdapterItem adapterItem = lateBindingAdapter.get().getAdapterItem(position);
        final int layoutId = adapterItem.getLayoutId();
        cachedBindingIds.put(layoutId, adapterItem.getBindingId());
        return layoutId;
    }

    @Override
    public int getLayoutId(int viewType) {
        return viewType;
    }

    @Override
    public int getBindingId(int layoutId) {
        return cachedBindingIds.get(layoutId);
    }
}
