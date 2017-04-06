package com.aidanvii.kotlin

interface SparseCompositeLifecycleListener : LifecycleListener {

    fun putLifecycleListener(key: Int, listener: LifecycleListener)
    fun removeLifecycleListener(key: Int)
    fun clearLifecycleListeners()
}