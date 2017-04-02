package com.aidanvii.databinding.recyclerview;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableMap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.aidanvii.databinding.utils.suppliers.Lazy;
import com.aidanvii.databinding.utils.suppliers.Supplier;

/**
 * Created by aidan.vii@gmail.com on 24/10/16.
 */
public final class ObservableArrayMapRecyclerViewBinder<Key, AdapterItem extends ResourceIdProvider> extends RecyclerViewBinder<AdapterItem, ObservableArrayMapBindingAdapter<Key, AdapterItem>, ObservableArrayMap<Key, AdapterItem>, ObservableArrayMap.OnMapChangedCallback<ObservableArrayMap<Key, AdapterItem>, Key, AdapterItem>> {

    private static final Class<ObservableArrayMap> COLLECTION_CLASS = ObservableArrayMap.class;

    static {
        RecyclerViewBindingAdapters.addDelegateMapping(ObservableArrayMapRecyclerViewBinder.class, COLLECTION_CLASS, new ObservableArrayMapBinderDelegate());
    }

    private Lazy<ObservableMap.OnMapChangedCallback<ObservableArrayMap<Key, AdapterItem>, Key, AdapterItem>> observableMapCallbackSupplier = Lazy.create(new Supplier<ObservableMap.OnMapChangedCallback<ObservableArrayMap<Key, AdapterItem>, Key, AdapterItem>>() {
        @Override
        public ObservableMap.OnMapChangedCallback<ObservableArrayMap<Key, AdapterItem>, Key, AdapterItem> get() {
            return createObservableMapCallback();
        }
    });

    public static <Key, AdapterItem extends ResourceIdProvider> SetBindableCollection<ObservableArrayMapRecyclerViewBinder<Key, AdapterItem>, AdapterItem, ObservableArrayMapBindingAdapter<Key, AdapterItem>, ObservableArrayMap<Key, AdapterItem>, ObservableArrayMap.OnMapChangedCallback<ObservableArrayMap<Key, AdapterItem>, Key, AdapterItem>> builder() {
        return new StagedBuilder<ObservableArrayMapRecyclerViewBinder<Key, AdapterItem>, AdapterItem, ObservableArrayMapBindingAdapter<Key, AdapterItem>, ObservableArrayMap<Key, AdapterItem>, ObservableArrayMap.OnMapChangedCallback<ObservableArrayMap<Key, AdapterItem>, Key, AdapterItem>>() {
            @NonNull
            @Override
            public ObservableArrayMapRecyclerViewBinder<Key, AdapterItem> build() {
                final ObservableArrayMapBindingAdapter<Key, AdapterItem> adapter = new ObservableArrayMapBindingAdapter<>(getBindingAdapterDelegate(), getViewTypeHandler(), getBindingFactory());
                return new ObservableArrayMapRecyclerViewBinder<>(getBindableCollection(), adapter, getLayoutManager());
            }
        };
    }

    private ObservableArrayMapRecyclerViewBinder(final ObservableArrayMap<Key, AdapterItem> keyAdapterItemObservableArrayMap, final ObservableArrayMapBindingAdapter<Key, AdapterItem> adapter, final RecyclerView.LayoutManager layoutManager) {
        super(keyAdapterItemObservableArrayMap, COLLECTION_CLASS, adapter, layoutManager);
    }

    @NonNull
    @Override
    public ObservableArrayMap.OnMapChangedCallback<ObservableArrayMap<Key, AdapterItem>, Key, AdapterItem> getBindableCollectionCallback() {
        return observableMapCallbackSupplier.get();
    }

    public void unbind() {
        super.unbind();
        getBindableCollection().removeOnMapChangedCallback(getBindableCollectionCallback());
    }

    private ObservableMap.OnMapChangedCallback<ObservableArrayMap<Key, AdapterItem>, Key, AdapterItem> createObservableMapCallback() {
        return new ObservableMap.OnMapChangedCallback<ObservableArrayMap<Key, AdapterItem>, Key, AdapterItem>() {

            private int lastSize;

            @Override
            public void onMapChanged(final ObservableArrayMap<Key, AdapterItem> sender, final Key key) {
                final int currentSize = sender.size();
                final int index = sender.indexOfKey(key);
                if (index < 0) {
                    onItemRangeRemoved(sender, index, 1);
                } else if (lastSize != currentSize) {
                    onItemRangeInserted(sender, index, 1);
                } else {
                    onItemRangeChanged(sender, index, 1);
                }
                lastSize = currentSize;
            }

            private void onChanged(ObservableArrayMap<Key, AdapterItem> sender) {
                getAdapter().notifyDataSetChanged();
            }

            private void onItemRangeChanged(ObservableArrayMap<Key, AdapterItem> sender, int positionStart, int itemCount) {
                getAdapter().notifyItemRangeChanged(positionStart, itemCount);
            }

            private void onItemRangeInserted(ObservableArrayMap<Key, AdapterItem> sender, int positionStart, int itemCount) {
                getAdapter().notifyItemRangeInserted(positionStart, itemCount);
            }

            private void onItemRangeRemoved(ObservableArrayMap<Key, AdapterItem> sender, int positionStart, int itemCount) {
                getAdapter().notifyItemRangeRemoved(positionStart, itemCount);
            }
        };
    }
}
