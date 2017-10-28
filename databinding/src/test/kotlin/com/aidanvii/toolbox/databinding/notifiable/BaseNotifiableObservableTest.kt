package com.aidanvii.toolbox.databinding.notifiable

import android.databinding.Observable
import android.databinding.PropertyChangeRegistry
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

const val EXPECTED_PROP_ID = 1

class BaseNotifiableObservableTest {

    val spyPropertyChangedRegistry = spy<PropertyChangeRegistry>()
    val mockPropertyChangeCallback = mock<Observable.OnPropertyChangedCallback>()
    val mockDelegator = mock<NotifiableObservable>()

    val tested = BaseNotifiableObservable({ spyPropertyChangedRegistry }).apply { initDelegator(mockDelegator) }

    @Test
    fun `Adds callback to PropertyChangeRegistry when addOnPropertyChangedCallback invoked`() {
        tested.addOnPropertyChangedCallback(mockPropertyChangeCallback)

        verify(spyPropertyChangedRegistry).add(mockPropertyChangeCallback)
    }

    @Test
    fun `Removes callback to PropertyChangeRegistry when addOnPropertyChangedCallback invoked`() {
        tested.removeOnPropertyChangedCallback(mockPropertyChangeCallback)

        verify(spyPropertyChangedRegistry).remove(mockPropertyChangeCallback)
    }

    @Test
    fun `notifies PropertyChangeRegistry with given property ID when notifyPropertyChanged invoked`() {
        tested.addOnPropertyChangedCallback(mockPropertyChangeCallback)

        tested.notifyPropertyChanged(EXPECTED_PROP_ID)

        verify(spyPropertyChangedRegistry).notifyCallbacks(mockDelegator, EXPECTED_PROP_ID, null)
    }

    @Test
    fun `notifies PropertyChangeRegistry with ALL_PROPERTIES when notifyChanged invoked`() {
        tested.addOnPropertyChangedCallback(mockPropertyChangeCallback)

        tested.notifyChange()

        verify(spyPropertyChangedRegistry).notifyCallbacks(mockDelegator, ALL_PROPERTIES, null)
    }
}