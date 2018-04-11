package com.just.agentwebX5;

import android.os.Build;
import android.support.v4.util.ArrayMap;

import com.tencent.smtt.sdk.WebView;


/**
 */

public class WebSecurityControllerImpl implements WebSecurityController<WebSecurityCheckLogic> {

    private WebView mWebView;
    private ArrayMap<String, Object> mMap;
    private AgentWebX5.SecurityType mSecurityType;

    public WebSecurityControllerImpl(WebView view, ArrayMap<String, Object> map, AgentWebX5.SecurityType securityType) {
        this.mWebView = view;
        this.mMap = map;
        this.mSecurityType=securityType;
    }

    @Override
    public void check(WebSecurityCheckLogic webSecurityCheckLogic) {

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            webSecurityCheckLogic.dealHoneyComb(mWebView);
        }

        if (mMap != null &&mSecurityType== AgentWebX5.SecurityType.strict&& !mMap.isEmpty()) {
            webSecurityCheckLogic.dealJsInterface(mMap,mSecurityType);
        }

    }
}
