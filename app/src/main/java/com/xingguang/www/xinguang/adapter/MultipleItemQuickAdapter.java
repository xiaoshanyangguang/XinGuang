package com.xingguang.www.xinguang.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.entity.MultipleItem;

import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/9/23 21:32
 * @描述 TODO
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(Context context, List<MultipleItem> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {

    }
}
