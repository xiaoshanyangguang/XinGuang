package com.xingguang.www.xinguang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import com.xingguang.www.xinguang.Fragment.BaseFragment;
import com.xingguang.www.xinguang.Fragment.CreateFragment;
import com.xingguang.www.xinguang.Fragment.Html5Fragment;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.base.CameraRefreshInterface;
import com.xingguang.www.xinguang.base.Html5Interface;
import com.xingguang.www.xinguang.base.JumpInterface;
import com.xingguang.www.xinguang.entity.LinkEntity;
import com.xingguang.www.xinguang.util.AppFileHelper;
import com.xingguang.www.xinguang.util.PhotoHelper;

import java.util.List;

public class MainActivity extends BaseActivity implements JumpInterface {

    private static final String       TAG = "MainActivity";
    private              BaseFragment mCreatelanFragment;
    private              String       mHtml5UriString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        Log.i(TAG, "getIntent().getData():" + getIntent().getDataString() + "--:intent:" + intent);
        if (null != intent) {
            if (intent.getAction().equals(Intent.ACTION_VIEW)) {
                mHtml5UriString = intent.getDataString();
            }
        }
        if (null == savedInstanceState) {
            mCreatelanFragment = CreateFragment.newInstance();
            mCreatelanFragment.setIsRoot(true);
            jumpFragment(mCreatelanFragment);
            if (!TextUtils.isEmpty(mHtml5UriString)) {
                LinkEntity linkEntity = new LinkEntity();
                linkEntity.setWebsite(mHtml5UriString);
                jumpFragment(Html5Fragment.newInstance(linkEntity));
            }
        }
    }

    @Override
    public void jumpFragment(Fragment toFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //        fragmentTransaction.setCustomAnimations(R.anim.h_fragment_enter,R.anim.h_fragment_exit);
        fragmentTransaction.replace(R.id.fl_container, toFragment).addToBackStack(null).commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                //            pop
                Fragment activeFragment = getVisibleFragment();
                if (activeFragment instanceof Html5Interface) {
                    if (((Html5Interface) activeFragment).onKeyDown(KeyEvent.KEYCODE_BACK, event)) {
                        return true;
                    } else {
                        super.onKeyDown(keyCode, event);
                    }
                }
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoHelper.handleActivityResult(this, requestCode, resultCode, data, new PhotoHelper.PhotoCallback() {
            @Override
            public void onFinish(String filePath) {
                Fragment activeFragment = getVisibleFragment();
                Log.i(TAG, "filePath:" + filePath + "--activeFragment:" + activeFragment);
                if (activeFragment instanceof CameraRefreshInterface) {
                    ((CameraRefreshInterface) activeFragment).refreshPictureData(filePath);
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppFileHelper.initStroagePath(this);

    }


    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }
}
