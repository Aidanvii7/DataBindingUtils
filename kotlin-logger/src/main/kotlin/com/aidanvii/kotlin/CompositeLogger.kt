package com.aidanvii.kotlin

import com.aidanvii.kotlin.CompositeLoggerDelegate
import com.aidanvii.kotlin.LoggerDelegate

/**
 * Created by aidan.vii@gmail.com on 25/02/17.
 */
object CompositeLogger : CompositeLoggerDelegate {

    private var tagPrefix = ""

    // not synchronised!
    private val delegates: MutableList<LoggerDelegate> = mutableListOf()

    override fun attachDelegate(delegate: LoggerDelegate) {
        delegates.add(delegate)
    }

    override fun detachDelegate(delegate: LoggerDelegate) {
        delegates.remove(delegate)
    }

    fun setTagPrefix(tagPrefix: String) {
        this.tagPrefix = tagPrefix
    }

    override fun d(tag: String, message: String) = delegates.forEach { it.d(tagPrefix + tag, message) }
}