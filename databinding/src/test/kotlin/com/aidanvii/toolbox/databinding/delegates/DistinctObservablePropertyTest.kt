package com.aidanvii.toolbox.databinding.delegates

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.aidanvii.toolbox.utils.Consumer
import com.aidanvii.toolbox.utils.delegates.distinctObservable
import org.junit.Test

class DistinctObservablePropertyTest {

    val onNewValue = mock<Consumer<Int>>()

    @Test
    fun `onNewValue not invoked when same value assigned`() {
        var tested by distinctObservable(1, onNewValue)

        tested = 1

        verifyZeroInteractions(onNewValue)
    }

    @Test
    fun `onNewValue invoked when different value assigned`() {
        var tested by distinctObservable(1, onNewValue)

        tested = 2

        verify(onNewValue).invoke(2)
        verifyNoMoreInteractions(onNewValue)
    }
}