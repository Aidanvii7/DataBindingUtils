package com.aidanvii.toolbox.databinding.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.util.SortedList;

import java.util.ArrayList;
import java.util.List;

import static com.aidanvii.toolbox.databinding.utils.Preconditions.checkArgumentIsNotNull;

/**
 * Created by aidan.vii@gmail.com on 23/02/17.
 */

public class ObservableSortedList<AdapterItem extends ResourceIdProvider> extends SortedList<AdapterItem> {

    private final SortedListCallbackDelegator<AdapterItem> callbackDelegator;

    public ObservableSortedList(@NonNull final Class<AdapterItem> klass, @NonNull final SortedListCallbackDelegator<AdapterItem> callbackDelegator) {
        super(checkArgumentIsNotNull(klass), checkArgumentIsNotNull(callbackDelegator));
        this.callbackDelegator = callbackDelegator;
    }

    public ObservableSortedList(final Class<AdapterItem> klass, final SortedListCallbackDelegator<AdapterItem> callbackDelegator, final int initialCapacity) {
        super(checkArgumentIsNotNull(klass), checkArgumentIsNotNull(callbackDelegator), initialCapacity);
        this.callbackDelegator = callbackDelegator;
    }

    public List<AdapterItem> asList() {
        List<AdapterItem> list = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            list.add(get(i));
        }
        return list;
    }

    @NonNull
    public SortedListResolver<AdapterItem> getSortedListResolver() {
        return callbackDelegator.getSortedListResolver();
    }

    public SortedListCallbackDelegator<AdapterItem> getCallbackDelegator() {
        return callbackDelegator;
    }
}
