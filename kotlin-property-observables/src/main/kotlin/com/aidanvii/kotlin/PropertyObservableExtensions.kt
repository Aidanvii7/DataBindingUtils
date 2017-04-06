package com.aidanvii.kotlin

import com.aidanvii.toolbox.ChangePredicate
import com.aidanvii.toolbox.OnChange
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */
fun <T> checkedObservable(initialValue: T, changePredicate: ChangePredicate<T>) = CheckedObservableProperty<T>(initialValue, changePredicate)

fun <T> distinctObservable(initialValue: T, onChange: OnChange<T>? = null):
        ReadWriteProperty<Any?, T> = object : DistinctObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        onChange?.invoke(newValue)
    }
}

