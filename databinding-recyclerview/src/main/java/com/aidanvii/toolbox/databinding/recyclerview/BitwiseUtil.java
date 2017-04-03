package com.aidanvii.toolbox.databinding.recyclerview;

/**
 * Created by aidan.vii@gmail.com on 30/03/17.
 */
public class BitwiseUtil {

    public static int FLAG_0() {
        return 0x0;
    }

    public static int FLAG_1() {
        return 0x1;
    }

    public static int FLAG_2() {
        return 0x2;
    }

    public static int FLAG_3() {
        return 0x4;
    }

    public static int FLAG_4() {
        return 0x8;
    }

    public static int FLAG_5() {
        return 0x16;
    }

    public static int setTrue(final int flags, final int flag) {
        return flags | flag;
    }

    public static int setFalse(final int flags, final int flag) {
        return flags & ~flag;
    }

    public static boolean isTrue(final int flags, final int flag) {
        return (flags & flag) == flag;
    }

    public static boolean isFalse(final int flags, final int flag) {
        return (flags & flag) != flag;
    }
}