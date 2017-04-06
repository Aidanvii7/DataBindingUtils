package com.aidanvii.toolbox

/**
 * Created by aidan.vii@gmail.com on 23/02/17.
 */

inline fun Boolean.whenTrue(action: () -> Unit) {
    if (this) action.invoke()
}

inline fun Boolean.whenFalse(action: () -> Unit) {
    if (!this) action.invoke()
}