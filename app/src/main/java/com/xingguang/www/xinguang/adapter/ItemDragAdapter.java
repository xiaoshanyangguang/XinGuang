package com.xingguang.www.xinguang.adapter;

import android.content.Context;
import android.util.Log;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.entity.LinkEntity;

import java.util.List;

/**
 * Created by luoxw on 2016/6/20.
 */
public class ItemDragAdapter extends BaseItemDraggableAdapter<LinkEntity, BaseViewHolder> {

    private static final String  TAG           = "ItemDragAdapter";
    private static final int     CRITICAL_SIZE = 3;
    private              boolean mIsOpen       = false;
    private              Context mContext;

    public ItemDragAdapter(Context context, List<LinkEntity> linkEntities) {
        super(R.layout.item_inner_link, linkEntities);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, LinkEntity item) {
        Log.i(TAG, "item.website:" + item.website);
        helper.setText(R.id.tv__inner_link, item.title);
    }

    @Override
    public int getItemCount() {
        if (isShouldOpenOrClose(mData)) {
            if (mIsOpen) {
                return super.getItemCount();
            } else {
                return CRITICAL_SIZE;
            }
        } else {
            return super.getItemCount();

        }
    }

    public void setIsOpen(boolean isOpen) {
        mIsOpen = isOpen;
        Log.i(TAG, "setIsOpen mIsOpen:" + mIsOpen);
        if (isShouldOpenOrClose(mData)) {
            notifyItemRangeChanged(CRITICAL_SIZE, mData.size() - CRITICAL_SIZE);
        }
    }

    protected boolean isShouldOpenOrClose(List data) {
        if (data.size() <= CRITICAL_SIZE) {
            return false;
        } else {
            return true;
        }
    }
}
