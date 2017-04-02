package com.aidanvii.databinding.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.aidanvii.databinding.utils.suppliers.Lazy;
import com.aidanvii.databinding.utils.suppliers.Supplier;

/**
 * Created by aidan.vii@gmail.com on 24/10/16.
 */
public final class ObservableSortedListRecyclerViewBinder<AdapterItem extends ResourceIdProvider> extends RecyclerViewBinder<AdapterItem, ObservableSortedListBindingAdapter<AdapterItem>, ObservableSortedList<AdapterItem>, SortedListCallback> {

    private static final Class<ObservableSortedList> COLLECTION_CLASS = ObservableSortedList.class;

    static {
        RecyclerViewBindingAdapters.addDelegateMapping(ObservableSortedListRecyclerViewBinder.class, COLLECTION_CLASS, new ObservableSortedListBinderDelegate());
    }

    private final Lazy<SortedListCallback> sortedListCallbackSupplier = Lazy.create(new Supplier<SortedListCallback>() {
        @Override
        public SortedListCallback get() {
            return createSortedListCallback();
        }
    });

    public static <AdapterItem extends ResourceIdProvider> SetBindableCollection<ObservableSortedListRecyclerViewBinder<AdapterItem>, AdapterItem, ObservableSortedListBindingAdapter<AdapterItem>, ObservableSortedList<AdapterItem>, SortedListCallback> builder() {
        return new StagedBuilder<ObservableSortedListRecyclerViewBinder<AdapterItem>, AdapterItem, ObservableSortedListBindingAdapter<AdapterItem>, ObservableSortedList<AdapterItem>, SortedListCallback>() {
            @NonNull
            @Override
            public ObservableSortedListRecyclerViewBinder<AdapterItem> build() {
                final ObservableSortedListBindingAdapter<AdapterItem> adapter = new ObservableSortedListBindingAdapter<>(getBindingAdapterDelegate(), getViewTypeHandler(), getBindingFactory());
                return new ObservableSortedListRecyclerViewBinder<>(getBindableCollection(), adapter, getLayoutManager());
            }
        };
    }

    private ObservableSortedListRecyclerViewBinder(final ObservableSortedList<AdapterItem> adapterItemObservableSortedList, final ObservableSortedListBindingAdapter<AdapterItem> adapter, final RecyclerView.LayoutManager layoutManager) {
        super(adapterItemObservableSortedList, COLLECTION_CLASS, adapter, layoutManager);
    }

    @NonNull
    @Override
    public SortedListCallback getBindableCollectionCallback() {
        return sortedListCallbackSupplier.get();
    }

    @Override
    public void unbind() {
        super.unbind();
        getBindableCollection().getCallbackDelegator().setDelegate(null);
    }

    private SortedListCallback createSortedListCallback() {
        return new SortedListCallback() {
            @Override
            public void onChanged(final int position, final int count) {
                getAdapter().notifyItemRangeChanged(position, count);
            }

            @Override
            public void onInserted(final int position, final int count) {
                getAdapter().notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(final int position, final int count) {
                getAdapter().notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(final int fromPosition, final int toPosition) {
                getAdapter().notifyItemMoved(fromPosition, toPosition);
            }
        };
    }
}
