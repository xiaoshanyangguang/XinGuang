package com.xingguang.www.xinguang.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItem implements MultiItemEntity {
    public static final int              PICTURE      = 1;
    public static final int              LINK         = 2;
    public static final int              TEXT         = 3;
    public static final int              LINK_SIZE    = 1;
    public static final int              PICTURE_SIZE = 1;
    public static final int              TEXT_SIZE    = 1;
    private             int              itemType;
    private             int              spanSize;
    private             String           title;
    private             String           content;
    private             List<String>     mSystemPhotoList;
    private             List<LinkEntity> mLinkList;
    private             long             time;
    private             int              joinNumbers;

    public List<LinkEntity> getLinkList() {
        return mLinkList;
    }

    public void setLinkList(List<LinkEntity> linkList) {
        mLinkList = linkList;
    }


    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getJoinNumbers() {
        return joinNumbers;
    }

    public void setJoinNumbers(int joinNumbers) {
        this.joinNumbers = joinNumbers;
    }

    public MultipleItem(int itemType, int spanSize, String content) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content = content;
    }


    public MultipleItem(int itemType, int spanSize, List<String> systemPhotoList) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.mSystemPhotoList = systemPhotoList;
    }


    public MultipleItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getSystemPhotoList() {
        return mSystemPhotoList;
    }

    public void setSystemPhotoList(List<String> systemPhotoList) {
        mSystemPhotoList = systemPhotoList;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
