package com.aidanvii.kotlin

/**
 * Created by aidan.vii@gmail.com on 24/03/17.
 */
class StatefulLifecycleListener : LifecycleListener {
    override var initialised: Boolean = false
    override var lifecycleStage: LifecycleStage = LifecycleStage.PRE_CREATED
}