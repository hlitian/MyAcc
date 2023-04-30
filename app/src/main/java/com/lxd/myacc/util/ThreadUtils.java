package com.lxd.myacc.util;

import android.os.Handler;
import android.os.Looper;

/**
 * @Author lixd
 */
public class ThreadUtils {
    public static void runOnUI(Runnable runnable){
        if(runnable == null){
            return;
        }
        if(Looper.myLooper() != Looper.getMainLooper()){
            new Handler(Looper.getMainLooper()).post(runnable);
        }else{
            runnable.run();
        }

    }
}
