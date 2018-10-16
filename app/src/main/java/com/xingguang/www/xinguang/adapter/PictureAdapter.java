package com.xingguang.www.xinguang.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;
import com.xingguang.www.xinguang.R;

import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/10/16 9:44
 * @描述 TODO
 */
public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {
    private static final String       TAG = "PictureAdapter";
    private              List<String> mSystemPhotoList;
    private              Context      mContext;

    public PictureAdapter(Context context, List<String> systemPhotoList) {
        mSystemPhotoList = systemPhotoList;
        mContext = context;
    }

    @Override
    public PictureAdapter.PictureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_inner_picture, parent, false);
        PictureViewHolder pictureViewHolder = new PictureViewHolder(inflate);
        return pictureViewHolder;
    }

    @Override
    public void onBindViewHolder(PictureAdapter.PictureViewHolder holder, int position) {
        Log.i(TAG, "mSystemPhotoList.get(position)" + mSystemPhotoList.get(position));
        Picasso.get().load("file://"+mSystemPhotoList.get(position)).error(R.drawable.click_head_img_1).into(holder
                .mIv_inner_picture);

//        Picasso.with(mContext).load("file://"+mSystemPhotoList.get(position)).error(R.drawable.click_head_img_1).into(holder
//                .mIv_inner_picture);
    }

    @Override
    public int getItemCount() {
        return mSystemPhotoList.size();
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
}
