package com.aidanvii.toolbox.databinding;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

import static com.aidanvii.toolbox.databinding.utils.Preconditions.checkArgumentIsNotNull;
/**
 * Created by aidan.vii@gmail.com on 02/04/17.
 */

final class Utils {
    private Utils() {
    }

    @NonNull
    static <T> WeakReference<T> weak(@Nullable T reference) {
        return new WeakReference<T>(reference);
    }
}
