package com.aidanvii.toolbox.databinding;

import android.databinding.Observable;
/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */

public interface NotifiableObservable extends Observable {
    void notifyPropertyChanged(int propertyId);
    void notifyChange();
}