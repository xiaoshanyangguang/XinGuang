package com.xingguang.www.xinguang.entity;

public class CreateFragmentItemEntity {


    private String id;
    private String title;
    private String content;
    private long   time;
    private int    peopleSum;
    private int    imageResource;
    private String imageUrl;

    public CreateFragmentItemEntity() {
        super();
    }

    public CreateFragmentItemEntity(String title, String content, long time, int peopleSum, int imageResource, String
            imageUrl) {
        this.title = title;
        this.content = content;
        this.time = time;
        this.peopleSum = peopleSum;
        this.imageResource = imageResource;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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


}