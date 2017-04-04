package com.aidanvii.toolbox.kotlin

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */

typealias OnChange<T> = (T) -> Unit

fun <T> distinctObservable(initialValue: T, onChange: OnChange<T>? = null):
        ReadWriteProperty<Any?, T> = object : DistinctObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        onChange?.invoke(newValue)
    }
}