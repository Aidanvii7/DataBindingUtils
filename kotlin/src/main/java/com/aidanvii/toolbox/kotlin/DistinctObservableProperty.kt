package com.aidanvii.toolbox.kotlin

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 18/03/17.
 */
open class DistinctObservableProperty<T>(initialValue: T) : ObservableProperty<T>(initialValue) {

    override fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T): Boolean {
        return oldValue != newValue
    }
}