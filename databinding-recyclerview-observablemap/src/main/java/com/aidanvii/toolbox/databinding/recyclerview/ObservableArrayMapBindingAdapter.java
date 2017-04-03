package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.ObservableArrayMap;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by aidan.vii@gmail.com on 21/10/16.
 */
public final class ObservableArrayMapBindingAdapter<Key, AdapterItem extends ResourceIdProvider> extends BindingAdapter<AdapterItem> {

    private ObservableArrayMap<Key, AdapterItem> items;

    ObservableArrayMapBindingAdapter(@NonNull BindingAdapterDelegate<AdapterItem> bindingAdapterDelegate, @NonNull ViewTypeHandler<AdapterItem> viewTypeHandler, @NonNull ViewDataBindingFactory<?> viewDataBindingFactory) {
        super(bindingAdapterDelegate, viewTypeHandler, viewDataBindingFactory);
    }

    @NonNull
    @Override
    protected AdapterItem getAdapterItem(int position) {
        return items.valueAt(position);
    }

    @NonNull
    @Override
    protected List<AdapterItem> getAdapterItems() {
        return new ArrayList<>(items.values());
    }

    @Override
    protected int getAdapterItemPosition(final AdapterItem adapterItem) {
        final List<AdapterItem> values = new ArrayList<>(items.values());
        return values.indexOf(adapterItem);
    }

    @NonNull
    private Map<Key, AdapterItem> getItems() {
        return items;
    }

    void setItems(@NonNull ObservableArrayMap<Key, AdapterItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }
}
