package com.aidanvii.toolbox.databinding.recyclerview;

import android.databinding.Observable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import com.aidanvii.toolbox.databinding.BaseNotifiableObservable;
import com.aidanvii.toolbox.databinding.NotifiableObservable;

import java.util.ArrayList;
import java.util.List;

import static com.aidanvii.toolbox.databinding.recyclerview.BitwiseUtil.FLAG_1;
import static com.aidanvii.toolbox.databinding.recyclerview.BitwiseUtil.FLAG_2;
import static com.aidanvii.toolbox.databinding.recyclerview.BitwiseUtil.isTrue;
import static com.aidanvii.toolbox.databinding.recyclerview.BitwiseUtil.setFalse;
import static com.aidanvii.toolbox.databinding.recyclerview.BitwiseUtil.setTrue;
import static com.aidanvii.toolbox.databinding.recyclerview.Util.notEmpty;
import static com.aidanvii.toolbox.databinding.utils.Preconditions.checkArgumentIsNotNull;

/**
 * Created by aidan.vii@gmail.com on 30/03/17.
 */
class BaseAdapterNotifierResourceProvider implements AdapterNotifierResourceProvider {

    private static final int NOTIFY_PAUSED = FLAG_1();
    private static final int ADAPTER_BINDING = FLAG_2();

    private final List<Integer> pendingChangedProperties;

    @Nullable
    private NotifiableObservable notifiableObservable;
    @Nullable
    private BindingAdapter<?> adapter;
    private int flags = 0;
    private int curPosition = RecyclerView.NO_POSITION;

    private BaseAdapterNotifierResourceProvider() {
        pendingChangedProperties = new ArrayList<>();
    }

    public static BaseAdapterNotifierResourceProvider create() {
        return new BaseAdapterNotifierResourceProvider();
    }

    public static BaseAdapterNotifierResourceProvider createWithDelegate(@NonNull Observable delegate) {
        checkArgumentIsNotNull(delegate);
        final BaseAdapterNotifierResourceProvider instance = new BaseAdapterNotifierResourceProvider();
        instance.setNotifiableObservable(new BaseNotifiableObservable(delegate));
        return instance;
    }

    @Override
    public void setNotifiableObservable(@Nullable final NotifiableObservable notifiableObservable) {
        this.notifiableObservable = notifiableObservable;
    }

    @Override
    public void notifyAdapterPropertyChanged(final int property) {
        notifyAdapterPropertiesChanged(property);
    }

    @Override
    public void bindAdapter(final BindingAdapter<?> adapter) {
        this.adapter = adapter;
    }

    @Override
    public void unbindAdapter(final BindingAdapter<?> adapter) {
        // TODO support multiple adapters, api sort of implies this works
        if (this.adapter == adapter) {
            this.adapter = null;
        }
    }

    @Override
    public void adapterBindStart(final int position) {
        flags = setTrue(flags, ADAPTER_BINDING);
        curPosition = position;
    }

    @Override
    public void adapterBindEnd() {
        flags = setFalse(flags, ADAPTER_BINDING);
    }

    @Override
    public void beginBatchedUpdates() {
        flags = setTrue(flags, NOTIFY_PAUSED);
    }

    @Override
    public void endBatchedUpdates() {
        flags = setFalse(flags, NOTIFY_PAUSED);
        notifyAdapterPropertiesChanged(Util.toIntArray(pendingChangedProperties));
        pendingChangedProperties.clear();
    }

    @Override
    public int getLayoutId() {
        throw new NotImplementedException("getLayoutId");
    }

    @Override
    public int getBindingId() {
        throw new NotImplementedException("getBindingId");
    }

    private void notifyAdapterPropertiesChanged(@Nullable int... properties) {
        if (adapter != null && notEmpty(properties)) {
            if (!tryPostponePropertyChanges(properties)) {
                if (isTrue(flags, ADAPTER_BINDING) && notifiableObservable != null) {
                    for (final int property : properties) {
                        notifiableObservable.notifyPropertyChanged(property);
                    }
                } else {
                    adapter.notifyItemChanged(curPosition, properties);
                }
            }
        }
    }

    private boolean tryPostponePropertyChanges(@NonNull int[] properties) {
        final boolean notifyPaused = isTrue(flags, NOTIFY_PAUSED);
        if (notifyPaused) {
            postponePropertyChanges(properties);
        }
        return notifyPaused;
    }

    private void postponePropertyChanges(@NonNull final int[] properties) {
        for (final int property : properties) {
            pendingChangedProperties.add(property);
        }
    }
}
