package com.xingguang.www.xinguang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xingguang.www.xinguang.R;

/**
 * @创建者 pengbo
 * @创建时间 2018/6/3 12:34
 * @描述 TODO
 */

public class TargetFragment extends BaseFragment {

    public static TargetFragment newInstance() {
        Bundle bundle = new Bundle();
        TargetFragment fragment = new TargetFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_target, container, false);
        return inflate;
    }
}
