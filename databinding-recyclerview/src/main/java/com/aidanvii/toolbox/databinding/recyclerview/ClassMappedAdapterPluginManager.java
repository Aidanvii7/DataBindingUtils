package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import static com.aidanvii.toolbox.databinding.utils.Preconditions.checkArgumentIsNotNull;
import static com.aidanvii.toolbox.databinding.utils.Preconditions.getOrElse;
import static java.util.Collections.unmodifiableMap;

/**
 * Created by Aidan on 30/12/2016.
 */
public final class ClassMappedAdapterPluginManager<AdapterItem extends ResourceIdProvider> implements AdapterPluginManager<AdapterItem> {
    private final AdapterPlugin<?, AdapterItem> defaultPlugin;
    private final Map<Class<? extends ViewDataBinding>, AdapterPlugin<?, ? extends AdapterItem>> viewDataBindingPluginMap;
    private final Map<Class<? extends AdapterItem>, AdapterPlugin<?, ? extends AdapterItem>> adapterItemPluginMap;

    private ClassMappedAdapterPluginManager(final AdapterPlugin<?, AdapterItem> defaultPlugin, final Map<Class<? extends ViewDataBinding>, AdapterPlugin<?, ? extends AdapterItem>> viewDataBindingPluginMap, final Map<Class<? extends AdapterItem>, AdapterPlugin<?, ? extends AdapterItem>> adapterItemPluginMap) {
        this.defaultPlugin = defaultPlugin;
        this.viewDataBindingPluginMap = viewDataBindingPluginMap;
        this.adapterItemPluginMap = adapterItemPluginMap;
    }

    @NonNull
    public AdapterPlugin getPlugin(ViewDataBinding viewDataBinding) {
        final Class clazz = viewDataBinding.getClass();
        return getOrElse(viewDataBindingPluginMap.get(clazz), defaultPlugin);
    }

    @NonNull
    public AdapterPlugin getPlugin(AdapterItem adapterItem) {
        final Class clazz = adapterItem.getClass();
        return getOrElse(adapterItemPluginMap.get(clazz), defaultPlugin);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onViewHolderCreated(ViewDataBinding viewDataBinding) {
        getPlugin(viewDataBinding).onViewHolderCreated(viewDataBinding);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onViewAttachedToWindow(ViewDataBinding viewDataBinding) {
        getPlugin(viewDataBinding).onViewAttachedToWindow(viewDataBinding);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onViewDetachedFromWindow(ViewDataBinding viewDataBinding) {
        getPlugin(viewDataBinding).onViewDetachedFromWindow(viewDataBinding);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onViewRecycled(ViewDataBinding viewDataBinding) {
        getPlugin(viewDataBinding).onViewRecycled(viewDataBinding);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean onFailedToRecycleView(ViewDataBinding viewDataBinding) {
        return getPlugin(viewDataBinding).onFailedToRecycleView(viewDataBinding);
    }

    public static <AdapterItem extends ResourceIdProvider> BuilderNotReady<AdapterItem> builder() {
        return new Builder<>();
    }

    public static <AdapterItem extends ResourceIdProvider> BuilderReady<AdapterItem> builderWithDefault() {
        return new Builder<AdapterItem>().addDefaultMapping(new AdapterPluginBase<ViewDataBinding, AdapterItem>());
    }

    public interface BuilderNotReady<AdapterItem extends ResourceIdProvider> {
        /**
         * Creates a mapping between a class of type {@link ViewDataBinding} and a compatible {@link AdapterPlugin} type.
         *
         * @param adapterPlugin
         * @return
         */
        BuilderReady<AdapterItem> addDefaultMapping(@NonNull AdapterPlugin<?, AdapterItem> adapterPlugin);

    }

    public interface BuilderReady<AdapterItem extends ResourceIdProvider> extends BuilderNotReady<AdapterItem> {
        /**
         * Creates a mapping between a class of type {@link ViewDataBinding} and a compatible {@link AdapterPlugin} type.
         *
         * @param viewDataBindingClass
         * @param adapterPlugin
         * @param <Binding>
         * @return
         */
        <Binding extends ViewDataBinding> BuilderReady<AdapterItem> addMapping(@NonNull Class<Binding> viewDataBindingClass, @NonNull Class<? extends AdapterItem> adapterItemClass, @NonNull AdapterPlugin<?, ? extends AdapterItem> adapterPlugin);

        ClassMappedAdapterPluginManager<AdapterItem> build();
    }

    /**
     * Used to getDelegateForContext a new instance of {@link ClassMappedAdapterPluginManager}.
     */
    private static class Builder<AdapterItem extends ResourceIdProvider> implements BuilderReady<AdapterItem> {
        private final Map<Class<? extends ViewDataBinding>, AdapterPlugin<?, ? extends AdapterItem>> viewDataBindingHookMap;
        private final Map<Class<? extends AdapterItem>, AdapterPlugin<?, ? extends AdapterItem>> adapterItemHookMap;
        private AdapterPlugin<?, AdapterItem> defaultPlugin;

        private Builder() {
            viewDataBindingHookMap = new HashMap<>();
            adapterItemHookMap = new HashMap<>();
        }

        @Override
        public BuilderReady<AdapterItem> addDefaultMapping(@NonNull final AdapterPlugin<?, AdapterItem> adapterPlugin) {
            this.defaultPlugin = checkArgumentIsNotNull(adapterPlugin);
            return this;
        }

        @Override
        public <Binding extends ViewDataBinding> BuilderReady<AdapterItem> addMapping(@NonNull final Class<Binding> viewDataBindingClass, @NonNull final Class<? extends AdapterItem> adapterItemClass, @NonNull final AdapterPlugin<?, ? extends AdapterItem> adapterPlugin) {
            final AdapterPlugin<?, ? extends AdapterItem> bindingAdapterPlugin = checkArgumentIsNotNull(adapterPlugin);
            viewDataBindingHookMap.put(checkArgumentIsNotNull(viewDataBindingClass), bindingAdapterPlugin);
            adapterItemHookMap.put(checkArgumentIsNotNull(adapterItemClass), bindingAdapterPlugin);
            return this;
        }

        @Override
        public ClassMappedAdapterPluginManager<AdapterItem> build() {
            return new ClassMappedAdapterPluginManager<>(defaultPlugin, unmodifiableMap(viewDataBindingHookMap), unmodifiableMap(adapterItemHookMap));
        }
    }
}
