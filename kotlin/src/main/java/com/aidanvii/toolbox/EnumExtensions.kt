package com.aidanvii.toolbox

/**
 * Created by aidan.vii@gmail.com on 24/03/17.
 */

fun <E : Enum<E>> Enum<E>.previousValue(values: Array<E>): E? {
    val previousIndex = ordinal - 1
    return if (ordinal > 0) values[previousIndex] else null
}

fun <E : Enum<E>> Enum<E>.nextValue(values: Array<E>): E? {
    val nextIndex = ordinal + 1
    return if (ordinal < values.size) values[nextIndex] else null
}