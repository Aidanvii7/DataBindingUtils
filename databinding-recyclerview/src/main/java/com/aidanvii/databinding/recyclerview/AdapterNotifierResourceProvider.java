package com.aidanvii.databinding.recyclerview;

import com.aidanvii.databinding.DelegateBaseObservable;
/**
 * Created by aidan.vii@gmail.com on 30/03/17.
 */

public interface AdapterNotifierResourceProvider extends ResourceIdProvider {

    void setDelegateBaseObservable(DelegateBaseObservable delegateBaseObservable);

    void notifyAdapterPropertyChanged(int property);

    void bindAdapter(BindingAdapter<?> adapter);

    void unbindAdapter(BindingAdapter<?> adapter);

    void adapterBindStart(int position);

    void adapterBindEnd();

    void pauseNotifications();

    void resumeNotifications();

}
