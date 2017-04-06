package com.aidanvii.kotlin

import java.io.Closeable

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */
fun <T : Closeable> ecoClosable(closable: T?) = EcoClosableProperty(closable)
