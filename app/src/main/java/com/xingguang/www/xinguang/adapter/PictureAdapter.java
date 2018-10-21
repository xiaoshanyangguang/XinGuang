package com.xingguang.www.xinguang.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xingguang.www.xinguang.Fragment.PicturePhotoViewFragment;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.base.JumpInterface;
import com.xingguang.www.xinguang.util.PhotoHelper;
import com.xingguang.www.xinguang.util.PicassoUtil;

import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/10/16 9:44
 * @描述 TODO
 */
public class PictureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public static final  String  TAKE_PHOTO    = "take_photo";
    private static final String  TAG           = "PictureAdapter";
    public static final  int     CRITICAL_SIZE = 2 * 4;//3行4列
    private              Context mContext;
    private              boolean mIsOpen       = false;

    public PictureAdapter(Context context, List<String> systemPhotoList) {
        super(R.layout.item_inner_picture, systemPhotoList);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, final String item) {
        ImageView view = holder.getView(R.id.iv_inner_picture);
        switch (item) {
            case TAKE_PHOTO:
                PicassoUtil.showDrawablePicture(R.drawable.take_photo, R.drawable.chuangjian_256_256, view);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PhotoHelper.fromCamera(mContext);
                    }
                });
                break;
            default:
                PicassoUtil.showFilePicture(item, R.drawable.chuangjian_256_256, view);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mContext instanceof JumpInterface) {
                            PicturePhotoViewFragment targetFragment = PicturePhotoViewFragment.newInstance
                                    (item);
                            ((JumpInterface) mContext).jumpFragment(targetFragment);
                        }
                    }
                });
                break;
        }
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

    public boolean isShouldOpenOrClose(List data) {
        if (data.size() <= CRITICAL_SIZE) {
            return false;
        } else {
            return true;
        }
    }

    public void notifyData(String filePath) {
        Log.i(TAG, "notifyData(String filePath) " + filePath);
        mData.add(1, filePath);
        //        notifyItemInserted(1);
        notifyItemRangeChanged(0,mData.size()-1);
    }
}
