package com.xingguang.www.xinguang.adapter;


import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.entity.CreateFragmentItemEntity;
import com.xingguang.www.xinguang.entity.MySection;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class CreateSectionAdapter extends BaseSectionQuickAdapter<MySection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public CreateSectionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MySection item) {
        helper.setText(R.id.tv_title, item.header);
    }


    @Override
    protected void convert(BaseViewHolder helper, MySection item) {
        CreateFragmentItemEntity video =  item.t;
        helper.setText(R.id.tv_title, video.getTitle());
        helper.setText(R.id.tv_content, video.getContent());

    }
}
