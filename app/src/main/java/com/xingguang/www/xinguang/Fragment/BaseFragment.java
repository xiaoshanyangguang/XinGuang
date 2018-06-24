package com.xingguang.www.xinguang.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.animation.Animation;

import com.xingguang.www.xinguang.FragmentUtil.AnimatorHelper;
import com.xingguang.www.xinguang.FragmentUtil.FragmentConfig;
import com.xingguang.www.xinguang.FragmentUtil.anim.DefaultHorizontalAnimator;
import com.xingguang.www.xinguang.FragmentUtil.anim.FragmentAnimator;

/**
 * @创建者 pengbo
 * @创建时间 2017/12/15 23:41
 * @描述 TODO
 */

public class BaseFragment extends Fragment {
    protected  String FRAGMENT_ARG_IS_ROOT = "fragment_arg_is_root";
    protected static final String TAG = BaseFragment.class.getName();
    protected Context mContext;
    public FragmentOnclickListener mFragmentOnclickListener;
    private AnimatorHelper mAnimHelper;
    private boolean mIsRoot;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if(null != arguments){
            mIsRoot = arguments.getBoolean(FRAGMENT_ARG_IS_ROOT);
        }
        mAnimHelper = new AnimatorHelper(mContext, onCreateFragmentAnimator());
        // 监听入栈动画结束(1.为了防抖动; 2.为了Fragmentation的回调所用)
        mAnimHelper.enterAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //                _mActivity.setFragmentClickable(false);  // 开启防抖动
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //                notifyEnterAnimEnd();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    public interface FragmentOnclickListener {
        void onFragmentClick(int id, FragmentConfig targetFragment);
    }

    public void setOnFragmentJumpOnclickListener(FragmentOnclickListener fragmentOnclickListener) {
        mFragmentOnclickListener = fragmentOnclickListener;
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if(transit == FragmentTransaction.TRANSIT_FRAGMENT_OPEN){
            Log.i(TAG,"onCreateAnimation TRANSIT_FRAGMENT_OPEN:"+transit+"mIsRoot:"+mIsRoot);
            if(enter){
                if(mIsRoot){
                    return mAnimHelper.getNoneAnim();
                }
                return mAnimHelper.enterAnim;
            }else{
                return mAnimHelper.exitAnim;
            }
        }
        if(transit == FragmentTransaction.TRANSIT_FRAGMENT_CLOSE){
            Log.i(TAG,"onCreateAnimation TRANSIT_FRAGMENT_CLOSE:"+transit);
            if(enter){
                return mAnimHelper.popEnterAnim;
            }else{
                return mAnimHelper.popExitAnim;
            }
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }


    /**
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    protected FragmentAnimator onCreateFragmentAnimator() {
        return new DefaultHorizontalAnimator();
    }

    public void setIsRoot(boolean isRoot){
        Bundle arguments = getArguments();
        arguments.putBoolean(FRAGMENT_ARG_IS_ROOT,isRoot);
    }

}
