package com.aidanvii.kotlin

import android.util.SparseArray
import com.aidanvii.toolbox.forEachValue
import com.aidanvii.toolbox.forEachValueThenRemove
import com.aidanvii.toolbox.throwIfNotMainThread

class BaseSparseCompositeLifecycleListener : SparseCompositeLifecycleListener {
    override var initialised: Boolean = false
    override var lifecycleStage = LifecycleStage.PRE_CREATED

    private val listeners: SparseArray<LifecycleListener> = SparseArray()

    override fun putLifecycleListener(key: Int, listener: LifecycleListener) {
        removeLifecycleListener(key)
        listeners.put(key, listener)
        lifecycleStage.moveListenerToStage(listener)
    }

    override fun removeLifecycleListener(key: Int) {
        throwIfNotMainThread()
        val removedListener = listeners.get(key)
        if (removedListener != null) {
            LifecycleStage.DESTROYED.moveListenerToStage(removedListener)
        }
    }

    override fun clearLifecycleListeners() {
        throwIfNotMainThread()
        listeners.forEachValueThenRemove { LifecycleStage.DESTROYED.moveListenerToStage(it) }
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
        listeners.forEachValue { lifecycleStage.invokeLifecycleStage(it) }
    }
}