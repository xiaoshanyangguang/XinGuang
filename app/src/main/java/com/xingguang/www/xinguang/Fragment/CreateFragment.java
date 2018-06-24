package com.xingguang.www.xinguang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.xingguang.www.xinguang.FragmentUtil.FragmentConfig;
import com.xingguang.www.xinguang.R;

/**
 * @创建者 pengbo
 * @创建时间 2017/12/15 23:37
 * @描述 计划信息(掌握内容, 学习方式, 评价方式)展示页面
 */

public class CreateFragment extends BaseFragment implements View.OnClickListener {

    private Button mNextButton;


    public static CreateFragment newInstance() {
        Bundle bundle = new Bundle();
        CreateFragment mainFragment = new CreateFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_create, container, false);
        initView(inflate);
        Log.i(TAG, "onCreateView");
        return inflate;
    }

    private void initView(View inflate) {
        mNextButton = (Button) inflate.findViewById(R.id.bt_create_jump);
        mNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bt_create_jump) {
            if (null != mFragmentOnclickListener) {
                mFragmentOnclickListener.onFragmentClick(id, FragmentConfig.TARGETFRAGMENT);
            }
        }
    }
}
