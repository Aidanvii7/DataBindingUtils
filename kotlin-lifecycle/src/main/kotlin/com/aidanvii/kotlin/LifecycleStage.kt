package com.aidanvii.kotlin

import com.aidanvii.toolbox.CompareResult
import com.aidanvii.toolbox.compareResult
import com.aidanvii.toolbox.nextValue
import com.aidanvii.toolbox.previousValue
import com.aidanvii.kotlin.LifecycleFunctions.created
import com.aidanvii.kotlin.LifecycleFunctions.destroyed
import com.aidanvii.kotlin.LifecycleFunctions.paused
import com.aidanvii.kotlin.LifecycleFunctions.resumed
import com.aidanvii.kotlin.LifecycleFunctions.started
import com.aidanvii.kotlin.LifecycleFunctions.stopped
import com.aidanvii.kotlin.LifecycleListener

enum class LifecycleStage(private val lifecycleFunc: LifecycleFunction) {

    PRE_CREATED({ }),
    CREATED({ created(it) }),
    STARTED({ started(it) }),
    RESUMED({ resumed(it) }),
    PAUSED({ paused(it) }),
    STOPPED({ stopped(it) }),
    DESTROYED({ destroyed(it) });

    fun moveListenerToStage(listener: LifecycleListener) {
        if (listener.initialised) {
            invokeLifecycleStage(listener)
        } else {
            when (compareResult(listener.lifecycleStage)) {
                CompareResult.BELOW -> moveListenerBackwardToStage(listener)
                CompareResult.ABOVE -> moveListenerForwardToStage(listener)
            }
        }
    }

    fun invokeLifecycleStage(listener: LifecycleListener) {
        listener.initialised = true
        lifecycleFunc.invoke(listener)
    }

    private fun moveListenerForwardToStage(listener: LifecycleListener) {
        while (listener.lifecycleStage < this) {
            if (listener.lifecycleStage.invokeNext(listener)) break
        }
    }

    private fun moveListenerBackwardToStage(listener: LifecycleListener) {
        while (listener.lifecycleStage > this) {
            if (listener.lifecycleStage.invokeLast(listener)) break
        }
    }

    private fun invokeLast(listener: LifecycleListener): Boolean {
        this.previousValue(values())?.run {
            invokeLifecycleStage(listener)
            return false
        }
        return true
    }

    private fun invokeNext(listener: LifecycleListener): Boolean {
        this.nextValue(values())?.run {
            invokeLifecycleStage(listener)
            return false
        }
        return true
    }
}