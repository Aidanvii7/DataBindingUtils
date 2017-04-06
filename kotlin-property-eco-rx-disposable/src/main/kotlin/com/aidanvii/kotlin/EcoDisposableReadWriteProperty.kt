package com.aidanvii.kotlin

import io.reactivex.disposables.Disposable
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 18/03/17.
 */
class EcoDisposableReadWriteProperty(private var disposable: Disposable?) : ReadWriteProperty<Any?, Disposable?> {

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Disposable?) {
        disposable?.dispose()
        disposable = value
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Disposable? = disposable
}