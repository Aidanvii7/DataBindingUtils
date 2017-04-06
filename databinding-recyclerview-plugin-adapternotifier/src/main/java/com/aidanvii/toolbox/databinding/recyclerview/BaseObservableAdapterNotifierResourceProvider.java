package com.aidanvii.toolbox.databinding.recyclerview;

import android.support.annotation.NonNull;

import com.aidanvii.toolbox.databinding.BaseNotifiableObservable;
import com.aidanvii.toolbox.databinding.NotifiableObservable;

import static com.aidanvii.toolbox.databinding.utils.Preconditions.checkArgumentIsNotNull;
/**
 * Created by aidan.vii@gmail.com on 04/04/17.
 */
public class BaseObservableAdapterNotifierResourceProvider implements ObservableAdapterNotifierResourceProvider {

    private final NotifiableObservable notifiableObservable;
    private final AdapterNotifierResourceProvider adapterNotifierResourceProvider;

    private BaseObservableAdapterNotifierResourceProvider(final NotifiableObservable notifiableObservable, final AdapterNotifierResourceProvider adapterNotifierResourceProvider) {
        adapterNotifierResourceProvider.setNotifiableObservable(notifiableObservable);
        this.notifiableObservable = notifiableObservable;
        this.adapterNotifierResourceProvider = adapterNotifierResourceProvider;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private NotifiableObservable notifiableObservable;
        private AdapterNotifierResourceProvider adapterNotifierResourceProvider;

        private Builder() {
        }

        private void tryCreateDefaults() {
            this.notifiableObservable = notifiableObservable != null ? notifiableObservable : new BaseNotifiableObservable();
            this.adapterNotifierResourceProvider = adapterNotifierResourceProvider != null ? adapterNotifierResourceProvider : BaseAdapterNotifierResourceProvider.create();
        }

        public Builder setNotifiableObservable(@NonNull final NotifiableObservable notifiableObservable) {
            this.notifiableObservable = checkArgumentIsNotNull(notifiableObservable);
            return this;
        }

        public Builder setAdapterNotifierResourceProvider(@NonNull final AdapterNotifierResourceProvider adapterNotifierResourceProvider) {
            this.adapterNotifierResourceProvider = checkArgumentIsNotNull(adapterNotifierResourceProvider);
            return this;
        }

        public BaseObservableAdapterNotifierResourceProvider build() {
            tryCreateDefaults();
            return new BaseObservableAdapterNotifierResourceProvider(notifiableObservable, adapterNotifierResourceProvider);
        }
    }

    @Override
    public void delegateTo(@NonNull final NotifiableObservable notifiableObservable) {
        this.notifiableObservable.delegateTo(notifiableObservable);
    }

    @Override
    public void notifyPropertyChanged(final int propertyId) {
        notifiableObservable.notifyPropertyChanged(propertyId);
    }

    @Override
    public void notifyChange() {
        notifiableObservable.notifyChange();
    }

    @Override
    public void addOnPropertyChangedCallback(final OnPropertyChangedCallback callback) {
        notifiableObservable.addOnPropertyChangedCallback(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(final OnPropertyChangedCallback callback) {
        notifiableObservable.removeOnPropertyChangedCallback(callback);
    }

    @Override
    public void setNotifiableObservable(final NotifiableObservable notifiableObservable) {
        throw new UnsupportedOperationException("not supported by " + getClass().getSimpleName());
    }

    @Override
    public void notifyAdapterPropertyChanged(final int property) {
        adapterNotifierResourceProvider.notifyAdapterPropertyChanged(property);
    }

    @Override
    public void bindAdapter(final BindingAdapter<?> adapter) {
        adapterNotifierResourceProvider.bindAdapter(adapter);
    }

    @Override
    public void unbindAdapter(final BindingAdapter<?> adapter) {
        adapterNotifierResourceProvider.unbindAdapter(adapter);
    }

    @Override
    public void adapterBindStart(final int position) {
        adapterNotifierResourceProvider.adapterBindStart(position);
    }

    @Override
    public void adapterBindEnd() {
        adapterNotifierResourceProvider.adapterBindEnd();
    }

    @Override
    public void beginBatchedUpdates() {
        adapterNotifierResourceProvider.beginBatchedUpdates();
    }

    @Override
    public void endBatchedUpdates() {
        adapterNotifierResourceProvider.endBatchedUpdates();
    }

    @Override
    public int getLayoutId() {
        return adapterNotifierResourceProvider.getLayoutId();
    }

    @Override
    public int getBindingId() {
        return adapterNotifierResourceProvider.getBindingId();
    }
}
