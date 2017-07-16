package com.just.agentwebX5;

import android.support.v4.util.ArrayMap;

/**
 * Created by cenxiaozhong on 2017/5/13.
 * source CODE  https://github.com/Justson/AgentWebX5
 */

public interface JsInterfaceHolder {

    JsInterfaceHolder addJavaObjects(ArrayMap<String, Object> maps);

    JsInterfaceHolder addJavaObject(String k, Object v);

    boolean checkObject(Object v) ;

}
