package com.xingguang.www.xinguang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.util.PhotoHelper;
import com.xingguang.www.xinguang.util.PicassoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/10/16 9:44
 * @描述 TODO
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {
    public static final  String       TAKE_PHOTO         = "take_photo";
    public static final  String       MORE_PICTURE       = "more_picture";
    private static final String       TAG                = "PictureAdapter";
    private static final int          CRITICAL_SIZE      = 3 * 4;//3行4列
    private              List<String> mLeavePhotoList    = new ArrayList<>();
    private              List<String> mOriginPhotoList;
    private              String       mCritical_data;
    private              List<String> mRealUsedPhotoList = new ArrayList<>();
    private              Context      mContext;
    public               boolean      mIsOpen            = false;

    public PictureAdapter(Context context, List<String> systemPhotoList) {
        mOriginPhotoList = systemPhotoList;
        mContext = context;
        handleData(false);
    }

    @Override
    public PictureAdapter.PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_inner_picture, parent, false);
        PictureViewHolder pictureViewHolder = new PictureViewHolder(inflate);
        return pictureViewHolder;
    }

    @Override
    public void onBindViewHolder(PictureAdapter.PictureViewHolder holder, int position) {
        switch (mRealUsedPhotoList.get(position)) {
            case TAKE_PHOTO:
                PicassoUtil.showDrawablePicture(R.drawable.take_photo, R.drawable.chuangjian_256_256, holder
                        .mIv_inner_picture);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PhotoHelper.fromCamera(mContext);
                    }
                });
                break;
            case MORE_PICTURE:
                PicassoUtil.showDrawablePicture(R.drawable.picture_more, R.drawable.chuangjian_256_256, holder
                        .mIv_inner_picture);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mIsOpen = !mIsOpen;
                        notifyData(mIsOpen);
                    }
                });
                break;
            default:
                PicassoUtil.showFilePicture(mRealUsedPhotoList.get(position), R.drawable.chuangjian_256_256, holder
                        .mIv_inner_picture);
                break;
        }
        //        Picasso.with(mContext).load("file://"+mSystemPhotoList.get(position)).error(R.drawable
        // .click_head_img_1).into(holder
        //                .mIv_inner_picture);
    }

    @Override
    public int getItemCount() {
        return mRealUsedPhotoList.size();
    }

    class PictureViewHolder extends BaseViewHolder {

        private ImageView mIv_inner_picture;
        private TextView  mIv_inner_textView;

        public PictureViewHolder(View view) {
            super(view);
            mIv_inner_picture = view.findViewById(R.id.iv_inner_picture);
            mIv_inner_textView = view.findViewById(R.id.tv__inner_picture);
        }
    }

    public void handleData(boolean isHandleDataOpen) {
        if (mOriginPhotoList.size() <= CRITICAL_SIZE) {
            mRealUsedPhotoList = mOriginPhotoList;
        } else {
            if (mRealUsedPhotoList.size() == 0) {
                for (int i = 0; i < CRITICAL_SIZE; i++) {
                    mRealUsedPhotoList.add(mOriginPhotoList.get(i));
                }
            }
            if (null == mCritical_data) {
                mCritical_data = mRealUsedPhotoList.get(CRITICAL_SIZE - 1);
            }
            if (isHandleDataOpen) {
                mRealUsedPhotoList.set(CRITICAL_SIZE - 1, mCritical_data);
                mRealUsedPhotoList.addAll(mLeavePhotoList);
            } else {
                if (mLeavePhotoList.size() == 0) {
                    mOriginPhotoList.removeAll(mRealUsedPhotoList);
                    mLeavePhotoList = mOriginPhotoList;
                }
                if (mRealUsedPhotoList.containsAll(mLeavePhotoList)) {
                    mRealUsedPhotoList.removeAll(mLeavePhotoList);
                    mRealUsedPhotoList.set(CRITICAL_SIZE - 1, mLeavePhotoList.get(mLeavePhotoList.size() - 1));
                }else{
                    mRealUsedPhotoList.set(CRITICAL_SIZE - 1, mLeavePhotoList.get(mLeavePhotoList.size() - 1));
                }
            }

        }
    }

    public void notifyData(boolean isOpen) {
        if (mLeavePhotoList.size() + mRealUsedPhotoList.size() > CRITICAL_SIZE) {
            handleData(isOpen);
            notifyItemRangeChanged(CRITICAL_SIZE - 1, mLeavePhotoList.size() + 1);
        }
    }
}
