package com.aidanvii.databinding

import com.aidanvii.kotlin.DistinctObservableProperty
import com.aidanvii.kotlin.OnChange
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */


fun <T> NotifiableObservable.bindable(initialValue: T, onChange: OnChange<T>? = null):
        ReadWriteProperty<Any?, T> = object : DistinctObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        notifyPropertyChanged(PropertyCacher.bindableResourceIdFor(property))
        onChange?.invoke(newValue)
    }
}