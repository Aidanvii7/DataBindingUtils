package com.aidanvii.toolbox.databinding.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.List;
/**
 * Created by aidan.vii@gmail.com on 24/10/16.
 */
public final class ListRecyclerViewBinder<AdapterItem extends ResourceIdProvider> extends RecyclerViewBinder<AdapterItem, ListBindingAdapter<AdapterItem>, List<AdapterItem>, Object> {

    private static final Class<List> COLLECTION_CLASS = List.class;

    static {
        RecyclerViewBindingAdapters.addDelegateMapping(ListRecyclerViewBinder.class, COLLECTION_CLASS, new ListBinderDelegate());
    }

    public static <AdapterItem extends ResourceIdProvider> SetBindableCollection<ListRecyclerViewBinder<AdapterItem>, AdapterItem, ListBindingAdapter<AdapterItem>, List<AdapterItem>, Object> builder() {
        return new StagedBuilder<ListRecyclerViewBinder<AdapterItem>, AdapterItem, ListBindingAdapter<AdapterItem>, List<AdapterItem>, Object>() {

            @NonNull
            @Override
            public ListRecyclerViewBinder<AdapterItem> build() {
                final ListBindingAdapter<AdapterItem> adapter = new ListBindingAdapter<>(getBindingAdapterDelegate(), getViewTypeHandler(), getBindingFactory());
                return new ListRecyclerViewBinder<>(getBindableCollection(), adapter, getLayoutManager());
            }
        };
    }

    private ListRecyclerViewBinder(final List<AdapterItem> adapterItems, final ListBindingAdapter<AdapterItem> adapter, final RecyclerView.LayoutManager layoutManager) {
        super(adapterItems, COLLECTION_CLASS, adapter, layoutManager);
    }

    @NonNull
    @Override
    public Object getBindableCollectionCallback() {
        return new Object(); // TODO rethink this
    }
}
