package com.aidanvii.databinding;

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */

public interface BindListener {
    void onFirstPropertyCallbackAdded();

    void onAnotherPropertyCallbackAdded();

    void onAnotherPropertyCallbackRemoved();

    void onLastPropertyCallbackRemoved();
}
