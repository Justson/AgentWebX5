package com.just.agentwebX5;

import android.support.v4.util.ArrayMap;

import com.tencent.smtt.sdk.WebView;


/**
 * <b>@项目名：</b> <br>
 * <b>@包名：</b><br>
 * <b>@创建者：</b> cxz --  just<br>
 * <b>@创建时间：</b> &{DATE}<br>
 * <b>@公司：</b> <br>
 * <b>@邮箱：</b> cenxiaozhong.qqcom@qq.com<br>
 * <b>@描述</b><br>
 *   source code https://github.com/Justson/AgentWebX5
 *
 */

public interface WebSecurityCheckLogic {
    void dealHoneyComb(WebView view);

    void dealJsInterface(ArrayMap<String, Object> objects, AgentWebX5.SecurityType securityType);

}
