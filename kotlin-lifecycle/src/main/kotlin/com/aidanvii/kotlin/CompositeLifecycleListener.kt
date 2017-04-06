package com.aidanvii.kotlin

import com.aidanvii.kotlin.LifecycleListener

interface CompositeLifecycleListener : LifecycleListener {

    fun addLifecycleListener(listener: LifecycleListener)
    fun removeLifecycleListener(listener: LifecycleListener)
    fun clearLifecycleListeners()
}