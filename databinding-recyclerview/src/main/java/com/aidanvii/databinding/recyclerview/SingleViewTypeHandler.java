package com.aidanvii.databinding.recyclerview;

import android.support.annotation.NonNull;

import com.aidanvii.databinding.utils.suppliers.Late;

import java.lang.ref.WeakReference;
/**
 * Created by aidan.vii@gmail.com on 27/03/17.
 */
class SingleViewTypeHandler<AdapterItem extends ResourceIdProvider> implements BindingAdapter.ViewTypeHandler<AdapterItem> {

    private static final int NOT_SET = -1;

    private final Late<BindingAdapter<AdapterItem>> lateBindingAdapter = Late.create();

    private int bindingId = NOT_SET;
    private int layoutId = NOT_SET;

    @Override
    public void initBindingAdapter(@NonNull BindingAdapter<AdapterItem> bindingAdapter) {
        this.lateBindingAdapter.set(bindingAdapter);
    }

    @NonNull
    private WeakReference<AdapterItem> cachedWeak = new WeakReference<>(null);

    @Override
    public int getItemViewType(int position) {
        final AdapterItem adapterItem = getLazyAdapterItem();
        lateInitResourceId(adapterItem);
        lateInitBindingId(adapterItem);
        return 0;
    }

    @NonNull
    private AdapterItem getLazyAdapterItem() {
        final AdapterItem cached = cachedWeak.get();
        final AdapterItem adapterItem;
        if (cached != null) {
            adapterItem = cached;
        } else {
            adapterItem = lateBindingAdapter.get().getAdapterItem(0);
            cachedWeak = new WeakReference<>(adapterItem);
        }
        return adapterItem;
    }

    @Override
    public int getLayoutId(int viewType) {
        return layoutId;
    }

    @Override
    public int getBindingId(int layoutId) {
        return bindingId;
    }

    private void lateInitResourceId(AdapterItem adapterItem) {
        if (layoutId == NOT_SET) {
            layoutId = getLazyAdapterItem().getLayoutId();
        }
    }

    private void lateInitBindingId(AdapterItem adapterItem) {
        if (bindingId == NOT_SET) {
            bindingId = getLazyAdapterItem().getBindingId();
        }
    }
}
