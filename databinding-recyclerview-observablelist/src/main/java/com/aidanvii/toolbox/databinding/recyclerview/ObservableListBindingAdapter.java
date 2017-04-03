package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aidan.vii@gmail.com on 21/10/16.
 */
public final class ObservableListBindingAdapter<AdapterItem extends ResourceIdProvider> extends BindingAdapter<AdapterItem> {

    private ObservableList<AdapterItem> items;

    ObservableListBindingAdapter(@NonNull BindingAdapterDelegate<AdapterItem> bindingAdapterDelegate, @NonNull ViewTypeHandler<AdapterItem> viewTypeHandler, @NonNull ViewDataBindingFactory<?> viewDataBindingFactory) {
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
        return new ArrayList<AdapterItem>(items);
    }

    @Override
    protected int getAdapterItemPosition(final AdapterItem adapterItem) {
        return items.indexOf(adapterItem);
    }

    @NonNull
    private List<AdapterItem> getItems() {
        return items;
    }

    public void setItems(@NonNull ObservableList<AdapterItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }
}
