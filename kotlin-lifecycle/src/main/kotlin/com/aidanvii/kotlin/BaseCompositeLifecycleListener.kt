package com.aidanvii.kotlin

import com.aidanvii.toolbox.forEachThenRemove
import com.aidanvii.toolbox.throwIfNotMainThread
import java.util.*

class BaseCompositeLifecycleListener : CompositeLifecycleListener {
    override var initialised: Boolean = false
    override var lifecycleStage = LifecycleStage.PRE_CREATED

    private val listeners: MutableList<LifecycleListener> = ArrayList()

    override fun addLifecycleListener(listener: LifecycleListener) {
        throwIfNotMainThread()
        if (!listeners.contains(listener)) {
            listeners.add(listener)
            lifecycleStage.moveListenerToStage(listener)
        }
    }

    override fun removeLifecycleListener(listener: LifecycleListener) {
        throwIfNotMainThread()
        if (listeners.remove(listener)) {
            LifecycleStage.DESTROYED.moveListenerToStage(listener)
        }
    }

    override fun clearLifecycleListeners() {
        throwIfNotMainThread()
        listeners.forEachThenRemove { LifecycleStage.DESTROYED.moveListenerToStage(it) }
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
        listeners.forEach({ lifecycleStage.invokeLifecycleStage(it) })
    }
}