package com.aidanvii.toolbox.databinding.utils.suppliers;

/**
 * Created by aidan.vii@gmail.com on 06/12/16.
 */

public interface Function<T, R> {
    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    R apply(T t);
}
