package com.aidanvii.toolbox.databinding.recyclerview;

import android.support.annotation.Nullable;

import java.util.List;
/**
 * Created by aidan.vii@gmail.com on 30/03/17.
 */

final class Util {
    private Util() {
    }

    static int[] toIntArray(List<Integer> integerList) {
        int[] intArray = new int[integerList.size()];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = integerList.get(i);
        }
        return intArray;
    }

    static boolean notEmpty(final @Nullable int[] intArray) {
        return intArray != null && intArray.length > 0;
    }
}
