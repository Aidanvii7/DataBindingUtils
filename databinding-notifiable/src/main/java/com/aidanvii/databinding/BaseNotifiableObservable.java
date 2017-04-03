package com.aidanvii.databinding;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;
import android.support.annotation.NonNull;

import static com.aidanvii.databinding.utils.Preconditions.checkArgumentIsNotNull;
/**
 * Created by aidan.vii@gmail.com on 20/02/17.
 */

public class BaseNotifiableObservable implements NotifiableObservable {

    private static final int ALL_PROPERTIES = 0;

    protected final Observable observable;
    private PropertyChangeRegistry propertyChangeRegistry;

    public BaseNotifiableObservable() {
        this.observable = this;
    }

    public BaseNotifiableObservable(@NonNull final Observable observable) {
        this.observable = checkArgumentIsNotNull(observable);
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
    @Override
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
    @Override
    public void notifyPropertyChanged(int propertyId) {
        if (propertyChangeRegistry != null) {
            propertyChangeRegistry.notifyCallbacks(observable, propertyId, null);
        }
    }
}
