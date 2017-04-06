package com.aidanvii.kotlin

import com.aidanvii.kotlin.WeakProperty

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */
fun <T> weak(value: T?): WeakProperty<T?> = WeakProperty(value)