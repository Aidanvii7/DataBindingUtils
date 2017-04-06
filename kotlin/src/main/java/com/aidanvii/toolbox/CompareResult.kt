package com.aidanvii.toolbox

/**
 * Created by aidan.vii@gmail.com on 24/03/17.
 */
enum class CompareResult {
    EQUAL, ABOVE, BELOW

}

fun <T> Comparable<T>.compareResult(other: T): CompareResult {
    val result = compareTo(other)
    return if (result < 0) {
        CompareResult.BELOW
    } else if (result > 0) {
        CompareResult.ABOVE
    } else {
        CompareResult.EQUAL
    }
}