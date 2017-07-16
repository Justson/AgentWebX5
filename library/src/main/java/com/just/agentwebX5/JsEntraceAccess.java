package com.just.agentwebX5;

import android.webkit.ValueCallback;

/**
 * Created by cenxiaozhong on 2017/5/14.
 * source CODE  https://github.com/Justson/AgentWebX5
 */

public interface JsEntraceAccess extends QuickCallJs{


    void callJs(String js, ValueCallback<String> callback);

    void callJs(String js);




}
