package com.xingguang.www.xinguang.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.xingguang.www.xinguang.Fragment.BaseFragment;
import com.xingguang.www.xinguang.Fragment.CreateFragment;
import com.xingguang.www.xinguang.Fragment.DetailFragment;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.base.JumpInterface;

import java.util.List;

public class MainActivity extends BaseActivity implements JumpInterface {

    private static final String         TAG = "MainActivity";
    private              BaseFragment   mCreatelanFragment;
    private              DetailFragment mTargetFragment;

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
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            //            pop
            super.onBackPressed();
        } else {
            finish();
        }
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
