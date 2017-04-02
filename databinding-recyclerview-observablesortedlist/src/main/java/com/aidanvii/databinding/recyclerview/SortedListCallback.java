package com.aidanvii.databinding.recyclerview;

/**
 * Created by aidan.vii@gmail.com on 23/02/17.
 */

public interface SortedListCallback {

    void onChanged(final int position, final int count);

    void onInserted(final int position, final int count);

    void onRemoved(final int position, final int count);

    void onMoved(final int fromPosition, final int toPosition);
}