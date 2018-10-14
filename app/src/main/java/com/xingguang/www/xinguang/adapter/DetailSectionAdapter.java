package com.xingguang.www.xinguang.adapter;


import android.util.Log;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.entity.DetailFragmentItemEntity;
import com.xingguang.www.xinguang.entity.DetailSection;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class DetailSectionAdapter extends BaseSectionQuickAdapter<DetailSection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public DetailSectionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final DetailSection item) {
        Log.i(TAG,"convertHead"+item.header);
        helper.setText(R.id.tv_title, item.header);
    }


    @Override
    protected void convert(BaseViewHolder helper, DetailSection item) {
        DetailFragmentItemEntity video =  item.t;
        Log.i(TAG,"convert"+video.getContent());
        helper.setText(R.id.tv_content, video.getContent());
    }
}
