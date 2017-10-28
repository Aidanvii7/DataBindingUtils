package com.aidanvii.toolbox.databinding

import android.databinding.Bindable
import com.aidanvii.toolbox.databinding.delegates.bindable

class TestViewModel : ObservableViewModel() {

    @get:Bindable
    var property1 by bindable(1)

    @get:Bindable
    var property2 by bindable(2)

    @get:Bindable
    var property3 by bindable(3)
}