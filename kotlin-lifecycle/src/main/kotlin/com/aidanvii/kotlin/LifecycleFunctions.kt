package com.aidanvii.kotlin

import com.aidanvii.kotlin.LifecycleStage

/**
 * Created by aidan.vii@gmail.com on 01/03/17.
 */

object LifecycleFunctions {
    val created: LifecycleFunction = {
        it.logD("doOnCreate")
        it.doOnCreate(false)
        it.lifecycleStage = LifecycleStage.CREATED
    }
    val started: LifecycleFunction = {
        it.logD("doOnStart")
        it.doOnStart()
        it.lifecycleStage = LifecycleStage.STARTED
    }
    val resumed: LifecycleFunction = {
        it.logD("doOnResume")
        it.doOnResume()
        it.lifecycleStage = LifecycleStage.RESUMED
    }
    val paused: LifecycleFunction = {
        it.logD("doOnPause")
        it.doOnPause()
        it.lifecycleStage = LifecycleStage.PAUSED
    }
    val stopped: LifecycleFunction = {
        it.logD("doOnStop")
        it.doOnStop()
        it.lifecycleStage = LifecycleStage.STOPPED
    }
    val destroyed: LifecycleFunction = {
        it.logD("doOnDestroy")
        it.doOnDestroy(false)
        it.lifecycleStage = LifecycleStage.DESTROYED
    }
}