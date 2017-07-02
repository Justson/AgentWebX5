package com.just.agentwebX5;

import android.util.Log;

/**
 * Created by cenxiaozhong on 2017/5/28.
 */

public class LogUtils {


    public static boolean isDebug() {
        return true;
    }

    public static void i(String tag,String message){

        if(isDebug())
            Log.i(tag,message);
    }

    public static void v(String tag,String message){

        if(isDebug())
            Log.v(tag,message);

    }

    public static void safeCheckCrash(String tag, String msg, Throwable tr) {
        if (isDebug()) {
            throw new RuntimeException(tag + " " + msg, tr);
        } else {
            Log.e(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }
    public static void e(String tag,String message){

        if(isDebug())
            Log.e(tag,message);
    }
}
