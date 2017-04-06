package com.aidanvii.toolbox

import java.lang.ref.WeakReference

/**
 * Created by aidan.vii@gmail.com on 22/02/17.
 */

inline fun <T> WeakReference<T?>.safeGet(action: T.() -> Unit) = get()?.action()

fun <T> WeakReference<T>.notNull() = get() != null

inline fun <T> WeakReference<T?>.safeGetWithResult(actionNonNull: (T) -> Unit): Boolean {
    val ref = get()
    if (ref != null) {
        actionNonNull(ref)
        return true
    } else {
        return false
    }
}

inline fun <T> WeakReference<T?>.safeGetWithResult(actionNonNull: (T) -> Unit, actionNull: (WeakReference<T?>) -> Unit): Boolean {
    val ref = get()
    if (ref != null) {
        actionNonNull(ref)
        return true
    } else {
        actionNull(this)
        return false
    }
}