package com.xingguang.www.xinguang.homeadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.activity.BaseAppliCation;
import com.xingguang.www.xinguang.entity.HomeItem;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class HomeAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {
    public HomeAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_content, BaseAppliCation.getInstance().getString(item.getContent()));
        helper.setImageResource(R.id.iv, item.getImageResource());
    }
}
