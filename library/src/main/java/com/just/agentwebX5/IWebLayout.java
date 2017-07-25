package com.just.agentwebX5;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.tencent.smtt.sdk.WebView;

/**
 * Created by cenxiaozhong on 2017/7/1.
 * source CODE  https://github.com/Justson/AgentWebX5
 */

public interface IWebLayout<T extends WebView,V extends ViewGroup> {

    /**
     *
     * @return WebView 的父控件
     */
    @NonNull V getLayout();

    /**
     *
     * @return 返回 WebView  或 WebView 的子View ，返回null AgentWebX5 内部会自动创建适当 WebView
     */
    @Nullable T getWeb();
}
