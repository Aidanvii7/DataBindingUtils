package com.aidanvii.toolbox.databinding

import android.arch.lifecycle.ViewModel
import com.aidanvii.toolbox.databinding.notifiable.BaseNotifiableObservable
import com.aidanvii.toolbox.databinding.notifiable.NotifiableObservable

/**
 * A convenience [ViewModel] class that implements [NotifiableObservable].
 */
open class ObservableViewModel(notifiableObservableDelegate: NotifiableObservable = BaseNotifiableObservable())
    : ViewModel(), NotifiableObservable by notifiableObservableDelegate {

    init {
        initDelegator(this)
    }
}