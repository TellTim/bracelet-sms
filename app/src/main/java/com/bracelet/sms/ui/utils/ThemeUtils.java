package com.bracelet.sms.ui.utils;

import android.graphics.Color;
import android.support.annotation.ColorInt;

public class ThemeUtils {
    /*home fragment color set begin */
    public static final int NORMAL_R = 153;
    public static final int NORMAL_G = 153;
    public static final int NORMAL_B = 153;

    public static final int PRESS_R = 0x71;
    public static final int PRESS_G = 0x86;
    public static final int PRESS_B = 0xc7;
    /*home fragment color set end */

    @ColorInt
    public static int getNormalColor(int alpha) {
        return Color.argb(alpha, NORMAL_R, NORMAL_G, NORMAL_B);
    }

    @ColorInt
    public static int getPressColor(int alpha) {
        return Color.argb(alpha, PRESS_R, PRESS_G, PRESS_B);
    }
}
