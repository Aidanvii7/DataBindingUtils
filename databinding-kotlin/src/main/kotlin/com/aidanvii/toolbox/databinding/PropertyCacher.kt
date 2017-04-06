package com.aidanvii.toolbox.databinding

import kotlin.reflect.KProperty

/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */
object PropertyCacher {
    val resourceIdProviders = mutableListOf<Class<*>>()
    private val resourceIdMap: HashMap<String, Int> = HashMap<String, Int>()

    private val KProperty<*>.bindableResourceId: Int get() {
        return resourceIdProviders.mapNotNull {
            try {
                it.getDeclaredField(name).getInt(it)
            } catch (e: Throwable) {
                null
            }
        }.first() // TODO throw if multiple matches found?
    }

    fun bindableResourceIdFor(property: KProperty<*>): Int {
        return resourceIdMap.getOrPut(property.name) { property.bindableResourceId }
    }
}