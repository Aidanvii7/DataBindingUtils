package com.aidanvii.kotlin

import io.reactivex.disposables.Disposable

typealias OnChange<T> = (T) -> Unit
/**
 * Created by aidan.vii@gmail.com on 18/03/17.
 */

fun ecoDisposable(disposable: Disposable?): EcoDisposableReadWriteProperty = EcoDisposableReadWriteProperty(disposable)