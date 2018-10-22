package com.xingguang.www.xinguang.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.xingguang.www.xinguang.Fragment.Html5Fragment;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.base.JumpInterface;
import com.xingguang.www.xinguang.entity.LinkEntity;
import com.xingguang.www.xinguang.entity.MultipleItem;

import java.util.List;

import static com.xingguang.www.xinguang.adapter.PictureAdapter.TAKE_PHOTO;

/**
 * @创建者 pengbo
 * @创建时间 2018/9/23 21:32
 * @描述 TODO
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    private Context                  mContext;
    private PictureAdapter           mPictureAdapter;
    private ItemDragAdapter          mLinkAdapter;
    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;
    private ItemTouchHelper          mItemTouchHelper;
    private boolean                  mIsOpenLink    = true;
    private boolean                  mIsOpenPicture = true;
    private String                   mPlanContentPicId;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(Context context, List<MultipleItem> data, String planContentPicId) {
        super(data);
        addItemType(MultipleItem.PICTURE, R.layout.item_picture);
        addItemType(MultipleItem.LINK, R.layout.item_link);
        addItemType(MultipleItem.TEXT, R.layout.item_text);
        mContext = context;
        mPlanContentPicId = planContentPicId;
    }


    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        //图片选择模块
        if (item.getItemType() == MultipleItem.PICTURE) {
            RecyclerView recyclerView = helper.getView(R.id.recyclerview_picture);
            final ImageView iv_Arrow = helper.getView(R.id.iv_arrow);
            RelativeLayout relativeLayout = helper.getView(R.id.rl_picture);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 4));
            //头加一个
            List<String> systemPhotoList = item.getSystemPhotoList();
            systemPhotoList.add(0, TAKE_PHOTO);
            mPictureAdapter = new PictureAdapter(mContext, systemPhotoList,mPlanContentPicId);
            mPictureAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            if (!mPictureAdapter.isShouldOpenOrClose(systemPhotoList)) {
                iv_Arrow.setVisibility(View.GONE);
            }
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mPictureAdapter.setIsOpen(mIsOpenPicture);
                    if (mIsOpenPicture) {
                        iv_Arrow.setImageResource(R.drawable.icon_listcontorl_down_48dp);
                    } else {
                        iv_Arrow.setImageResource(R.drawable.icon_listcontorl_up_48dp);
                    }
                    mIsOpenPicture = !mIsOpenPicture;
                }
            });
            recyclerView.setAdapter(mPictureAdapter);
        }
        //链接选择模块
        if (item.getItemType() == MultipleItem.LINK) {
            RecyclerView recyclerView = helper.getView(R.id.recyclerview_link);
            RelativeLayout relativeLayout = helper.getView(R.id.rl_link);
            final ImageView imageView = helper.getView(R.id.iv_arrow);
            final List<LinkEntity> linkEntities = item.getLinkList();
            Log.i(TAG, "linkEntities:" + linkEntities.toString());
            mLinkAdapter = new ItemDragAdapter(mContext, linkEntities);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

            mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mLinkAdapter);
            mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);
            mItemTouchHelper.attachToRecyclerView(recyclerView);
            OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
                @Override
                public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
                    Log.d(TAG, "view swiped start: " + pos);
                    BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                    //                holder.setTextColor(R.id.tv, Color.WHITE);
                }

                @Override
                public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {
                    Log.d(TAG, "View reset: " + pos);
                    BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                    //                holder.setTextColor(R.id.tv, Color.BLACK);
                }

                @Override
                public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                    Log.d(TAG, "View Swiped: " + pos);
                }

                @Override
                public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                              boolean isCurrentlyActive) {
                    canvas.drawColor(ContextCompat.getColor(mContext, R.color.color_light_blue));
                    //                canvas.drawText("Just some text", 0, 40, paint);
                }
            };
            //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
            // ItemTouchHelper.UP | ItemTouchHelper.DOWN);
            mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);
            mLinkAdapter.enableSwipeItem();
            mLinkAdapter.setOnItemSwipeListener(onItemSwipeListener);
            mLinkAdapter.enableDragItem(mItemTouchHelper);

            OnItemDragListener listener = new OnItemDragListener() {
                @Override
                public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {
                    Log.d(TAG, "drag start");
                    BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                    //                holder.setTextColor(R.id.tv, Color.WHITE);
                }

                @Override
                public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder
                        target, int to) {
                    Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());
                }

                @Override
                public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
                    Log.d(TAG, "drag end");
                    BaseViewHolder holder = ((BaseViewHolder) viewHolder);
                    //                holder.setTextColor(R.id.tv, Color.BLACK);
                }
            };

            mLinkAdapter.setOnItemDragListener(listener);
            if (!mLinkAdapter.isShouldOpenOrClose(linkEntities)) {
                imageView.setVisibility(View.GONE);
            }
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mLinkAdapter.setIsOpen(mIsOpenLink);
                    if (mIsOpenLink) {
                        imageView.setImageResource(R.drawable.icon_listcontorl_down_48dp);
                    } else {
                        imageView.setImageResource(R.drawable.icon_listcontorl_up_48dp);
                    }
                    mIsOpenLink = !mIsOpenLink;
                }
            });
            mLinkAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (mContext instanceof JumpInterface) {
                        Html5Fragment targetFragment = Html5Fragment.newInstance(linkEntities.get(position));
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
