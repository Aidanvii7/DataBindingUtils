package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.ObservableArrayMap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import static com.aidanvii.toolbox.databinding.recyclerview.RecyclerViewBindingAdapters.cast;
/**
 * Created by aidan.vii@gmail.com on 27/03/17.
 */

class ObservableArrayMapBinderDelegate<Key, AdapterItem extends ResourceIdProvider> implements RecyclerViewBindingAdapters.BinderDelegate<AdapterItem, ObservableArrayMapRecyclerViewBinder<Key, AdapterItem>, ObservableArrayMap<Key, AdapterItem>> {

    @Override
    public void bind(final RecyclerView recyclerView, final ObservableArrayMapRecyclerViewBinder<Key, AdapterItem> binder, final ObservableArrayMap<Key, AdapterItem> observableArrayMap) {
        observableArrayMap.addOnMapChangedCallback(binder.getBindableCollectionCallback());
        setObservableArrayMap(recyclerView, observableArrayMap);
    }

    private static <Key, AdapterItem extends ResourceIdProvider> void setObservableArrayMap(RecyclerView recyclerView, @NonNull ObservableArrayMap<Key, AdapterItem> observableArrayMap) {
        final RecyclerView.Adapter<?> curAdapter = recyclerView.getAdapter();
        if (curAdapter != null) {
            final ObservableArrayMapBindingAdapter<Key, AdapterItem> curBindingAdapterCast = cast(curAdapter);
            curBindingAdapterCast.setItems(observableArrayMap);
        }
    }
}