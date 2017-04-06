package com.aidanvii.kotlin

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */
fun <T> softLazy(lazyInitializer: () -> T): SoftLazyProperty<T> = SoftLazyProperty(lazyInitializer)

