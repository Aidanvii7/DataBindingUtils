package com.aidanvii.databinding

import android.databinding.ViewDataBinding
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 18/03/17.
 */
class EcoBindingReadWriteProperty<Binding : ViewDataBinding>(private var binding: Binding?) : ReadWriteProperty<Any?, Binding?> {

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Binding?) {
        binding?.unbindNow()
        binding = value
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Binding? = binding
}