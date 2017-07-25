package com.just.agentwebX5;

import android.app.Activity;
import android.webkit.JavascriptInterface;

/**
 * Created by cenxiaozhong on 2017/5/24.
 */

public class AgentWebJsInterfaceCompat implements AgentWebCompat ,FileUploadPop<IFileUploadChooser> {

    private AgentWebX5 mAgentWebX5;
    private Activity mActivity;
     AgentWebJsInterfaceCompat(AgentWebX5 agentWebX5, Activity activity){
        this.mAgentWebX5 = agentWebX5;
         this.mActivity=activity;
    }

    private IFileUploadChooser mIFileUploadChooser;
    @JavascriptInterface
    public void uploadFile(){


        mIFileUploadChooser=new FileUpLoadChooserImpl(mActivity,new FileUpLoadChooserImpl.JsChannelCallback() {
            @Override
            public void call(String value) {

//                Log.i("Info","call:"+value);
//                StringBuilder sb=new StringBuilder().append("javascript:uploadFileResult ( \"").append(value).append("\" ) ");
                if(mAgentWebX5 !=null)
//                    mAgentWebX5.getJsEntraceAccess().callJs("javascript:uploadFileResult(" + value + ")");
                    mAgentWebX5.getJsEntraceAccess().quickCallJs("uploadFileResult",value);
            }
        });
        mIFileUploadChooser.openFileChooser();

    }

    @Override
    public IFileUploadChooser pop() {
        IFileUploadChooser mIFileUploadChooser=this.mIFileUploadChooser;
        this.mIFileUploadChooser=null;
        return mIFileUploadChooser;
    }
}
