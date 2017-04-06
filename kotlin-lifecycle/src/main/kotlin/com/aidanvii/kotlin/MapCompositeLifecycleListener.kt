package com.aidanvii.kotlin

interface MapCompositeLifecycleListener<K> : LifecycleListener {

    fun putLifecycleListener(key: K, listener: LifecycleListener)
    fun removeLifecycleListener(key: K)
    fun clearLifecycleListeners()
}