package com.aidanvii.toolbox.databinding

interface BindingConsumer<in T> {
    fun invoke(value: T)
}

interface BindingAction {
    fun invoke()
}