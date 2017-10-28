package com.aidanvii.toolbox.databinding

import android.databinding.Bindable
import android.databinding.Observable
import kotlin.test.assertEquals

fun Observable.getAnnotationValue(methodName: String): String {
    val method = javaClass.getMethod(methodName)
    return method.getAnnotation(Bindable::class.java).value[0]
}

val Observable.dependentProperties get() = DependentPropertyValidator(this)

class DependentPropertyValidator(val tested: Observable) {

    infix fun String.dependsOn(expectedDependencyProperty: String) {
        tested.javaClass.getMethod("get${substring(0..0).toUpperCase() + substring(1)}").run {
            getAnnotation(Bindable::class.java).value[0]
        }.let {
            assertEquals(expectedDependencyProperty, it)
        }
    }

    infix fun String.`depends on`(expectedDependencyProperty: String) = dependsOn(expectedDependencyProperty)
}