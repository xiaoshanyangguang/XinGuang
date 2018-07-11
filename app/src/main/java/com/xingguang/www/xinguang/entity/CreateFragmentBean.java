package com.xingguang.www.xinguang.entity;

import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/7/11 22:58
 * @描述 TODO
 */
public class CreateFragmentBean {
    private List<MyplansBean> myplans;
    private List<RecommendplansBean> recommendplans;

    public List<MyplansBean> getMyplans() {
        return myplans;
    }

    public void setMyplans(List<MyplansBean> myplans) {
        this.myplans = myplans;
    }

    public List<RecommendplansBean> getRecommendplans() {
        return recommendplans;
    }

    public void setRecommendplans(List<RecommendplansBean> recommendplans) {
        this.recommendplans = recommendplans;
    }

    public static class MyplansBean {
        /**
         * name : 安卓面试计划
         * content : 时间会证明一切
         * startTime : 04-23 13:12
         * fllowNum : 45
         * images : http://img4.imgtn.bdimg.com/it/u=3776739438,757564394&fm=214&gp=0.jpg
         */

        private String name;
        private String content;
        private String startTime;
        private String fllowNum;
        private String images;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getFllowNum() {
            return fllowNum;
        }

        public void setFllowNum(String fllowNum) {
            this.fllowNum = fllowNum;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
    }

    public static class RecommendplansBean {
        /**
         * name : 安卓面试计划
         * content : 7天掌握安卓面试需要的技能
         * createTime : 04-23 13:12
         * fllowNum : 45
         * images : http://img4.imgtn.bdimg.com/it/u=3776739438,757564394&fm=214&gp=0.jpg
         */

        private String name;
        private String content;
        private String createTime;
        private String fllowNum;
        private String images;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getFllowNum() {
            return fllowNum;
        }

        public void setFllowNum(String fllowNum) {
            this.fllowNum = fllowNum;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }
    }
}
