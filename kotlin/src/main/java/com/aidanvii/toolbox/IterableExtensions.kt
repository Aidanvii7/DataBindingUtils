package com.aidanvii.toolbox

/**
 * Created by aidan.vii@gmail.com on 24/02/17.
 */

inline fun <T> MutableCollection<T>.forEachThenRemove(action: (T) -> Unit) {
    val iterator = iterator()
    while (iterator.hasNext()) {
        val entry = iterator.next()
        action.invoke(entry)
        iterator.remove()
    }
}

inline fun <T> MutableCollection<T>.forEachThenMaybeRemove(action: (T) -> Boolean) {
    val iterator = iterator()
    while (iterator.hasNext()) {
        val entry = iterator.next()
        if (action.invoke(entry)) iterator.remove()
    }
}

