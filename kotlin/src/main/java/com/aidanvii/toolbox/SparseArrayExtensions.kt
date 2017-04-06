package com.aidanvii.toolbox

import android.util.SparseArray
import java.lang.ref.WeakReference
import java.util.*


/**
 * Created by aidan.vii@gmail.com on 20/02/17.
 */

val SparseArray<*>.max: Int get () = size() - 1;

inline fun <V> SparseArray<V>.forEachValue(action: (V) -> Unit): SparseArray<V> {
    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        action(value)
    }
    return this
}

inline fun <V> SparseArray<V>.forEachPair(action: (Int, V) -> Unit): SparseArray<V> {
    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        action(key, value)
    }
    return this
}

inline fun <V> SparseArray<V>.forEachValueThenRemove(action: (V) -> Unit): SparseArray<V> {
    val toRemove = ArrayList<Int>()
    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        action(value)
        toRemove.add(key)
    }
    toRemove.forEach { remove(it) }
    return this
}

inline fun <V> SparseArray<V>.forEachPairThenRemove(action: (Int, V) -> Unit): SparseArray<V> {
    val toRemove = ArrayList<Int>()
    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        action(key, value)
        toRemove.add(key)
    }
    toRemove.forEach { remove(it) }
    return this
}

inline fun <V> SparseArray<V>.forEachValueThenMaybeRemove(action: (V) -> Boolean): SparseArray<V> {
    val toRemove = ArrayList<Int>()
    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        val removeEntry = action(value)
        if (removeEntry) {
            toRemove.add(key)
        }
    }
    toRemove.forEach { remove(it) }
    return this
}

inline fun <V> SparseArray<V>.forEachPairThenMaybeRemove(action: (Int, V) -> Boolean): SparseArray<V> {
    val toRemove = ArrayList<Int>()
    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        val removeEntry = action(key, value)
        if (removeEntry) {
            toRemove.add(key)
        }
    }
    toRemove.forEach { remove(it) }
    return this
}

inline fun <V> SparseArray<V>.filterOnKey(filterPredicate: (Int) -> Boolean): SparseArray<V> {
    val filtered = SparseArray<V>()

    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        if (filterPredicate(key)) {
            filtered.put(key, value)
        }
    }
    return filtered
}

inline fun <V> SparseArray<V>.filterOnValue(filterPredicate: (V) -> Boolean): SparseArray<V> {
    val filtered = SparseArray<V>()

    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        if (filterPredicate(value)) {
            filtered.put(key, value)
        }
    }
    return filtered
}

inline fun <V> SparseArray<V>.filterOnPair(filterPredicate: (Int, V) -> Boolean): SparseArray<V> {
    val filtered = SparseArray<V>()

    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        if (filterPredicate(key, value)) {
            filtered.put(key, value)
        }
    }
    return filtered
}

inline fun <V1, V2> SparseArray<V1>.map(mapper: (V1) -> V2): SparseArray<V2> {
    val mapped = SparseArray<V2>()

    for (index in 0..max) {
        val key = keyAt(index)
        val value = get(key)
        mapped.put(key, mapper(value))
    }
    return mapped
}

inline fun <V> SparseArray<WeakReference<V?>>.forEachNonNull(action: (V) -> Unit): SparseArray<WeakReference<V?>> {
    for (index in 0..max) {
        val key = keyAt(index)
        val weakValue = get(key)
        weakValue.safeGet(action)
    }
    return this
}

inline fun <V> SparseArray<WeakReference<V?>>.forEachNonNullSweep(action: (V) -> Unit): SparseArray<WeakReference<V?>> {
    val toRemove = ArrayList<Int>()
    for (index in 0..max) {
        val key = keyAt(index)
        val weakValue = get(key)
        weakValue.safeGetWithResult(action).whenFalse { toRemove.add(key) }
    }
    toRemove.forEach { remove(it) }
    return this
}