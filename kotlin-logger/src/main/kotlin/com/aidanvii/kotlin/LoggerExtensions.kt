package com.aidanvii.kotlin

/**
 * Created by aidan.vii@gmail.com on 05/04/17.
 */

fun logD(tag: String, message: String) = CompositeLogger.d(tag, message)

fun Any.logD(message: String) = logD(javaClass.simpleName, message)
fun <T> Any.logD(t: T): T {
    logD(javaClass.simpleName, t.toString())
    return t
}
