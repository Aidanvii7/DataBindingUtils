package com.aidanvii.toolbox.databinding;

import android.databinding.Observable;
import android.support.annotation.NonNull;
/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */

public interface NotifiableObservable extends Observable {
    void delegateTo(@NonNull NotifiableObservable notifiableObservable);
    void notifyPropertyChanged(int propertyId);
    void notifyChange();
}