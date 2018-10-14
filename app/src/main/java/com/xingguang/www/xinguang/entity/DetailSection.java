package com.xingguang.www.xinguang.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @创建者 pengbo
 * @创建时间 2018/9/24 23:10
 * @描述 TODO
 */
public class DetailSection extends SectionEntity<DetailFragmentItemEntity> {
    private boolean isMore;
    public DetailSection(boolean isHeader, String header, boolean isMroe) {
        super(isHeader, header);
        this.isMore = isMroe;
    }

    public DetailSection(DetailFragmentItemEntity t) {
        super(t);
    }

    public boolean isMore() {
        return isMore;
    }

    public void setMore(boolean mroe) {
        isMore = mroe;
    }
}
