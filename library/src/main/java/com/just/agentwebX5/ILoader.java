package com.just.agentwebX5;

/**
 * Created by cenxiaozhong on 2017/6/3.
 * source CODE  https://github.com/Justson/AgentWebX5
 */

public interface ILoader {


    void loadUrl(String url);

    void reload();

    void loadData(String data, String mimeType, String encoding);

    void stopLoading();

    void loadDataWithBaseURL(String baseUrl, String data,
                             String mimeType, String encoding, String historyUrl);

}
