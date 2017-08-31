package com.just.agentwebX5;

import android.util.Log;

/**
 * Created by cenxiaozhong on 2017/5/28.
 */

public class LogUtils {

    private static final String PREFIX = " agentwebX5 ---> "; //

    public static boolean isDebug() {
        return AgentWebConfig.DEBUG;
    }

    public static void i(String tag, String message) {

        if (isDebug())
            Log.i(PREFIX.concat(tag), message);
    }

    public static void v(String tag, String message) {

        if (isDebug())
            Log.v(PREFIX.concat(tag), message);

    }

    public static void safeCheckCrash(String tag, String msg, Throwable tr) {
        if (isDebug()) {
            throw new RuntimeException(PREFIX.concat(tag) + " " + msg, tr);
        } else {
            Log.e(PREFIX.concat(tag), msg, tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }

    public static void e(String tag, String message) {

        if (BuildConfig.DEBUG)
            Log.e(PREFIX.concat(tag), message);
    }
}
