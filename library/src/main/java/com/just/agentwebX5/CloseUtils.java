package com.just.agentwebX5;

import java.io.Closeable;

/**
 * Created by cenxiaozhong on 2017/5/24.
 * source CODE  https://github.com/Justson/AgentWebX5
 */

class CloseUtils {


    public static void closeIO(Closeable closeable){
        try {

            if(closeable!=null)
                closeable.close();
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}
