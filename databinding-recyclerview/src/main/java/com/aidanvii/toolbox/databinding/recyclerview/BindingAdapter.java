package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import static com.aidanvii.toolbox.databinding.utils.Preconditions.checkArgumentIsNotNull;
import static com.aidanvii.toolbox.databinding.utils.Preconditions.checkNotNull;

/**
 * Created by aidan.vii@gmail.com on 21/11/16.
 */
public abstract class BindingAdapter<AdapterItem extends ResourceIdProvider> extends RecyclerView.Adapter<BindingViewHolder<?>> {

    public interface ViewDataBindingFactory<Binding extends ViewDataBinding> {
        @NonNull
        Binding createViewDataBinding(@NonNull LayoutInflater inflater, int layoutId, @NonNull ViewGroup parent);
    }

    interface ViewTypeHandler<AdapterItem extends ResourceIdProvider> {

        void initBindingAdapter(@NonNull BindingAdapter<AdapterItem> bindingAdapter);

        int getItemViewType(final int position);

        int getLayoutId(int viewType);

        int getBindingId(int layoutId);

    }

    interface BindingAdapterDelegate<AdapterItem extends ResourceIdProvider> {

        void initBindingAdapter(@NonNull BindingAdapter<AdapterItem> bindingAdapter);

        @NonNull
        BindingViewHolder<?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

        void onBindViewHolder(@NonNull BindingViewHolder<?> viewHolder, int position);

        void onPartialBindViewHolder(@NonNull final BindingViewHolder<?> viewHolder, final int position, @NonNull final int[] changedProperties);

        void onViewAttachedToWindow(@NonNull ViewDataBinding viewDataBinding);

        void onViewDetachedFromWindow(@NonNull ViewDataBinding viewDataBinding);

        void onViewRecycled(@NonNull ViewDataBinding viewDataBinding);

        boolean onFailedToRecycleView(@NonNull ViewDataBinding viewDataBinding);

        void unbind();

    }

    private final BindingAdapterDelegate<AdapterItem> bindingAdapterDelegate;
    private final ViewTypeHandler<AdapterItem> viewTypeHandler;
    private final ViewDataBindingFactory<?> viewDataBindingFactory;

    protected BindingAdapter(@NonNull BindingAdapterDelegate<AdapterItem> bindingAdapterDelegate, @NonNull ViewTypeHandler<AdapterItem> viewTypeHandler, @NonNull ViewDataBindingFactory<?> viewDataBindingFactory) {
        this.bindingAdapterDelegate = checkArgumentIsNotNull(bindingAdapterDelegate);
        this.viewTypeHandler = checkArgumentIsNotNull(viewTypeHandler);
        this.viewDataBindingFactory = checkArgumentIsNotNull(viewDataBindingFactory);

        bindingAdapterDelegate.initBindingAdapter(this);
        viewTypeHandler.initBindingAdapter(this);
    }

    @Override
    public final BindingViewHolder<?> onCreateViewHolder(ViewGroup parent, int viewType) {
        return checkNotNull(bindingAdapterDelegate.onCreateViewHolder(parent, viewType));
    }

    @Override
    public final void onBindViewHolder(BindingViewHolder<?> viewHolder, int position, List<Object> payloads) {
        final int[] changedProperties = getChangedProperties(payloads);
        if (changedProperties != null) {
            onPartialBindViewHolder(viewHolder, position, changedProperties);
        } else {
            onBindViewHolder(viewHolder, position);
        }
    }

    @SuppressWarnings("unchecked")
    private void onPartialBindViewHolder(final BindingViewHolder<?> viewHolder, final int position, final int[] changedProperties) {
        bindingAdapterDelegate.onPartialBindViewHolder(viewHolder, position, changedProperties);
    }

    @Override
    public final void onBindViewHolder(BindingViewHolder<?> viewHolder, int position) {
        bindingAdapterDelegate.onBindViewHolder(viewHolder, position);
    }

    @Nullable
    private int[] getChangedProperties(final List<Object> payloads) {
        if (payloads.size() == 1) {
            final Object payload = payloads.get(0);
            if (payload instanceof int[]) {
                return (int[]) payload;
            } else {
                throw new IllegalArgumentException("change payload is not an int[]");
            }
        }
        return null;
    }

    @Override
    public final int getItemViewType(int position) {
        return viewTypeHandler.getItemViewType(position);
    }

    @Override
    public final void onViewAttachedToWindow(BindingViewHolder<?> holder) {
        bindingAdapterDelegate.onViewAttachedToWindow(holder.viewDataBinding);
    }

    @Override
    public final void onViewDetachedFromWindow(BindingViewHolder<?> holder) {
        bindingAdapterDelegate.onViewDetachedFromWindow(holder.viewDataBinding);
    }

    @Override
    public final void onViewRecycled(BindingViewHolder<?> holder) {
        bindingAdapterDelegate.onViewRecycled(holder.viewDataBinding);
    }

    @Override
    public final boolean onFailedToRecycleView(BindingViewHolder<?> holder) {
        return bindingAdapterDelegate.onFailedToRecycleView(holder.viewDataBinding);
    }

    @SuppressWarnings("unchecked")
    void unbind() {
        bindingAdapterDelegate.unbind();
    }

    /**
     * Called when the adapter is binding an item to the {@link BindingViewHolder<?>} which requires a viewDataBinding resource ID.
     * <p>The {@link AdapterItem} should be relative to the position.</p>
     *
     * @param position the position in the data set to determine the {@link AdapterItem} and viewDataBinding resource ID.
     * @return
     */
    @NonNull
    protected abstract AdapterItem getAdapterItem(int position);

    @NonNull
    protected abstract List<AdapterItem> getAdapterItems();

    protected abstract int getAdapterItemPosition(AdapterItem adapterItem);

    BindingViewHolder<?> createBindingViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        final int layoutId = viewTypeHandler.getLayoutId(viewType);
        final int bindingId = viewTypeHandler.getBindingId(layoutId);
        final ViewDataBinding viewDataBinding = viewDataBindingFactory.createViewDataBinding(inflater, layoutId, parent);

        return new BindingViewHolder<>(bindingId, viewDataBinding);
    }
}
