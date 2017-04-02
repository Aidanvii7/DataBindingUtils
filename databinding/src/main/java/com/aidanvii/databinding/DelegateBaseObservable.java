package com.aidanvii.databinding;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;
/**
 * Created by aidan.vii@gmail.com on 20/02/17.
 */

public class DelegateBaseObservable implements Observable {

    private static final int ALL_PROPERTIES = 0;

    protected final Observable observable;
    private PropertyChangeRegistry propertyChangeRegistry;

    public DelegateBaseObservable() {
        this.observable = this;
    }

    public DelegateBaseObservable(@NonNull final Observable observable) {
        this.observable = observable;
    }

    @Override
    public void addOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry == null) {
            propertyChangeRegistry = new PropertyChangeRegistry();
        }
        propertyChangeRegistry.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(Observable.OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry != null) {
            propertyChangeRegistry.remove(callback);
        }
    }

    /**
     * Notifies listeners that all properties of this instance have changed.
     */
    public void notifyChange() {
        if (propertyChangeRegistry != null) {
            propertyChangeRegistry.notifyCallbacks(observable, ALL_PROPERTIES, null);
        }
    }

    /**
     * Notifies listeners that a specific property has changed. The getter for the property
     * that changes should be marked with {@link Bindable} to generate a field in
     * <code>BR</code> to be used as <code>fieldId</code>.
     *
     * @param propertyId The generated BR id for the {@link Bindable} field.
     */
    public void notifyPropertyChanged(int propertyId) {
        if (propertyChangeRegistry != null) {
            propertyChangeRegistry.notifyCallbacks(observable, propertyId, null);
        }
    }
}
