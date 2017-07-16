package com.just.agentwebX5;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.ValueCallback;

/**
 * Created by cenxiaozhong on 2017/5/29.
 * source code https://github.com/Justson/AgentWebX5
 */

public interface QuickCallJs {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    void quickCallJs(String method, ValueCallback<String> callback, String... params);
    void quickCallJs(String method, String... params);
    void quickCallJs(String method);
}
