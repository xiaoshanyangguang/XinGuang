package com.xingguang.www.xinguang.datamanager;

import android.util.Log;

import com.google.gson.Gson;
import com.xingguang.www.xinguang.activity.BaseAppliCation;
import com.xingguang.www.xinguang.entity.CreateFragmentBean;
import com.xingguang.www.xinguang.entity.CreateFragmentItemEntity;
import com.xingguang.www.xinguang.entity.DetailFragmentItemEntity;
import com.xingguang.www.xinguang.entity.DetailSection;
import com.xingguang.www.xinguang.entity.MultipleItem;
import com.xingguang.www.xinguang.entity.MySection;
import com.xingguang.www.xinguang.util.CommonUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/7/11 22:40
 * @描述 TODO
 */
public class DataImpl {
    private static final String HTTPS_AVATARS1_GITHUBUSERCONTENT_COM_LINK = "https://avatars1.githubusercontent" +
            ".com/u/7698209?v=3&s=460";
    private static final String TAG                                       = "DataImpl";
    private static final String CREATE_DATA                               = "create_data.txt";
    private static final String MYPLANS                                   = "myplans";
    private static final String RECOMMENDPLANS                            = "recommendplans";
    private static final String CYM_CHAD                                  = "CymChad";

    public static CreateFragmentBean getCreateFragmentData() {
        String json = CommonUtil.getJson(CREATE_DATA, BaseAppliCation.getInstance());
        Log.i(TAG, "json" + json);
        return new Gson().fromJson(json, CreateFragmentBean.class);
    }

    public static List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new MultipleItem(MultipleItem.IMG, MultipleItem.IMG_SPAN_SIZE));
            list.add(new MultipleItem(MultipleItem.TEXT, MultipleItem.TEXT_SPAN_SIZE, "--"));
            list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE));
            list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN));
            list.add(new MultipleItem(MultipleItem.IMG_TEXT, MultipleItem.IMG_TEXT_SPAN_SIZE_MIN));
        }

        return list;
    }


    public static List<MySection> getCreateSampleData() {
        List<MySection> list = new ArrayList<>();
        list.add(new MySection(true, "我的计划", true));
        for (int i = 0; i < 2; i++) {
            CreateFragmentItemEntity createFragmentItemEntity = new CreateFragmentItemEntity();
            createFragmentItemEntity.setTitle("我的安卓面试计划" + i);
            createFragmentItemEntity.setContent("时间会证明一切" + i);
            list.add(new MySection(createFragmentItemEntity));
        }
        list.add(new MySection(true, "推荐计划", true));
        for (int i = 0; i < 4; i++) {
            CreateFragmentItemEntity createFragmentItemEntity = new CreateFragmentItemEntity();
            createFragmentItemEntity.setTitle("推荐安卓面试计划" + i);
            createFragmentItemEntity.setContent("加油" + i);
            list.add(new MySection(createFragmentItemEntity));
        }

        return list;
    }

    public static List<DetailSection> getDetailSampleData() {
        List<DetailSection> list = new ArrayList<>();
        list.add(new DetailSection(true, "掌握内容", true));
        for (int i = 0; i < 2; i++) {
            DetailFragmentItemEntity createFragmentItemEntity = new DetailFragmentItemEntity();
            createFragmentItemEntity.setId("");
            createFragmentItemEntity.setContent("掌握内容" + i + "\n");
            list.add(new DetailSection(createFragmentItemEntity));
        }
        list.add(new DetailSection(true, "学习方式", true));
        for (int i = 0; i < 4; i++) {
            DetailFragmentItemEntity createFragmentItemEntity = new DetailFragmentItemEntity();
            createFragmentItemEntity.setId("");
            createFragmentItemEntity.setContent("学习方式" + i + "\n");
            list.add(new DetailSection(createFragmentItemEntity));
        }
        list.add(new DetailSection(true, "评价方式", true));
        for (int i = 0; i < 4; i++) {
            DetailFragmentItemEntity createFragmentItemEntity = new DetailFragmentItemEntity();
            createFragmentItemEntity.setId("");
            createFragmentItemEntity.setContent("评价方式" + i + "\n");
            list.add(new DetailSection(createFragmentItemEntity));
        }
        return list;
    }

}
