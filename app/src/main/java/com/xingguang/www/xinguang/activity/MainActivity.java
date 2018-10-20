package com.xingguang.www.xinguang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;

import com.xingguang.www.xinguang.Fragment.BaseFragment;
import com.xingguang.www.xinguang.Fragment.CreateFragment;
import com.xingguang.www.xinguang.Fragment.DetailFragment;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.base.CameraRefreshInterface;
import com.xingguang.www.xinguang.base.Html5Interface;
import com.xingguang.www.xinguang.base.JumpInterface;
import com.xingguang.www.xinguang.util.AppFileHelper;
import com.xingguang.www.xinguang.util.PhotoHelper;

import java.util.List;

public class MainActivity extends BaseActivity implements JumpInterface {

    private static final String         TAG = "MainActivity";
    private              BaseFragment   mCreatelanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState) {
            mCreatelanFragment = CreateFragment.newInstance();
            mCreatelanFragment.setIsRoot(true);
            jumpFragment(mCreatelanFragment);
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
                BaseFragment activeFragment = getActiveFragment(getSupportFragmentManager());
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
                Log.i(TAG, "filePath:" + filePath);
                BaseFragment activeFragment = getActiveFragment(getSupportFragmentManager());
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


    /**
     * 从栈顶开始查找,状态为show & userVisible的Fragment
     */
    BaseFragment getActiveFragment(FragmentManager fragmentManager) {
        List<Fragment> fragmentList = fragmentManager.getFragments();
        for (int i = fragmentList.size() - 1; i >= 0; i--) {
            Fragment fragment = fragmentList.get(i);
            if (fragment instanceof BaseFragment) {
                BaseFragment baseFragment = (BaseFragment) fragment;
                if (baseFragment.isResumed() && !baseFragment.isHidden() && baseFragment.getUserVisibleHint()) {
                    return getActiveFragment(baseFragment.getChildFragmentManager());
                }
            }
        }
        return null;
    }
}
