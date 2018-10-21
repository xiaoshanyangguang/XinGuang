package com.xingguang.www.xinguang.datamanager;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xingguang.www.xinguang.activity.BaseAppliCation;
import com.xingguang.www.xinguang.entity.CreateFragmentBean;
import com.xingguang.www.xinguang.entity.CreateFragmentItemEntity;
import com.xingguang.www.xinguang.entity.DetailFragmentItemEntity;
import com.xingguang.www.xinguang.entity.DetailSection;
import com.xingguang.www.xinguang.entity.LinkEntity;
import com.xingguang.www.xinguang.entity.MultipleItem;
import com.xingguang.www.xinguang.entity.MySection;
import com.xingguang.www.xinguang.util.CommonUtil;
import com.xingguang.www.xinguang.util.SpUtils;

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
        list.add(new MultipleItem(MultipleItem.PICTURE, MultipleItem.PICTURE_SIZE, CommonUtil.getAppPhotoList()));
        MultipleItem multipleItem = new MultipleItem(MultipleItem.LINK, MultipleItem.LINK_SIZE);
        multipleItem.setLinkList(getSampleLinkEntity());
        list.add(multipleItem);
        list.add(new MultipleItem(MultipleItem.TEXT, MultipleItem.TEXT_SIZE));

        return list;
    }

    public static List<LinkEntity> getSampleLinkEntity() {
        String gsonString = SpUtils.getInstance(SpUtils.HTMLWEBCHROMECLIENT).getString(SpUtils.HTMLWEBCHROMECLIENT);
        Log.i(TAG, "gsonString:" + gsonString);
//        List<LinkEntity> linkEntities = GsonUtil.GsonToList(gsonString, LinkEntity.class);
        List<LinkEntity> linkEntities = new Gson().fromJson(gsonString, new TypeToken<List<LinkEntity>>() {
        }.getType());
        if(null == linkEntities){
            linkEntities = new ArrayList<>();
        }
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setWebsite("https://www.baidu.com/");
        linkEntity.setTitle("baiduyixia");
        linkEntities.add(linkEntity);
        for (int i = 0; i < linkEntities.size(); i++) {
             Log.i(TAG,"-"+linkEntities.get(i).getTitle()+"--"+linkEntities.get(i).getWebsite());
        }
        Log.i(TAG, "gsonString2:" + linkEntities);
        return linkEntities;
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

    //安卓权限
    public static List<DetailSection> getDetailSampleData() {
        List<DetailSection> list = new ArrayList<>();
        list.add(new DetailSection(true, "内容", true));
        DetailFragmentItemEntity createFragmentItemEntity1 = new DetailFragmentItemEntity();
        createFragmentItemEntity1.setId("");
        createFragmentItemEntity1.setContent("1.UI框架(点击进入具体的知识点,采用图片，链接，文字的形式))");
        list.add(new DetailSection(createFragmentItemEntity1));

        DetailFragmentItemEntity createFragmentItemEntity2 = new DetailFragmentItemEntity();
        createFragmentItemEntity2.setId("");
        createFragmentItemEntity2.setContent("2.单activity多fragment");
        list.add(new DetailSection(createFragmentItemEntity2));


        DetailFragmentItemEntity createFragmentItemEntity3 = new DetailFragmentItemEntity();
        createFragmentItemEntity3.setId("");
        createFragmentItemEntity3.setContent("3.自定义控件");

        list.add(new DetailSection(createFragmentItemEntity3));


        DetailFragmentItemEntity createFragmentItemEntity4 = new DetailFragmentItemEntity();
        createFragmentItemEntity4.setId("");
        createFragmentItemEntity4.setContent("4.MVP架构");
        list.add(new DetailSection(createFragmentItemEntity4));


        list.add(new DetailSection(true, "方法", true));
        DetailFragmentItemEntity createFragmentItemEntity001 = new DetailFragmentItemEntity();
        createFragmentItemEntity001.setId("");
        createFragmentItemEntity001.setContent("上午:6-8(点击进入圈子,这个时间点的人的打卡)");
        list.add(new DetailSection(createFragmentItemEntity001));

        DetailFragmentItemEntity createFragmentItemEntity002 = new DetailFragmentItemEntity();
        createFragmentItemEntity002.setId("");
        createFragmentItemEntity002.setContent("下午:18-20");
        list.add(new DetailSection(createFragmentItemEntity002));

        DetailFragmentItemEntity createFragmentItemEntity003 = new DetailFragmentItemEntity();
        createFragmentItemEntity003.setId("");
        createFragmentItemEntity003.setContent("以及其他零碎时间");
        list.add(new DetailSection(createFragmentItemEntity003));


        list.add(new DetailSection(true, "评价", true));
        DetailFragmentItemEntity createFragmentItemEntity0001 = new DetailFragmentItemEntity();
        createFragmentItemEntity0001.setId("");
        createFragmentItemEntity0001.setContent("1.生动回答ui框架问题(点击进入评价方式的答案)");
        list.add(new DetailSection(createFragmentItemEntity0001));

        DetailFragmentItemEntity createFragmentItemEntity0002 = new DetailFragmentItemEntity();
        createFragmentItemEntity0002.setId("");
        createFragmentItemEntity0002.setContent("2.写出单activity中得主要代码以及回答问题");
        list.add(new DetailSection(createFragmentItemEntity0002));

        DetailFragmentItemEntity createFragmentItemEntity0003 = new DetailFragmentItemEntity();
        createFragmentItemEntity0003.setId("");
        createFragmentItemEntity0003.setContent("3.能掌握圆弧控件的使用以及回答问题如下");
        list.add(new DetailSection(createFragmentItemEntity0003));

        DetailFragmentItemEntity createFragmentItemEntity0004 = new DetailFragmentItemEntity();
        createFragmentItemEntity0004.setId("");
        createFragmentItemEntity0004.setContent("4.掌握xx效果xx框架的标准代码");
        list.add(new DetailSection(createFragmentItemEntity0004));


        //
        list.add(new DetailSection(true, "成就", true));
        DetailFragmentItemEntity createFragmentItemEntity01 = new DetailFragmentItemEntity();
        createFragmentItemEntity01.setId("");
        createFragmentItemEntity01.setContent("解锁成就1:坚持1天,评价方式1达到");
        list.add(new DetailSection(createFragmentItemEntity01));

        DetailFragmentItemEntity createFragmentItemEntity02 = new DetailFragmentItemEntity();
        createFragmentItemEntity02.setId("");


        createFragmentItemEntity02.setContent("解锁成就2:坚持3天,评价方式2达到");
        list.add(new DetailSection(createFragmentItemEntity02));


        return list;
    }


}
