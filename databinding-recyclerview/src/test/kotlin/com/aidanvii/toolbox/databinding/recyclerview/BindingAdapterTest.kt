package com.aidanvii.toolbox.databinding.recyclerview

import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test


/**
 * Created by Aidan on 16/04/2017.
 */
abstract class BindingAdapterTest<BindingAdapterImpl : BindingAdapter<*>> {

    val mockDelegate: BindingAdapter.BindingAdapterDelegate<*> = mock()
    val mockTypeHandler: BindingAdapter.ViewTypeHandler<*> = mock()
    val mockBindingFactory: BindingAdapter.ViewDataBindingFactory<*> = mock()

    abstract val tested: BindingAdapterImpl

    @Before
    fun setUp() {

    }

    @Test
    fun onCreateViewHolder() {

    }

    @Test
    fun onBindViewHolder() {

    }

    @Test
    fun onBindViewHolder1() {

    }

    @Test
    fun getItemViewType() {

    }

    @Test
    fun onViewAttachedToWindow() {

    }

    @Test
    fun onViewDetachedFromWindow() {

    }

    @Test
    fun onViewRecycled() {

    }

    @Test
    fun onFailedToRecycleView() {

    }

    @Test
    fun unbind() {

    }

    @Test
    fun getAdapterItem() {

    }

    @Test
    fun getAdapterItems() {

    }

    @Test
    fun getAdapterItemPosition() {

    }

    @Test
    fun createBindingViewHolder() {

    }




}