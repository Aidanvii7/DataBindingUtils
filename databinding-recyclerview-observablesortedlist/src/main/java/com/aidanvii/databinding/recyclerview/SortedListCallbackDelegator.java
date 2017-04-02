package com.aidanvii.databinding.recyclerview;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.SortedList;
/**
 * Created by aidan.vii@gmail.com on 23/02/17.
 */
public final class SortedListCallbackDelegator<AdapterItem extends ResourceIdProvider> extends SortedList.Callback<AdapterItem> {

    // TODO support dynamic switching of sorting algorithm (not sure if this is possible with SortedList)
    private final SortedListResolver<AdapterItem> sortedListResolver;

    @Nullable
    private SortedListCallback delegate = null;

    public SortedListCallbackDelegator(final SortedListResolver<AdapterItem> sortedListResolver) {
        this.sortedListResolver = sortedListResolver;
    }

    @Override
    public int compare(final AdapterItem item1, final AdapterItem item2) {
        return sortedListResolver.compare(item1, item2);
    }

    @Override
    public boolean areContentsTheSame(final AdapterItem oldItem, final AdapterItem newItem) {
        return sortedListResolver.areContentsTheSame(oldItem, newItem);
    }

    @Override
    public boolean areItemsTheSame(final AdapterItem item1, final AdapterItem item2) {
        return sortedListResolver.areItemsTheSame(item1, item2);
    }

    @Override
    public void onChanged(final int position, final int count) {
        if (delegate != null) {
            delegate.onChanged(position, count);
        }
    }

    @Override
    public void onInserted(final int position, final int count) {
        if (delegate != null) {
            delegate.onInserted(position, count);
        }
    }

    @Override
    public void onRemoved(final int position, final int count) {
        if (delegate != null) {
            delegate.onRemoved(position, count);
        }
    }

    @Override
    public void onMoved(final int fromPosition, final int toPosition) {
        if (delegate != null) {
            delegate.onMoved(fromPosition, toPosition);
        }
    }

    @NonNull
    public SortedListResolver<AdapterItem> getSortedListResolver() {
        return sortedListResolver;
    }

    public void setDelegate(@Nullable SortedListCallback delegate) {
        this.delegate = delegate;
    }

}
