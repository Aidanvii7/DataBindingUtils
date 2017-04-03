package com.aidanvii.databinding.recyclerview

import com.aidanvii.databinding.PropertyCacher
import com.aidanvii.kotlin.DistinctObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */
fun <T> AdapterNotifierResourceProvider.adapterBindable(initialValue: T):
        ReadWriteProperty<Any?, T> = object : DistinctObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        notifyAdapterPropertyChanged(PropertyCacher.bindableResourceIdFor(property))
    }
}