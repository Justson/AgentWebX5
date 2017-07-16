package com.just.agentwebX5;

/**
 * Created by cenxiaozhong on 2017/6/21.
 *
 * source CODE  https://github.com/Justson/AgentWebX5
 */

public interface DownLoadResultListener {


    void success(String path);

    void error(String path, String resUrl, String cause, Throwable e);

}
