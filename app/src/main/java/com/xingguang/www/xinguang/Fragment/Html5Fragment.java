package com.xingguang.www.xinguang.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wuhenzhizao.titlebar.widget.CommonTitleBar;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.activity.BaseAppliCation;
import com.xingguang.www.xinguang.base.Html5Interface;
import com.xingguang.www.xinguang.util.CommonUtil;
import com.xingguang.www.xinguang.view.Html5WebView;
import com.xingguang.www.xinguang.view.HtmlWebChromeClient;

public class Html5Fragment extends BaseFragment implements Html5Interface {
    private static final String URL = "URL";
    private              String mUrl;

    private FrameLayout    mLayout;
    private CommonTitleBar mCommonTitleBar;
    private SeekBar        mSeekBar;
    private Html5WebView   mWebView;


    public static Html5Fragment newInstance(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        Html5Fragment fragment = new Html5Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mUrl = arguments.getString(URL);
        CommonUtil.verifyStoragePermissions((Activity) mContext);
        Log.i(TAG, "mString" + mUrl);
    }

    @Override
    protected void initData(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        super.initData(inflater, container, savedInstanceState);
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_web, container, false);
        mLayout = inflate.findViewById(R.id.web_layout);
        mCommonTitleBar = inflate.findViewById(R.id.titlebar);
        mSeekBar = inflate.findViewById(R.id.web_sbr);
        // 创建 WebView
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new Html5WebView(BaseAppliCation.getInstance());
        mWebView.setLayoutParams(params);
        mLayout.addView(mWebView);
        mWebView.setWebChromeClient(new Html5WebChromeClient(mUrl, mCommonTitleBar.getCenterTextView()));
        mWebView.loadUrl(mUrl);

        return inflate;
    }

    // 继承 WebView 里面实现的基类
    class Html5WebChromeClient extends HtmlWebChromeClient {

        public Html5WebChromeClient(String url, TextView title) {
            super(url, title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            // 顶部显示网页加载进度
            mSeekBar.setProgress(newProgress);
        }
    }

    @Override
    public void onDestroy() {
        // 销毁 WebView
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }

    //============================= 下面是本 demo  的逻辑代码
    // ======================================================================================

    /**
     * 按目录键，弹出“关闭页面”的选项
     */
    //    @Override
    //    public boolean onCreateOptionsMenu(Menu menu) {
    //        getMenuInflater().inflate(R.menu.menu, menu);
    //        return super.onCreateOptionsMenu(menu);
    //    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //        int itemId = item.getItemId();
        //        switch (itemId) {
        //            case R.id.close:
        //                Html5Fragment.this.finish();
        //                return true;
        //            case R.id.copy:
        //                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        //                String url = mWebView.getUrl();
        //                ClipData clipData = ClipData.newPlainText("test", url);
        //                if (clipboardManager != null) {
        //                    clipboardManager.setPrimaryClip(clipData);
        //                    Toast.makeText(getApplicationContext(), "本页网址复制成功", Toast.LENGTH_SHORT).show();
        //                }
        //                return true;
        //        }
        return super.onOptionsItemSelected(item);
    }

    private long mOldTime;

    /**
     * 点击“返回键”，返回上一层
     * 双击“返回键”，返回到最开始进来时的网页
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - mOldTime < 1500) {
                mWebView.clearHistory();
                mWebView.loadUrl(mUrl);
                Log.i("tag", "onKeyDown3");
            } else if (mWebView.canGoBack()) {
                mWebView.goBack();
                Log.i("tag", "onKeyDown4");
            } else {
                Log.i("tag", "onKeyDown5");

                return false;
            }
            mOldTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}