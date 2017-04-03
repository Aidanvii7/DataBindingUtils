package com.aidanvii.toolbox.databinding.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import static com.aidanvii.toolbox.databinding.recyclerview.RecyclerViewBindingAdapters.cast;
/**
 * Created by aidan.vii@gmail.com on 27/03/17.
 */
class ObservableSortedListBinderDelegate<AdapterItem extends ResourceIdProvider> implements RecyclerViewBindingAdapters.BinderDelegate<AdapterItem, ObservableSortedListRecyclerViewBinder<AdapterItem>, ObservableSortedList<AdapterItem>> {

    @Override
    public void bind(final RecyclerView recyclerView, final ObservableSortedListRecyclerViewBinder<AdapterItem> binder, final ObservableSortedList<AdapterItem> observableSortedList) {
        observableSortedList.getCallbackDelegator().setDelegate(binder.getBindableCollectionCallback());
        setSortedList(recyclerView, observableSortedList);
    }

    private void setSortedList(RecyclerView recyclerView, @NonNull ObservableSortedList<AdapterItem> sortedList) {
        final RecyclerView.Adapter<?> curAdapter = recyclerView.getAdapter();
        if (curAdapter != null) {
            final ObservableSortedListBindingAdapter<AdapterItem> curBindingAdapterCast = cast(curAdapter);
            curBindingAdapterCast.setItems(sortedList);
        }
    }
}