package com.xingguang.www.xinguang.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.Fragment.Html5Fragment;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.base.JumpInterface;
import com.xingguang.www.xinguang.entity.LinkEntity;
import com.xingguang.www.xinguang.entity.MultipleItem;

import java.util.List;

import static com.xingguang.www.xinguang.adapter.PictureAdapter.MORE_PICTURE;
import static com.xingguang.www.xinguang.adapter.PictureAdapter.TAKE_PHOTO;

/**
 * @创建者 pengbo
 * @创建时间 2018/9/23 21:32
 * @描述 TODO
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    private Context        mContext;
    private PictureAdapter mPictureAdapter;
    private LinkAdapter    mLinkAdapter;

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
            //头尾各加一个
            List<String> systemPhotoList = item.getSystemPhotoList();
            systemPhotoList.add(0, TAKE_PHOTO);
            if (systemPhotoList.size() > PictureAdapter.CRITICAL_SIZE) {
                systemPhotoList.add(MORE_PICTURE);
            }
            mPictureAdapter = new PictureAdapter(mContext, systemPhotoList);
            recyclerView.setAdapter(mPictureAdapter);
        }
        //链接选择模块
        if (item.getItemType() == MultipleItem.LINK) {
            RecyclerView recyclerView = helper.getView(R.id.recyclerview_link);
            final List<LinkEntity> linkEntities = item.getLinkList();
            Log.i(TAG, "linkEntities:" + linkEntities.toString());
            mLinkAdapter = new LinkAdapter(mContext, linkEntities);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            mLinkAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (mContext instanceof JumpInterface) {
                        Html5Fragment targetFragment = Html5Fragment.newInstance(linkEntities.get(position)
                                .getWebsite());
                        Log.i(TAG, "--" + linkEntities.get(position)
                                .getWebsite());
                        ((JumpInterface) mContext).jumpFragment(targetFragment);
                    }
                }
            });
            recyclerView.setAdapter(mLinkAdapter);
        }
    }

    public void refreshCameraPicture(String filePath) {
        mPictureAdapter.notifyData(filePath);
    }

}
