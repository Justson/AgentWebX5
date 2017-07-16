package com.just.agentwebX5;

import android.view.ViewGroup;

/**
 * <b>@项目名：</b> agentwebX5<br>
 * <b>@包名：</b>com.just.library<br>
 * <b>@创建者：</b> cxz --  just<br>
 * <b>@创建时间：</b> &{DATE}<br>
 * <b>@公司：</b> <br>
 * <b>@邮箱：</b> cenxiaozhong.qqcom@qq.com<br>
 * <b>@描述:source CODE  https://github.com/Justson/AgentWebX5</b><br>
 */

public interface WebCreator extends ProgressManager {
    WebCreator create();

    com.tencent.smtt.sdk.WebView get();

    ViewGroup getGroup();
}
