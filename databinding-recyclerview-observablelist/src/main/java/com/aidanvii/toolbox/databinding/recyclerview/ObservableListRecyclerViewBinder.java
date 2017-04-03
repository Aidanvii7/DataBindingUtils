package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.ObservableList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.aidanvii.toolbox.databinding.utils.suppliers.Lazy;
import com.aidanvii.toolbox.databinding.utils.suppliers.Supplier;

/**
 * Created by aidan.vii@gmail.com on 24/10/16.
 */
public final class ObservableListRecyclerViewBinder<AdapterItem extends ResourceIdProvider> extends RecyclerViewBinder<AdapterItem, ObservableListBindingAdapter<AdapterItem>, ObservableList<AdapterItem>, ObservableList.OnListChangedCallback<ObservableList<AdapterItem>>> {

    private static final Class<ObservableList> COLLECTION_CLASS = ObservableList.class;

    static {
        RecyclerViewBindingAdapters.addDelegateMapping(ObservableListRecyclerViewBinder.class, COLLECTION_CLASS, new ObservableListBinderDelegate());
    }

    private Lazy<ObservableList.OnListChangedCallback<ObservableList<AdapterItem>>> observableListCallbackSupplier = Lazy.create(new Supplier<ObservableList.OnListChangedCallback<ObservableList<AdapterItem>>>() {
        @Override
        public ObservableList.OnListChangedCallback<ObservableList<AdapterItem>> get() {
            return createObservableListCallback();
        }
    });

    public static <AdapterItem extends ResourceIdProvider> SetBindableCollection<ObservableListRecyclerViewBinder<AdapterItem>, AdapterItem, ObservableListBindingAdapter<AdapterItem>, ObservableList<AdapterItem>, ObservableList.OnListChangedCallback<ObservableList<AdapterItem>>> builder() {
        return new StagedBuilder<ObservableListRecyclerViewBinder<AdapterItem>, AdapterItem, ObservableListBindingAdapter<AdapterItem>, ObservableList<AdapterItem>, ObservableList.OnListChangedCallback<ObservableList<AdapterItem>>>() {
            @NonNull
            @Override
            public ObservableListRecyclerViewBinder<AdapterItem> build() {
                final ObservableListBindingAdapter<AdapterItem> adapter = new ObservableListBindingAdapter<>(getBindingAdapterDelegate(), getViewTypeHandler(), getBindingFactory());
                return new ObservableListRecyclerViewBinder<>(getBindableCollection(), adapter, getLayoutManager());
            }
        };
    }

    private ObservableListRecyclerViewBinder(final ObservableList<AdapterItem> observableList, final ObservableListBindingAdapter<AdapterItem> observableListBindingAdapter, final RecyclerView.LayoutManager layoutManager) {
        super(observableList, COLLECTION_CLASS, observableListBindingAdapter, layoutManager);
    }

    @NonNull
    @Override
    public ObservableList.OnListChangedCallback<ObservableList<AdapterItem>> getBindableCollectionCallback() {
        return observableListCallbackSupplier.get();
    }

    @Override
    public void unbind() {
        super.unbind();
        getBindableCollection().removeOnListChangedCallback(getBindableCollectionCallback());
    }

    private ObservableList.OnListChangedCallback<ObservableList<AdapterItem>> createObservableListCallback() {
        return new ObservableList.OnListChangedCallback<ObservableList<AdapterItem>>() {
            @Override
            public void onChanged(ObservableList<AdapterItem> sender) {
                getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<AdapterItem> sender, int positionStart, int itemCount) {
                getAdapter().notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<AdapterItem> sender, int positionStart, int itemCount) {
                getAdapter().notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<AdapterItem> sender, int fromPosition, int toPosition, int itemCount) {
                // TODO addDelegateMapping support for when itemCount > 1
                if (itemCount > 1) {
                    throw new UnsupportedOperationException("cannot move batches of items at once.");
                }
                getAdapter().notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<AdapterItem> sender, int positionStart, int itemCount) {
                getAdapter().notifyItemRangeRemoved(positionStart, itemCount);
            }
        };
    }
}
