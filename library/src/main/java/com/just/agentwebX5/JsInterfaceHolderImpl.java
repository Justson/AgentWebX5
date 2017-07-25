package com.just.agentwebX5;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.tencent.smtt.sdk.WebView;

import java.util.Map;
import java.util.Set;

/**
 * Created by cenxiaozhong on 2017/5/13.
 * source CODE  https://github.com/Justson/AgentWebX5
 */

public class JsInterfaceHolderImpl extends JsBaseInterfaceHolder {

    static JsInterfaceHolderImpl getJsInterfaceHolder(WebView webView, AgentWebX5.SecurityType securityType) {

        return new JsInterfaceHolderImpl(webView,securityType);
    }

    private WebView mWebView;
    private AgentWebX5.SecurityType mSecurityType;
    JsInterfaceHolderImpl(WebView webView, AgentWebX5.SecurityType securityType) {
        super(securityType);
        this.mWebView = webView;
        this.mSecurityType=securityType;
    }

    @Override
    public JsInterfaceHolder addJavaObjects(ArrayMap<String, Object> maps) {



        if(!checkSecurity()){
            return this;
        }
        Set<Map.Entry<String, Object>> sets = maps.entrySet();
        for (Map.Entry<String, Object> mEntry : sets) {


            Object v = mEntry.getValue();
            boolean t = checkObject(v);
            if (!t)
                throw new JsInterfaceObjectException("this object has not offer method javascript to call ,please check addJavascriptInterface annotation was be added");

            else
                addJavaObjectDirect(mEntry.getKey(), v);
        }

        return this;
    }

    @Override
    public JsInterfaceHolder addJavaObject(String k, Object v) {

        if(!checkSecurity()){
            return this;
        }
        boolean t = checkObject(v);
        if (!t)
            throw new JsInterfaceObjectException("this object has not offer method javascript to call , please check addJavascriptInterface annotation was be added");

        else
            addJavaObjectDirect(k, v);
        return this;
    }

    private JsInterfaceHolder addJavaObjectDirect(String k, Object v) {
        Log.i("Info", "k" + k + "  v:" + v);
        this.mWebView.addJavascriptInterface(v, k);
        return this;
    }


}
