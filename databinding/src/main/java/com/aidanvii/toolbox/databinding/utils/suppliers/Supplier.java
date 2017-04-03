package com.aidanvii.toolbox.databinding.utils.suppliers;

/**
 * Created by aidan.vii@gmail.com on 06/12/16.
 */

public interface Supplier<T> {
    /**
     * Gets a result.
     *
     * @return a result
     */
    T get();
}
