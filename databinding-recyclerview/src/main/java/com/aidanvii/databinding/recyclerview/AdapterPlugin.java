package com.aidanvii.databinding.recyclerview;

/**
 * Created by aidan.vii@gmail.com on 10/03/17.
 */

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;
/**
 * Allows the implementor to hook into various {@link RecyclerView.Adapter} callbacks that are
 * specific to the {@link ViewDataBinding} subclass and {@link AdapterItem} subclass.
 * <p>A {@link AdapterPlugin} can be attached to an {@link ClassMappedAdapterPluginManager}</p>
 * <p>See {@link ClassMappedAdapterPluginManager.Builder#addMapping(Class, Class, AdapterPlugin)} for details.</p>
 *
 * @param <Binding>
 * @param <AdapterItem extends ResourceIdProvider>
 */
public interface AdapterPlugin<Binding extends ViewDataBinding, AdapterItem extends ResourceIdProvider> {

    /**
     * hook for {@link RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)}
     * <p>called after it has been automatically created.</p>
     *
     * @param viewDataBinding
     */
    void onViewHolderCreated(@NonNull Binding viewDataBinding);

    /**
     * TODO
     *
     * @param viewDataBinding
     * @param bindingAdapter
     * @param adapterItem
     * @param position
     */
    void onViewHolderBindStart(@NonNull Binding viewDataBinding, @NonNull final BindingAdapter<AdapterItem> bindingAdapter, @NonNull AdapterItem adapterItem, int position);

    /**
     * TODO
     *
     * @param viewDataBinding
     * @param adapterItem
     * @param position
     */
    boolean onInterceptBind(@NonNull Binding viewDataBinding, @NonNull AdapterItem adapterItem, int position);

    /**
     * TODO
     *
     * @param viewDataBinding
     * @param adapterItem
     * @param position
     * @param changedProperties
     */
    void onInterceptPartialBind(@NonNull Binding viewDataBinding, @NonNull AdapterItem adapterItem, int position, @NonNull int[] changedProperties);

    /**
     * hook for {@link RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int)}
     * <p>called after it has been automatically bound.</p>
     *
     * @param viewDataBinding
     * @param adapterItem
     * @param position
     */
    void onViewHolderBindEnd(@NonNull Binding viewDataBinding, @NonNull AdapterItem adapterItem, int position);

    /**
     * hook for {@link RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder, int, List)}
     * <p>called after it has been automatically bound.</p>
     *
     * @param viewDataBinding
     * @param adapterItem
     * @param position
     * @param changedProperties
     */
    void onViewHolderPartialBindEnd(@NonNull Binding viewDataBinding, @NonNull AdapterItem adapterItem, int position, int[] changedProperties);

    /**
     * hook for {@link RecyclerView.Adapter#onViewAttachedToWindow(RecyclerView.ViewHolder)}
     *
     * @param viewDataBinding
     */
    void onViewAttachedToWindow(@NonNull Binding viewDataBinding);

    /**
     * hook for {@link RecyclerView.Adapter#onViewDetachedFromWindow(RecyclerView.ViewHolder)}
     *
     * @param viewDataBinding
     */
    void onViewDetachedFromWindow(@NonNull Binding viewDataBinding);

    /**
     * hook for {@link RecyclerView.Adapter#onViewRecycled(RecyclerView.ViewHolder)}
     *
     * @param viewDataBinding
     */
    void onViewRecycled(@NonNull Binding viewDataBinding);

    /**
     * hook for {@link RecyclerView.Adapter#onFailedToRecycleView(RecyclerView.ViewHolder)}
     *
     * @param viewDataBinding
     */
    boolean onFailedToRecycleView(@NonNull Binding viewDataBinding);

    void bind(@NonNull final AdapterItem adapterItem, @NonNull BindingAdapter<AdapterItem> bindingAdapter);

    void unbind(@NonNull final AdapterItem adapterItem, @NonNull BindingAdapter<AdapterItem> bindingAdapter);
}