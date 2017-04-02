package com.aidanvii.databinding.utils.suppliers;

/**
 * Created by aidan.vii@gmail.com on 06/12/16.
 */

public interface VoidFunction<T> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     */
    void apply(T t);
}
