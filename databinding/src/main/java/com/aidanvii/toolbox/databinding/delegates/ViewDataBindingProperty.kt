package com.aidanvii.toolbox.databinding.delegates

import android.databinding.ViewDataBinding
import com.aidanvii.toolbox.databinding.PropertyMapper
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A property delegate for [ViewDataBinding] objects that performs cleanup on assignment.
 *
 * When a new value is assigned, [setValue] will run a cleanup on the previous value if available.
 * @param binding the new value
 * @param propertyIds properties associated with the [ViewDataBinding] that should be released on re-assignment.
 *  Internally this will call [ViewDataBinding.setVariable] for each propertyId, with a null value.
 *  This allows an easy way to release resources in static [BindingAdapter]s
 */
class ViewBindingProperty<Binding : ViewDataBinding>(
        private var binding: Binding?,
        private val propertyIds: List<Int>? = null)
    : ReadWriteProperty<Any?, Binding?> {

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Binding?) {
        cleanupBinding()
        binding = value
    }

    private fun cleanupBinding() {
        binding?.let { binding ->
            propertyIds?.forEach {
                binding.setVariable(it, null)
            }
            binding.apply {
                unbind()
                executePendingBindings()
            }
        }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Binding? = binding
}

/**
 * Creates a [ViewBindingProperty] with the given [ViewDataBinding].
 */
fun <Binding : ViewDataBinding>
        binding(binding: Binding?, vararg propertyIds: Int) = ViewBindingProperty(binding, propertyIds.asList())

/**
 * Creates a [ViewBindingProperty] with the given [ViewDataBinding].
 */
fun <Binding : ViewDataBinding>
        autoViewBinding(binding: Binding?) = ViewBindingProperty(binding, PropertyMapper.resourceIds.toList())