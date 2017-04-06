package com.aidanvii.toolbox

/**
 * Created by aidan.vii@gmail.com on 05/04/17.
 */


/**
 * The callback which is called before a change to the property value is attempted.
 * The value of the property hasn't been changed yet, when this callback is invoked.
 * If the callback returns true the value of the property is being set to the new value,
 * and if the callback returns false the new value is discarded and the property remains its old value.
 */
typealias ChangePredicate<T> = (T, T) -> Boolean

typealias OnChange<T> = (T) -> Unit