package com.xingguang.www.xinguang.view;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;

import com.xingguang.www.xinguang.entity.LinkEntity;
import com.xingguang.www.xinguang.util.GsonUtil;
import com.xingguang.www.xinguang.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

import static com.xingguang.www.xinguang.util.SpUtils.HTMLWEBCHROMECLIENT;

/**
 * @创建者 pengbo
 * @创建时间 2018/10/20 21:50
 * @描述 TODO
 */
public class HtmlWebChromeClient extends Html5WebView.BaseWebChromeClient {
    private static final String    TAG = "HtmlWebChromeClient";
    private              String    mUrl;
    private              TextView  mTextView;

    public HtmlWebChromeClient(String url,  TextView titleTextView) {
        mUrl = url;
        mTextView = titleTextView;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        mTextView.setText(title);
        List<LinkEntity> mLinkEntities1;
        String gsonString = SpUtils.getInstance(HTMLWEBCHROMECLIENT).getString(HTMLWEBCHROMECLIENT);


        if (TextUtils.isEmpty(gsonString)) {
            mLinkEntities1 = new ArrayList<>();
        } else {
            mLinkEntities1 = GsonUtil.GsonToList(gsonString, LinkEntity.class);
        }
        LinkEntity linkEntity = new LinkEntity();
        linkEntity.setTitle(title);
        linkEntity.setWebsite(mUrl);
        mLinkEntities1.add(0, linkEntity);
        String newString = GsonUtil.GsonString(mLinkEntities1);
        SpUtils.getInstance(HTMLWEBCHROMECLIENT).put(HTMLWEBCHROMECLIENT, newString);
        Log.i(TAG, "newString:" + newString);
    }


    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }
}
