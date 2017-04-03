package com.aidanvii.kotlin

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 18/03/17.
 */
open class CheckedObservableProperty<T>(initialValue: T,
                                   private val beforeChangeCheck: (T, T) -> Boolean = { oldValue, newValue -> oldValue != newValue })
    : ObservableProperty<T>(initialValue) {


    override fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T): Boolean {
        return beforeChangeCheck(oldValue, newValue)
    }
}