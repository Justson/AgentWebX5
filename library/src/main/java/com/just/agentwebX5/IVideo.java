package com.just.agentwebX5;


import android.view.View;

import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient;

/**
 * Created by cenxiaozhong on 2017/6/10.
 */

public interface IVideo {


    void onShowCustomView(View view,IX5WebChromeClient.CustomViewCallback callback);


    void onHideCustomView();


    boolean isVideoState();

}
