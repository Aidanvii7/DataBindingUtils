package com.aidanvii.kotlin

import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 18/03/17.
 */
class WeakProperty<T>(value: T) : ReadWriteProperty<Any?, T?> {

    var weakReference: WeakReference<T?> = WeakReference(value)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        weakReference = WeakReference(value)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = weakReference.get()
}