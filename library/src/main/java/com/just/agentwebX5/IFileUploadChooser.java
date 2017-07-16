package com.just.agentwebX5;

import android.content.Intent;

/**
 * Created by cenxiaozhong on 2017/5/22.
 * source CODE  https://github.com/Justson/AgentWebX5
 */

public interface IFileUploadChooser {



    void openFileChooser();

    void fetchFilePathFromIntent(int requestCode, int resultCode, Intent data);
}
