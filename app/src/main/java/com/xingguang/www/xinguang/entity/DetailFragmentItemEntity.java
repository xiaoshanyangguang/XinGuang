package com.xingguang.www.xinguang.entity;

/**
 * @创建者 pengbo
 * @创建时间 2018/9/24 23:00
 * @描述 TODO
 */
public class DetailFragmentItemEntity {
    public String planId;
    public long   createTime;
    public String id;
    public String content;

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
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
}
