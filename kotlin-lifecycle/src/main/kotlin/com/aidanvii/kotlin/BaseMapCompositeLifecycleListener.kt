package com.aidanvii.kotlin

import com.aidanvii.toolbox.throwIfNotMainThread

class BaseMapCompositeLifecycleListener<K> : MapCompositeLifecycleListener<K> {
    override var initialised: Boolean = false
    override var lifecycleStage = LifecycleStage.PRE_CREATED

    private val listeners: MutableMap<K, LifecycleListener> = mutableMapOf()

    override fun putLifecycleListener(key: K, listener: LifecycleListener) {
        removeLifecycleListener(key)
        listeners.put(key, listener)
        lifecycleStage.moveListenerToStage(listener)
    }

    override fun removeLifecycleListener(key: K) {
        throwIfNotMainThread()
        val removedListener = listeners.remove(key)
        if (removedListener != null) {
            LifecycleStage.DESTROYED.moveListenerToStage(removedListener)
        }
    }

    override fun clearLifecycleListeners() {
        throwIfNotMainThread()
        listeners.forEach { LifecycleStage.DESTROYED.moveListenerToStage(it.value) }
        listeners.clear()
    }

    override fun doOnCreate(didChangeConfigurations: Boolean) {
        doOnLifeCycleStage(LifecycleStage.CREATED)
    }

    override fun doOnStart() {
        doOnLifeCycleStage(LifecycleStage.STARTED)
    }

    override fun doOnResume() {
        doOnLifeCycleStage(LifecycleStage.RESUMED)
    }

    override fun doOnPause() {
        doOnLifeCycleStage(LifecycleStage.PAUSED)
    }

    override fun doOnStop() {
        doOnLifeCycleStage(LifecycleStage.STOPPED)
    }

    override fun doOnDestroy(isChangingConfigurations: Boolean) {
        doOnLifeCycleStage(LifecycleStage.DESTROYED)
    }

    private fun doOnLifeCycleStage(lifecycleStage: LifecycleStage) {
        throwIfNotMainThread()
        this.lifecycleStage = lifecycleStage
        listeners.forEach { lifecycleStage.invokeLifecycleStage(it.value) }
    }
}