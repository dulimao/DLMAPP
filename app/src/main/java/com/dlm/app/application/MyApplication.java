package com.dlm.app.application;

import android.app.Application;

import com.dlm.app.utils.DensityUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Administrator on 2017/4/28.
 */
public class MyApplication extends Application {
    private static MyApplication myApplication = null;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        Fresco.initialize(myApplication);
        DensityUtil.initValues(myApplication);//初始化DensityUtil
    }

    public static MyApplication getInstance(){
        return myApplication;
    }
}
