package com.xingguang.www.xinguang.activity;

import android.app.Application;

/**
 * @创建者 pengbo
 * @创建时间 2018/7/9 22:57
 * @描述 TODO
 */
public class BaseAppliCation extends Application {
    private static BaseAppliCation instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseAppliCation getInstance(){
        return instance;
    }

}
