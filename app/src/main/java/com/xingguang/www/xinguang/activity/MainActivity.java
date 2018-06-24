package com.xingguang.www.xinguang.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.xingguang.www.xinguang.Fragment.BaseFragment;
import com.xingguang.www.xinguang.Fragment.CreateFragment;
import com.xingguang.www.xinguang.Fragment.TargetFragment;
import com.xingguang.www.xinguang.FragmentUtil.FragmentConfig;
import com.xingguang.www.xinguang.R;

import java.util.List;

public class MainActivity extends BaseActivity implements BaseFragment.FragmentOnclickListener {

    private static final String TAG = "MainActivity";
    private CreateFragment mCreatelanFragment;
    private TargetFragment mTargetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (null == savedInstanceState) {
            mCreatelanFragment = (CreateFragment) getFragment(FragmentConfig.CREATFRAGMENT);
            initFragment(mCreatelanFragment);
            replaceFragment(mCreatelanFragment);
        }
    }

    @Override
    public void onFragmentClick(int id, FragmentConfig targetFragment) {
        if (id == R.id.bt_create_jump) {
            if (targetFragment.equals(FragmentConfig.TARGETFRAGMENT)) {
                mTargetFragment = (TargetFragment) getFragment(FragmentConfig.TARGETFRAGMENT);
                initFragment(mTargetFragment);
                replaceFragment(mTargetFragment);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            //            pop
            super.onBackPressed();
        } else {
            finish();
        }
    }

    private BaseFragment getFragment(FragmentConfig targetFragmentCofig) {
        BaseFragment baseFragment = null;
        if (FragmentConfig.CREATFRAGMENT.equals(targetFragmentCofig)) {
            baseFragment = CreateFragment.newInstance();
            baseFragment.setIsRoot(true);
        } else if (FragmentConfig.TARGETFRAGMENT.equals(targetFragmentCofig)) {
            baseFragment = TargetFragment.newInstance();
        } else {
            Log.i(TAG, "getFragment error");
        }
        return baseFragment;
    }

    private void initFragment(BaseFragment baseFragment) {
        baseFragment.setOnFragmentJumpOnclickListener(this);
    }

    private void replaceFragment(BaseFragment mainFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        initTransaction(fragmentTransaction);
        fragmentTransaction.replace(R.id.fl_container, mainFragment).addToBackStack(null).commit();
    }

    private void initTransaction(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        //        fragmentTransaction.setCustomAnimations(R.anim.h_fragment_enter,R.anim.h_fragment_exit);
    }


    /**
     * 从栈顶开始查找,状态为show & userVisible的Fragment
     */
    BaseFragment getActiveFragment(BaseFragment parentFragment, FragmentManager fragmentManager) {
        List<Fragment> fragmentList = fragmentManager.getFragments();
        if (fragmentList == null) {
            return parentFragment;
        }
        for (int i = fragmentList.size() - 1; i >= 0; i--) {
            Fragment fragment = fragmentList.get(i);
            if (fragment instanceof BaseFragment) {
                BaseFragment baseFragment = (BaseFragment) fragment;
                if (baseFragment.isResumed() && !baseFragment.isHidden() && baseFragment.getUserVisibleHint()) {
                    return getActiveFragment(baseFragment, baseFragment.getChildFragmentManager());
                }
            }
        }
        return parentFragment;
    }
}
