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
    private static final String   TAG = "HtmlWebChromeClient";
    private              String   mUrl;
    private              TextView mTextView;
    private              String   mTitle;

    public HtmlWebChromeClient(String url, TextView titleTextView) {
        mUrl = url;
        mTextView = titleTextView;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        mTitle = title;
        mTextView.setText(title);

    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public void onReceivedIcon(WebView view, Bitmap icon) {
        super.onReceivedIcon(view, icon);
    }
}
