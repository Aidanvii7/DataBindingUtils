package com.aidanvii.databinding.recyclerview;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import static com.aidanvii.databinding.recyclerview.RecyclerViewBindingAdapters.cast;
/**
 * Created by aidan.vii@gmail.com on 27/03/17.
 */

class ObservableListBinderDelegate<AdapterItem extends ResourceIdProvider> implements RecyclerViewBindingAdapters.BinderDelegate<AdapterItem, ObservableListRecyclerViewBinder<AdapterItem>, ObservableList<AdapterItem>> {

    @Override
    public void bind(final RecyclerView recyclerView, final ObservableListRecyclerViewBinder<AdapterItem> binder, final ObservableList<AdapterItem> observableList) {
        observableList.addOnListChangedCallback(binder.getBindableCollectionCallback());
        setObservableList(recyclerView, observableList);
    }

    private void setObservableList(RecyclerView recyclerView, @NonNull ObservableList<AdapterItem> observableList) {
        final RecyclerView.Adapter<?> curAdapter = recyclerView.getAdapter();
        if (curAdapter != null) {
            final ObservableListBindingAdapter<AdapterItem> curBindingAdapterCast = cast(curAdapter);
            curBindingAdapterCast.setItems(observableList);
        }
    }
}