package com.aidanvii.toolbox.databinding

import android.databinding.*
import android.databinding.adapters.ListenerUtil
import android.support.annotation.IdRes
import android.view.View

/**
 * Designed to track objects passed as optional parameters via static [BindingAdapter] methods.
 *
 * Only one instance per instanceResId can be tracked at a time.
 *
 * This is useful for add*Listener and remove*Listener methods,
 * where associated [BindingAdapter] methods must replace the previously added listener, or remove it.
 *
 * It is a wrapper around [ListenerUtil.trackListener], with less specific naming, as the instance being tracked does not
 * necessarily need to be a listener.
 *
 * Example usage:
 * ```
 * @BindingAdapter("textWatcher")
 * fun TextView.setTextWatcher(textWatcher: TextWatcher?) {
 *     trackInstance(
 *              newInstance = textWatcher,
 *              instanceResId = R.id.textWatcher,
 *              onDetached = {
 *                  // [it] is the previously added listener, called when non-null.
 *                  removeTextChangedListener(it)
 *              },
 *              onAttached = {
 *                  // [it] is the newly added listener, called when non-null.
 *                  addTextChangedListener(it)
 *              })
 * }
 *
 * ```
 */
inline fun <V : View, I> V.trackInstance(newInstance: I?,
                                         @IdRes instanceResId: Int,
                                         onDetached: V.(I) -> Unit,
                                         onAttached: V.(I) -> Unit) {
    ListenerUtil.trackListener<I>(this, newInstance, instanceResId).let { oldInstance ->
        if (oldInstance !== newInstance) {
            oldInstance?.let { onDetached(it) }
            newInstance?.let { onAttached(it) }
        }
    }
}

inline fun <V : View, I> V.onTrackedInstance(@IdRes instanceResId: Int,
                                             provideNewInstance: V.() -> I,
                                             onInstance: I.() -> Unit) {

    ListenerUtil.trackListener<I>(this, null, instanceResId) ?: provideNewInstance().let { instance ->
        ListenerUtil.trackListener<I>(this, instance, instanceResId)
        instance.onInstance()
    }
}