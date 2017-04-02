package com.aidanvii.databinding;

import android.databinding.Observable;
import android.support.annotation.NonNull;
/**
 * Created by aidan.mcwilliams@vimn.com on 02/04/17.
 */

class SynchronizedDelegateBaseObservable extends DelegateBaseObservable {

    public SynchronizedDelegateBaseObservable() {
        super();
    }

    public SynchronizedDelegateBaseObservable(@NonNull final Observable observable) {
    }

    @Override
    public void addOnPropertyChangedCallback(final Observable.OnPropertyChangedCallback callback) {
        synchronized (observable) {
            super.addOnPropertyChangedCallback(callback);
        }
    }

    @Override
    public void removeOnPropertyChangedCallback(final Observable.OnPropertyChangedCallback callback) {
        synchronized (observable) {
            super.removeOnPropertyChangedCallback(callback);
        }
    }

    @Override
    public void notifyChange() {
        synchronized (observable) {
            super.notifyChange();
        }
    }

}
