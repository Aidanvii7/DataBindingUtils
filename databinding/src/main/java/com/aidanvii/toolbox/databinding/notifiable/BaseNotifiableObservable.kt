package com.aidanvii.toolbox.databinding.notifiable

import android.databinding.BaseObservable
import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.aidanvii.toolbox.utils.Provider

const val ALL_PROPERTIES = 0

/**
 * A convenience class that implements the [NotifiableObservable] interface.
 *
 * It is ultimately an alternative implementation of [BaseObservable],
 * where the [notifyChange] and [notifyPropertyChanged] methods are exposed through the [NotifiableObservable] interface.
 *
 * Unlike [BaseObservable], this class is final, so delegation must be used.
 *
 * See [NotifiableObservable] for usage details.
 */
class BaseNotifiableObservable internal constructor(
        changeRegistryProvider: Provider<PropertyChangeRegistry>,
        changeRegistryThreadSafetyMode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE) : NotifiableObservable {
    constructor(changeRegistryThreadSafetyMode: LazyThreadSafetyMode = LazyThreadSafetyMode.NONE)
            : this({ PropertyChangeRegistry() }, changeRegistryThreadSafetyMode)

    private val changeRegistry: PropertyChangeRegistry by lazy(changeRegistryThreadSafetyMode, changeRegistryProvider)

    private lateinit var delegator: NotifiableObservable

    override fun initDelegator(delegator: NotifiableObservable) {
        this.delegator = delegator
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        changeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        changeRegistry.remove(callback)
    }

    override fun notifyChange() {
        changeRegistry.notifyChange(delegator, ALL_PROPERTIES)
    }

    override fun notifyPropertyChanged(propertyId: Int) {
        changeRegistry.notifyChange(delegator, propertyId)
    }
}