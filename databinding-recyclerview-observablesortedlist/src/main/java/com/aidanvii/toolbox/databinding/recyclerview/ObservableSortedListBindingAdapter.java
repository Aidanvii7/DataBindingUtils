package com.aidanvii.toolbox.databinding.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;

import java.util.List;

/**
 * Created by aidan.vii@gmail.com on 21/10/16.
 */
public final class ObservableSortedListBindingAdapter<AdapterItem extends ResourceIdProvider> extends BindingAdapter<AdapterItem> {

    private ObservableSortedList<AdapterItem> items;

    ObservableSortedListBindingAdapter(@NonNull BindingAdapterDelegate<AdapterItem> bindingAdapterDelegate, @NonNull ViewTypeHandler<AdapterItem> viewTypeHandler, @NonNull ViewDataBindingFactory<?> viewDataBindingFactory) {
        super(bindingAdapterDelegate, viewTypeHandler, viewDataBindingFactory);
    }

    @NonNull
    @Override
    protected AdapterItem getAdapterItem(int position) {
        return items.get(position);
    }

    @NonNull
    @Override
    protected List<AdapterItem> getAdapterItems() {
        return items.asList();
    }

    @Override
    protected int getAdapterItemPosition(final AdapterItem adapterItem) {
        return items.indexOf(adapterItem);
    }

    @NonNull
    private SortedList<AdapterItem> getItems() {
        return items;
    }

    public void setItems(@NonNull ObservableSortedList<AdapterItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }
}
