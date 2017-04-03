package com.aidanvii.toolbox.kotlin

import java.io.Closeable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 18/03/17.
 */
class EcoClosableReadWriteProperty<T : Closeable>(private var closable: T?) : ReadWriteProperty<Any?, T?> {

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        closable?.close()
        closable = value
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = closable
}