package com.aidanvii.toolbox.databinding

import android.databinding.BaseObservable
import android.databinding.ViewDataBinding
import com.aidanvii.kotlin.DistinctObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */
fun ViewDataBinding.unbindNow() {
    unbind()
    executePendingBindings()
}

fun <Binding : ViewDataBinding> ecoBinding(binding: Binding?): EcoBindingReadWriteProperty<Binding> = EcoBindingReadWriteProperty(binding)

fun <T> BaseObservable.bindable(initialValue: T):
        ReadWriteProperty<Any?, T> = object : DistinctObservableProperty<T>(initialValue) {
    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        notifyPropertyChanged(PropertyCacher.bindableResourceIdFor(property))
    }
}