package com.xingguang.www.xinguang.datamanager;

import android.util.Log;

import com.google.gson.Gson;
import com.xingguang.www.xinguang.activity.BaseAppliCation;
import com.xingguang.www.xinguang.entity.CreateFragmentBean;
import com.xingguang.www.xinguang.util.CommonUtil;

/**
 * @创建者 pengbo
 * @创建时间 2018/7/11 22:40
 * @描述 TODO
 */
public class DataImpl {
    private static final String TAG            = "DataImpl";
    private static final String CREATE_DATA    = "create_data.txt";
    private static final String MYPLANS        = "myplans";
    private static final String RECOMMENDPLANS = "recommendplans";

    public static CreateFragmentBean getCreateFragmentData() {
        String json = CommonUtil.getJson(CREATE_DATA, BaseAppliCation.getInstance());
        Log.i(TAG, "json"+json);
        return new Gson().fromJson(json, CreateFragmentBean.class);
    }
}
