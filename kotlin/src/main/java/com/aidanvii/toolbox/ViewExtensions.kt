package com.aidanvii.toolbox

import android.os.Bundle
import android.os.Parcelable
import android.view.View

/**
 * Created by aidan.vii@gmail.com on 17/03/17.
 */

const val KEY_SUPER_VIEW_STATE = "SUPER_VIEW_STATE"
const val KEY_VIEW_STATE = "VIEW_STATE"

inline fun View.tryRestoreViewState(state: Parcelable?, restoreSuperState: (Parcelable) -> Unit, restoreState: (Bundle) -> Unit) {
    if (state != null && state is Bundle) {
        restoreSuperState(state.getParcelable(KEY_SUPER_VIEW_STATE))
        restoreState(state.getParcelable(KEY_VIEW_STATE))
    }
}

inline fun View.saveViewState(superViewState: Parcelable, saveState: (Bundle) -> Unit): Bundle {
    val viewState = Bundle()
    saveState(viewState)
    val combinedState = Bundle()
    combinedState.putParcelable(KEY_SUPER_VIEW_STATE, superViewState)
    combinedState.putParcelable(KEY_VIEW_STATE, viewState)
    return combinedState
}