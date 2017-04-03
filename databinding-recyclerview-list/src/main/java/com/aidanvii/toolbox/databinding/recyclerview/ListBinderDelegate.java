package com.aidanvii.toolbox.databinding.recyclerview;

import android.support.v7.widget.RecyclerView;

import java.util.List;
/**
 * Created by aidan.vii@gmail.com on 27/03/17.
 */

class ListBinderDelegate<AdapterItem extends ResourceIdProvider> implements RecyclerViewBindingAdapters.BinderDelegate<AdapterItem, ListRecyclerViewBinder<AdapterItem>, List<AdapterItem>> {

    @Override
    public void bind(final RecyclerView recyclerView, final ListRecyclerViewBinder<AdapterItem> binder, final List<AdapterItem> adapterItems) {

    }
}
