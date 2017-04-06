package com.aidanvii.kotlin

import com.aidanvii.kotlin.LifecycleStage

/**
 * Created by aidan.vii@gmail.com on 28/02/17.
 * TODO getDelegateForContext Bundle wrapper so listeners can save and restore state without having a direct dependency on Bundle
 */
interface LifecycleListener {
    var initialised: Boolean
    var lifecycleStage: LifecycleStage
    fun doOnCreate(didChangeConfigurations: Boolean = false) {}
    fun doOnStart() {}
    fun doOnResume() {}
    fun doOnPause() {}
    fun doOnStop() {}
    fun doOnDestroy(isChangingConfigurations: Boolean = false) {}
}