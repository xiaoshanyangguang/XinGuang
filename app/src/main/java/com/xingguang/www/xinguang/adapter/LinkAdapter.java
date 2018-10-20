package com.xingguang.www.xinguang.adapter;

import android.content.Context;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.entity.LinkEntity;

import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/10/16 9:44
 * @描述 TODO
 */
public class LinkAdapter extends BaseQuickAdapter<LinkEntity,BaseViewHolder> {
    private static final String TAG = "LinkAdapter";
    private Context          mContext;

    public LinkAdapter(Context context, List<LinkEntity> linkEntities) {
        super(R.layout.item_inner_link,linkEntities);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, LinkEntity item) {
        Log.i(TAG,"item.website:"+item.website);
        helper.setText(R.id.tv__inner_link,item.title);
    }


}
