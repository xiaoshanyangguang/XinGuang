package com.xingguang.www.xinguang.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.entity.MultipleItem;

import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/9/23 21:32
 * @描述 TODO
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    private Context mContext;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(Context context, List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.PICTURE, R.layout.item_picture);
        addItemType(MultipleItem.LINK, R.layout.item_link);
        addItemType(MultipleItem.TEXT, R.layout.item_text);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        //图片选择模块
        if (item.getItemType() == MultipleItem.PICTURE) {
            RecyclerView recyclerView = helper.getView(R.id.recyclerview_picture);

            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
            recyclerView.setAdapter(new PictureAdapter(mContext,item.getSystemPhotoList()));
        }
    }
}
