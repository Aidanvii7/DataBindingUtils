package com.aidanvii.databinding.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
/**
 * Created by aidan.vii@gmail.com on 07/10/16.
 */
public final class Preconditions {

    private Preconditions() {
        // helper class
    }

    /**
     * Checks if given reference is null. If yes throws {@link NullPointerException}.
     *
     * @param reference reference to suppressDestroyCreateOnConfigChange.
     * @param <T>       type of the reference.
     * @return passed reference is is not null.
     */
    public static <T> T checkNotNull(T reference) {
        return checkNotNull(reference, "Reference is null");
    }

    /**
     * Checks if given reference is null. If yes throws {@link NullPointerException}.
     *
     * @param reference reference to suppressDestroyCreateOnConfigChange.
     * @param <T>       type of the reference.
     * @param message   message to show.
     * @return passed reference is is not null.
     */
    public static <T> T checkNotNull(T reference, String message) {
        if (reference == null) {
            throw new NullPointerException(message);
        }
        return reference;
    }

    /**
     * Checks if given argument is null. If yes throws {@link IllegalArgumentException}.
     *
     * @param argument argument to suppressDestroyCreateOnConfigChange.
     * @param <T>      type of argument.
     * @return passed argument if is not null.
     */
    public static <T> T checkArgumentIsNotNull(T argument) {
        checkArgument(argument != null);
        return argument;
    }

    /**
     * Check argument expression. If false then throws {@link IllegalArgumentException}.
     *
     * @param expression expression to suppressDestroyCreateOnConfigChange arguments requirements.
     */
    public static void checkArgument(boolean expression) {
        checkArgument(expression, "Argument is invalid");
    }

    /**
     * Check argument expression. If false then throws {@link IllegalArgumentException}.
     *
     * @param expression expression to suppressDestroyCreateOnConfigChange arguments requirements.
     * @param message    message to show if expression is not met.
     */
    public static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T cast(Object uncast) {
        return (T) uncast;
    }

    @NonNull
    public static <T> T getOrElse(@Nullable T value, @NonNull T defaultValue) {
        return value != null ? value : checkArgumentIsNotNull(defaultValue);
    }
}