package com.lcodecore.tkrefreshlayout.utils;

import android.util.Log;

/**
 * Created by lcodecore on 2017/4/1.
 */

public class LogUtil {
    private static final boolean DEBUG = false;

    public static void i(String msg) {
        if (!DEBUG) return;
        Log.i("TwinklingRefreshLayout", msg);
    }
}
