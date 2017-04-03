package com.aidanvii.kotlin

import java.lang.ref.SoftReference
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 18/03/17.
 */
class SoftLazyProperty<T>(private val lazyInitializer: () -> T) : ReadOnlyProperty<Any?, T> {

    private var softReference: SoftReference<T?> = SoftReference(null)

    @Synchronized
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return softReference.get() ?: getAndSetSoft()
    }

    private fun getAndSetSoft(): T {
        val referent = lazyInitializer.invoke()
        softReference = SoftReference(referent)
        return referent;
    }
}