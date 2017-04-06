package com.aidanvii.kotlin

import com.aidanvii.kotlin.LoggerDelegate

interface CompositeLoggerDelegate : LoggerDelegate {
    fun attachDelegate(delegate: LoggerDelegate)
    fun detachDelegate(delegate: LoggerDelegate)
}