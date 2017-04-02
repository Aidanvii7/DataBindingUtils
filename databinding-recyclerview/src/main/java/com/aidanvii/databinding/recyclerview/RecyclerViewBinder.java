package com.aidanvii.databinding.recyclerview;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import static com.aidanvii.databinding.utils.Preconditions.checkArgumentIsNotNull;

/**
 * Created by aidan.vii@gmail.com on 10/03/17.
 */

public abstract class RecyclerViewBinder<AdapterItem extends ResourceIdProvider, Adapter extends BindingAdapter<AdapterItem>, BindableCollection, BindableCollectionCallback> {

    private final BindableCollection bindableCollection;
    private final Class<? super BindableCollection> bindableCollectionClass;
    private final Adapter adapter;
    private final RecyclerView.LayoutManager layoutManager;

    public RecyclerViewBinder(BindableCollection bindableCollection, final Class<? super BindableCollection> bindableCollectionClass, final Adapter adapter, final RecyclerView.LayoutManager layoutManager) {
        this.bindableCollection = bindableCollection;
        this.bindableCollectionClass = bindableCollectionClass;
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    @NonNull
    public final Adapter getAdapter() {
        return adapter;
    }

    @NonNull
    public final BindableCollection getBindableCollection() {
        return bindableCollection;
    }

    public final Class<? super BindableCollection> getCollectionClass() {
        return bindableCollectionClass;
    }

    @NonNull
    public abstract BindableCollectionCallback getBindableCollectionCallback();

    @NonNull
    public final RecyclerView.LayoutManager getLayoutManager() {
        return layoutManager;
    }

    @CallSuper
    public void unbind() {
        getAdapter().unbind();
    }

    public interface SetBindableCollection<Binder extends RecyclerViewBinder<AdapterItem, Adapter, BindableCollection, BindableCollectionCallback>, AdapterItem extends ResourceIdProvider, Adapter extends BindingAdapter<AdapterItem>, BindableCollection, BindableCollectionCallback> {
        SetLayoutManager<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> setBindableCollection(@NonNull BindableCollection bindableCollection);
    }

    public interface SetLayoutManager<Binder extends RecyclerViewBinder<AdapterItem, Adapter, BindableCollection, BindableCollectionCallback>, AdapterItem extends ResourceIdProvider, Adapter extends BindingAdapter<AdapterItem>, BindableCollection, BindableCollectionCallback> {
        Build<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> setLayoutManager(@NonNull RecyclerView.LayoutManager layoutManager);
    }

    public interface Build<Binder extends RecyclerViewBinder<AdapterItem, Adapter, BindableCollection, BindableCollectionCallback>, AdapterItem extends ResourceIdProvider, Adapter extends BindingAdapter<AdapterItem>, BindableCollection, BindableCollectionCallback> {

        @NonNull
        Build<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> enableSingleViewType();

        @NonNull
        Build<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> setViewDataBindingFactory(@NonNull BindingAdapter.ViewDataBindingFactory<?> bindingFactory);

        Build<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> setAdapterPluginManager(@NonNull AdapterPluginManager<AdapterItem> adapterPluginManager);

        @NonNull
        Binder build();

    }

    public static abstract class StagedBuilder<Binder extends RecyclerViewBinder<AdapterItem, Adapter, BindableCollection, BindableCollectionCallback>, AdapterItem extends ResourceIdProvider, Adapter extends BindingAdapter<AdapterItem>, BindableCollection, BindableCollectionCallback> implements SetBindableCollection<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback>, SetLayoutManager<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback>, Build<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> {

        private BindableCollection bindableCollection;
        private RecyclerView.LayoutManager layoutManager;
        private BindingAdapter.ViewDataBindingFactory<?> bindingFactory;
        private boolean hasMultiViewTypes = true;
        private AdapterPluginManager<AdapterItem> adapterPluginManager = ClassMappedAdapterPluginManager.<AdapterItem>builderWithDefault().build();

        @NonNull
        protected final BindingAdapter.ViewTypeHandler<AdapterItem> getViewTypeHandler() {
            return hasMultiViewTypes ? new MultiViewTypeHandler<AdapterItem>() : new SingleViewTypeHandler<AdapterItem>();
        }

        @NonNull
        protected final BindingAdapter.BindingAdapterDelegate<AdapterItem> getBindingAdapterDelegate() {
            return adapterPluginManager != null ? new PluginBindingAdapterDelegate<AdapterItem>(adapterPluginManager) : new LiteBindingAdapterDelegate<AdapterItem>();
        }

        @NonNull
        protected final BindingAdapter.ViewDataBindingFactory<?> getBindingFactory() {
            return bindingFactory != null ? bindingFactory : new DefaultViewDataBindingFactory();
        }

        @NonNull
        protected final BindableCollection getBindableCollection() {
            return bindableCollection;
        }

        @NonNull
        protected final RecyclerView.LayoutManager getLayoutManager() {
            return layoutManager;
        }

        @Override
        public final SetLayoutManager<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> setBindableCollection(@NonNull final BindableCollection bindableCollection) {
            this.bindableCollection = checkArgumentIsNotNull(bindableCollection);
            return this;
        }

        @Override
        public final Build<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> setLayoutManager(@NonNull final RecyclerView.LayoutManager layoutManager) {
            this.layoutManager = checkArgumentIsNotNull(layoutManager);
            return this;
        }

        @NonNull
        @Override
        public final Build<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> enableSingleViewType() {
            this.hasMultiViewTypes = false;
            return this;
        }

        @NonNull
        @Override
        public final Build<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> setViewDataBindingFactory(@NonNull final BindingAdapter.ViewDataBindingFactory<?> bindingFactory) {
            this.bindingFactory = checkArgumentIsNotNull(bindingFactory);
            return this;
        }

        @Override
        public final Build<Binder, AdapterItem, Adapter, BindableCollection, BindableCollectionCallback> setAdapterPluginManager(@NonNull final AdapterPluginManager<AdapterItem> adapterPluginManager) {
            this.adapterPluginManager = checkArgumentIsNotNull(adapterPluginManager);
            return this;
        }

    }
}
