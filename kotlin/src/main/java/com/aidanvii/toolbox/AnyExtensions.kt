package com.aidanvii.toolbox

import android.os.Looper

/**
 * Created by aidan.vii@gmail.com on 23/02/17.
 */
fun isMainThread() = Thread.currentThread() == Looper.getMainLooper().thread

fun throwIfNotMainThread() {
    if (!isMainThread()) throw RuntimeException("must be on the main thread!")
}