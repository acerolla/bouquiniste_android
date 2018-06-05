package com.acerolla.bouquiniste.presentation.utils;

import android.content.res.Resources;
import android.util.SparseIntArray;

public class ValuesConverter {

    public static final int DP_4 = 4;
    public static final int DP_5 = 5;
    public static final int DP_8 = 8;
    public static final int DP_10 = 10;
    public static final int DP_25 = 25;
    public static final int DP_30 = 30;
    public static final int DP_45 = 45;
    public static final int DP_48 = 48;
    public static final int DP_100 = 100;
    public static final int DP_150 = 150;
    public static final int DP_155 = 155;
    public static final int DP_350 = 350;

    private static final SparseIntArray CONVERTED_VALUES = new SparseIntArray();

    public static int dp2px(final int dp) {
        Integer value = CONVERTED_VALUES.get(dp);
        if (value == 0) {
            value = (int) (dp * Resources.getSystem().getDisplayMetrics().density);
            CONVERTED_VALUES.put(dp, value);
        }
        return value;
    }

    public static int pxToDp(final int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
