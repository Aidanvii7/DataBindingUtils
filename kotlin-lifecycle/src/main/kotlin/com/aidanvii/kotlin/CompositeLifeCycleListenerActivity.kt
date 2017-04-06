package com.aidanvii.kotlin

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import com.aidanvii.kotlin.BaseCompositeLifecycleListener
import com.aidanvii.kotlin.CompositeLifecycleListener

/**
 * Created by aidan.vii@gmail.com on 28/02/17.
 */

open class CompositeLifeCycleListenerActivity : AppCompatActivity(), CompositeLifecycleListener by BaseCompositeLifecycleListener() {

    companion object {
        val DID_CHANGE_CONFIG = "DID_CHANGE_CONFIG"
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val didChangeConfig = savedInstanceState?.getBoolean(DID_CHANGE_CONFIG) ?: false
        when (didChangeConfig) {
            true -> maybeDoOnCreate()
            false -> doOnCreate(false)
        }
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        doOnStart()
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        doOnResume()
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putBoolean(DID_CHANGE_CONFIG, isChangingConfigurations)
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        doOnPause()
    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        doOnStop()
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        when (isChangingConfigurations) {
            true -> maybeDoOnDestroy()
            false -> doOnDestroy(false)
        }
    }

    private fun maybeDoOnCreate() {
        when (suppressDestroyCreateOnConfigChange) {
            true -> logD("doOnCreate suppressed")
            false -> doOnCreate(true)
        }
    }

    private fun maybeDoOnDestroy() {
        when (suppressDestroyCreateOnConfigChange) {
            true -> logD("doOnDestroy suppressed")
            false -> doOnDestroy(true)
        }
    }

    open val suppressDestroyCreateOnConfigChange: Boolean = false

}
