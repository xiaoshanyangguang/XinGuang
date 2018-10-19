package com.xingguang.www.xinguang.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xingguang.www.xinguang.R;

import java.io.File;

import uk.co.senab.photoview.PhotoView;

/**
 * @创建者 pengbo
 * @创建时间 2018/10/19 19:54
 * @描述 TODO
 */
public class PicturePhotoViewFragment extends BaseFragment {
    private static final String PATH = "path";
    private              String mPathString;

    public static PicturePhotoViewFragment newInstance(String path) {
        Bundle bundle = new Bundle();
        bundle.putString(PATH, path);
        PicturePhotoViewFragment fragment = new PicturePhotoViewFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mPathString = arguments.getString(PATH);
        Log.i(TAG, "mString" + mPathString);
    }

    @Override
    protected void initData(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        super.initData(inflater, container, savedInstanceState);
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_picture_photoview, container, false);
        PhotoView photoView = inflate.findViewById(R.id.photoView);
        if (!TextUtils.isEmpty(mPathString)) {
            photoView.setImageURI(Uri.fromFile(new File(mPathString)));
        }
        return inflate;
    }
}
