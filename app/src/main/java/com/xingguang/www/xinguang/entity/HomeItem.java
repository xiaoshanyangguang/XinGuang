package com.xingguang.www.xinguang.entity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HomeItem {
    private String title;
    private int   content;
    private long     createTime;
    private int      peopleSum;
    private Class<?> activity;
    private int      imageResource;
    private String   imageUrl;

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public int getPeopleSum() {
        return peopleSum;
    }

    public void setPeopleSum(int peopleSum) {
        this.peopleSum = peopleSum;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<?> getActivity() {
        return activity;
    }

    public void setActivity(Class<?> activity) {
        this.activity = activity;
    }
}