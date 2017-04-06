package com.aidanvii

import com.aidanvii.kotlin.LifecycleListener
import com.aidanvii.kotlin.LifecycleStage
import org.junit.Test
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.mock

/**
 * Created by aidan.vii@gmail.com on 24/03/17.
 */
class LifecycleStageTest {

    @Test
    fun GIVEN_lifecycleListenerStageIsUnknown_WHEN_transitionToLifecycleStageStarted_THEN_expectedLifecycleMethodsInvoked() {
        val mockHolder = MockLifecycleDelagator(LifecycleStage.PRE_CREATED)
        val mock = mockHolder.mock
        val inOrder = inOrder(mock)

        LifecycleStage.STARTED.moveListenerToStage(mockHolder)

        inOrder.apply {
            verify(mock).doOnCreate()
            verify(mock).doOnStart()
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun GIVEN_lifecycleListenerStageIsUnknown_WHEN_transitionToLifecycleStageResumed_THEN_expectedLifecycleMethodsInvoked() {
        val mockHolder = MockLifecycleDelagator(LifecycleStage.PRE_CREATED)
        val mock = mockHolder.mock
        val inOrder = inOrder(mock)

        LifecycleStage.DESTROYED.moveListenerToStage(mockHolder)

        inOrder.apply {
            verify(mock).doOnCreate()
            verify(mock).doOnStart()
            verify(mock).doOnResume()
            verify(mock).doOnPause()
            verify(mock).doOnStop()
            verify(mock).doOnDestroy()
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun GIVEN_lifecycleListenerStageIsUnknown_WHEN_transitionToLifecycleStageDestroyed_THEN_expectedLifecycleMethodsInvoked() {
        val mockHolder = MockLifecycleDelagator(LifecycleStage.PRE_CREATED)
        val mock = mockHolder.mock
        val inOrder = inOrder(mock)

        LifecycleStage.DESTROYED.moveListenerToStage(mockHolder)

        inOrder.apply {
            verify(mock).doOnCreate()
            verify(mock).doOnStart()
            verify(mock).doOnResume()
            verify(mock).doOnPause()
            verify(mock).doOnStop()
            verify(mock).doOnDestroy()
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun GIVEN_lifecycleListenerStageIsStopped_WHEN_transitionToLifecycleStageResumed_THEN_expectedLifecycleMethodsInvoked() {
        val mockHolder = MockLifecycleDelagator(LifecycleStage.STOPPED)
        val mock = mockHolder.mock
        val inOrder = inOrder(mock)

        LifecycleStage.RESUMED.moveListenerToStage(mockHolder)

        inOrder.apply {
            verify(mock).doOnPause()
            verify(mock).doOnResume()
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun GIVEN_lifecycleListenerStageIsPaused_WHEN_transitionToLifecycleStageResumed_THEN_expectedLifecycleMethodsInvoked() {
        val mockHolder = MockLifecycleDelagator(LifecycleStage.PAUSED)
        val mock = mockHolder.mock
        val inOrder = inOrder(mock)

        LifecycleStage.RESUMED.moveListenerToStage(mockHolder)

        inOrder.apply {
            verify(mock).doOnResume()
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun GIVEN_lifecycleListenerStageIsStopped_WHEN_transitionToLifecycleStageStarted_THEN_expectedLifecycleMethodsInvoked() {
        val mockHolder = MockLifecycleDelagator(LifecycleStage.STOPPED)
        val mock = mockHolder.mock
        val inOrder = inOrder(mock)

        LifecycleStage.STARTED.moveListenerToStage(mockHolder)

        inOrder.apply {
            verify(mock).doOnPause()
            verify(mock).doOnResume()
            verify(mock).doOnStart()
            verifyNoMoreInteractions()
        }
    }
}


class MockLifecycleDelagator(
        override var lifecycleStage: LifecycleStage,
        val mock: LifecycleListener = mock(LifecycleListener::class.java))
    : LifecycleListener by mock