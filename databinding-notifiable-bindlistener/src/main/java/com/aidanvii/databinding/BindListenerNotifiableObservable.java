package com.aidanvii.databinding;

import android.support.annotation.NonNull;
/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */

public interface BindListenerNotifiableObservable extends BindListener, NotifiableObservable {
    void addBindListener(@NonNull BindListener bindListener);

    void removeBindListener(@NonNull BindListener bindListener);
}