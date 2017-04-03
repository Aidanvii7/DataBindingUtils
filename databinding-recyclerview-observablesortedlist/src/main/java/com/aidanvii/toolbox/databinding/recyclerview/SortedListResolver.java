package com.aidanvii.toolbox.databinding.recyclerview;

import android.support.annotation.NonNull;
/**
 * Created by aidan.vii@gmail.com on 23/02/17.
 */
public interface SortedListResolver<AdapterItem extends ResourceIdProvider> {

    int compare(@NonNull final AdapterItem item1, @NonNull final AdapterItem item2);

    boolean areContentsTheSame(@NonNull final AdapterItem oldItem, @NonNull final AdapterItem newItem);

    boolean areItemsTheSame(@NonNull final AdapterItem item1, @NonNull final AdapterItem item2);
}
