package com.aidanvii.kotlin

import android.util.Log
import com.aidanvii.kotlin.LoggerDelegate

class AndroidLoggerDelegate : LoggerDelegate {
    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }
}