package com.aidanvii.toolbox.databinding.bindingadapters

import android.databinding.BindingAdapter
import android.view.View
import com.aidanvii.toolbox.databinding.BindingAction

@BindingAdapter("android:onClick")
internal fun View._bind(onClick: BindingAction?) {
    setOnClickListener(onClick.toOnClickListener())
}

private fun BindingAction?.toOnClickListener(): View.OnClickListener? = this?.let { View.OnClickListener { invoke() } }