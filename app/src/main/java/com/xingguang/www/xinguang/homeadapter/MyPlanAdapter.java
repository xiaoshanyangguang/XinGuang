package com.xingguang.www.xinguang.homeadapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.entity.CreateFragmentBean;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MyPlanAdapter extends BaseQuickAdapter<CreateFragmentBean.MyplansBean, BaseViewHolder> {
    public MyPlanAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreateFragmentBean.MyplansBean item) {
        helper.setText(R.id.tv_title, item.getName());
        helper.setText(R.id.tv_content, item.getContent());
        helper.setText(R.id.tv_time, item.getStartTime());
        helper.setText(R.id.tv_fllowNum, item.getFllowNum());
//        helper.setImageResource(R.id.iv, item.getImages());
    }
}
