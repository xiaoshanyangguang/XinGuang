package com.xingguang.www.xinguang.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.activity.BaseAppliCation;
import com.xingguang.www.xinguang.base.Html5Interface;
import com.xingguang.www.xinguang.entity.LinkEntity;
import com.xingguang.www.xinguang.util.CommonUtil;
import com.xingguang.www.xinguang.util.GsonUtil;
import com.xingguang.www.xinguang.util.SpUtils;
import com.xingguang.www.xinguang.util.ToastUtils;
import com.xingguang.www.xinguang.view.Html5WebView;
import com.xingguang.www.xinguang.view.HtmlWebChromeClient;

import java.util.ArrayList;
import java.util.List;

import static com.xingguang.www.xinguang.util.SpUtils.HTMLWEBCHROMECLIENT;

public class Html5Fragment extends BaseFragment implements Html5Interface {
    private static final String HTML5_ENTITY = "html5_entity";
    private static final String URL          = "URL";
    private              String mUrl;

    private FrameLayout      mLayout;
    private CommonTitleBar   mCommonTitleBar;
    private ProgressBar      mProgressBar;
    private Html5WebView     mWebView;
    private LinkEntity       mLinkEntity;
    private String           mGsonString;
    private List<LinkEntity> mLinkEntities1;
    private boolean          mCollected;
    private LinkEntity       mEntity;


    public static Html5Fragment newInstance(LinkEntity linkEntity) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(HTML5_ENTITY, linkEntity);
        Html5Fragment fragment = new Html5Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mLinkEntity = arguments.getParcelable(HTML5_ENTITY);
        mUrl = mLinkEntity.getWebsite();
        mGsonString = SpUtils.getInstance(HTMLWEBCHROMECLIENT).getString(HTMLWEBCHROMECLIENT);
        if (TextUtils.isEmpty(mGsonString)) {
            mLinkEntities1 = new ArrayList<>();
        } else {
            //                    mLinkEntities1 = GsonUtil.GsonToList(gsonString, LinkEntity.class);
            mLinkEntities1 = new Gson().fromJson(mGsonString, new TypeToken<List<LinkEntity>>() {
            }.getType());
        }
        mEntity = getCollected();
        mCollected = isCollected(mEntity);
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
        mProgressBar = inflate.findViewById(R.id.web_prog);
        final ImageButton rightImageButton = mCommonTitleBar.getRightImageButton();

        // 创建 WebView
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView = new Html5WebView(BaseAppliCation.getInstance());
        mWebView.setLayoutParams(params);
        mLayout.addView(mWebView);
        final Html5WebChromeClient html5WebChromeClient = new Html5WebChromeClient(mUrl, mCommonTitleBar
                .getCenterTextView());
        mWebView.setWebChromeClient(html5WebChromeClient);
        mWebView.loadUrl(mUrl);
        if (mCollected) {
            rightImageButton.setImageResource(R.drawable.rating_small_full);
        } else {
            rightImageButton.setImageResource(R.drawable.rating_small_empty);
        }
        rightImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCollected = !mCollected;
                if (mCollected) {
                    rightImageButton.setImageResource(R.drawable.rating_small_full);
                    ToastUtils.showLongToast(R.string.cancell_colloect);
                    LinkEntity linkEntity = new LinkEntity();
                    linkEntity.setTitle(html5WebChromeClient.getTitle());
                    linkEntity.setWebsite(mUrl);
                    mLinkEntities1.add(0, linkEntity);
                    ToastUtils.showLongToast(R.string.colloect);
                } else {
                    mLinkEntities1.remove(mEntity);
                    rightImageButton.setImageResource(R.drawable.rating_small_empty);
                    ToastUtils.showLongToast(R.string.cancell_colloect);
                }
                //存储
                String newString = GsonUtil.GsonString(mLinkEntities1);
                SpUtils.getInstance(HTMLWEBCHROMECLIENT).put(HTMLWEBCHROMECLIENT, newString);
                Log.i(TAG, "newString:" + newString);

            }
        });
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
            if (newProgress == 100) {
                mProgressBar.setVisibility(View.GONE);
            } else {
                mProgressBar.setProgress(newProgress);
            }
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

    private boolean isCollected(LinkEntity entity) {
        if (null != entity) {
            return true;
        }
        return false;
    }

    private LinkEntity getCollected() {
        for (int i = 0; i < mLinkEntities1.size(); i++) {
            if (mLinkEntities1.get(i).getWebsite().equals(mUrl)) {
                return mLinkEntities1.get(i);
            }
        }
        return null;
    }
}