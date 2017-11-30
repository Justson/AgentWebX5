package com.just.agentwebX5;

import android.app.Activity;
import android.webkit.JavascriptInterface;

import java.lang.ref.WeakReference;

/**
 * Created by cenxiaozhong on 2017/5/24.
 */

public class AgentWebJsInterfaceX5Compat implements AgentWebX5Compat, FileUploadPop<IFileUploadChooser> {

    private AgentWebX5 mAgentWebX5;
    private WeakReference<AgentWebX5> mReference = null;
    private WeakReference<Activity> mActivityWeakReference = null;

    AgentWebJsInterfaceX5Compat(AgentWebX5 agentWebX5, Activity activity) {
        this.mReference = new WeakReference<AgentWebX5>(agentWebX5);
        mActivityWeakReference = new WeakReference<Activity>(activity);
    }

    private IFileUploadChooser mIFileUploadChooser;

    @JavascriptInterface
    public void uploadFile() {


        LogUtils.i("AgentWebJsInterfaceX5Compat", "upload file");
        if (mActivityWeakReference.get() != null && mReference.get() != null) {
            mIFileUploadChooser = new FileUpLoadChooserImpl.Builder()
                    .setActivity(mActivityWeakReference.get())
                    .setJsChannelCallback(new FileUpLoadChooserImpl.JsChannelCallback() {
                        @Override
                        public void call(String value) {
                            if (mReference.get() != null)
                                mReference.get().getJsEntraceAccess().quickCallJs("uploadFileResult", value);
                        }
                    }).setFileUploadMsgConfig(mReference.get().getDefaultMsgConfig().getChromeClientMsgCfg().getFileUploadMsgConfig())
                    .setPermissionInterceptor(mReference.get().getPermissionInterceptor())
                    .setWebView(mReference.get().getWebCreator().get())
                    .build();
            mIFileUploadChooser.openFileChooser();
            return;
        }
//        mIFileUploadChooser.openFileChooser();

    }

    @Override
    public IFileUploadChooser pop() {
        IFileUploadChooser mIFileUploadChooser = this.mIFileUploadChooser;
        this.mIFileUploadChooser = null;
        return mIFileUploadChooser;
    }
}
