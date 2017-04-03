package com.aidanvii.toolbox.databinding;

import android.databinding.Observable;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;

import java.lang.ref.WeakReference;

import static com.aidanvii.toolbox.databinding.utils.Preconditions.checkArgumentIsNotNull;

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */

public class BaseBindListenerNotifiableObservable extends BaseNotifiableObservable implements BindListenerNotifiableObservable {

    private final SparseArrayCompat<WeakReference<BindListener>> weakBindListeners = new SparseArrayCompat<>();
    private int callbackCount = 0;

    public BaseBindListenerNotifiableObservable() {
        super();
    }

    public BaseBindListenerNotifiableObservable(@NonNull final Observable observable) {
        super(observable);
    }

    @Override
    public void addBindListener(@NonNull final BindListener bindListener) {
        checkArgumentIsNotNull(bindListener);
        weakBindListeners.put(bindListener.hashCode(), Utils.weak(bindListener));
    }

    @Override
    public void removeBindListener(@NonNull final BindListener bindListener) {
        checkArgumentIsNotNull(bindListener);
        weakBindListeners.remove(bindListener.hashCode());
    }

    @Override
    public void addOnPropertyChangedCallback(final Observable.OnPropertyChangedCallback callback) {
        super.addOnPropertyChangedCallback(callback);
        if (callbackCount++ == 0) {
            onFirstPropertyCallbackAdded();
        } else {
            onAnotherPropertyCallbackAdded();
        }
    }

    @Override
    public void removeOnPropertyChangedCallback(final Observable.OnPropertyChangedCallback callback) {
        super.removeOnPropertyChangedCallback(callback);
        if (--callbackCount == 0) {
            onLastPropertyCallbackRemoved();
        } else {
            onAnotherPropertyCallbackRemoved();
        }
    }

    @Override
    public void onFirstPropertyCallbackAdded() {

    }

    @Override
    public void onAnotherPropertyCallbackAdded() {

    }

    @Override
    public void onAnotherPropertyCallbackRemoved() {

    }

    @Override
    public void onLastPropertyCallbackRemoved() {

    }
}
