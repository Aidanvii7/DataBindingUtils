package com.aidanvii.databinding.recyclerview;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aidan.vii@gmail.com on 21/10/16.
 */
public final class ListBindingAdapter<AdapterItem extends ResourceIdProvider> extends BindingAdapter<AdapterItem> {

    private List<AdapterItem> items;

    ListBindingAdapter(@NonNull BindingAdapterDelegate<AdapterItem> bindingAdapterDelegate, @NonNull ViewTypeHandler<AdapterItem> viewTypeHandler, @NonNull ViewDataBindingFactory<?> viewDataBindingFactory) {
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
        return new ArrayList<>(items);
    }

    @Override
    protected int getAdapterItemPosition(final AdapterItem adapterItem) {
        return items.indexOf(adapterItem);
    }

    @NonNull
    private List<AdapterItem> getItems() {
        return items;
    }

    void setItems(@NonNull List<AdapterItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }
}
