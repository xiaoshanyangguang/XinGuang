package com.xingguang.www.xinguang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.adapter.CreateSectionAdapter;
import com.xingguang.www.xinguang.base.JumpInterface;
import com.xingguang.www.xinguang.datamanager.DataImpl;
import com.xingguang.www.xinguang.entity.MySection;

import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2017/12/15 23:37
 * @描述 计划信息(掌握内容, 学习方式, 评价方式)展示页面
 */

public class CreateFragment extends BaseFragment implements View.OnClickListener {

    private       Button       mNextButton;
    private       RecyclerView mMyPlanRecyclerView;
    private       RecyclerView mRecommendRecyclerView;
    private final int[]        IMG = {R.drawable.hetangyuese2, R.drawable.tiankong2, R.drawable.gv_header_and_footer,
            R.drawable.gv_pulltorefresh, R.drawable.gv_section, R.drawable.gv_empty, R.drawable.gv_drag_and_swipe, R
            .drawable.gv_item_click, R.drawable.gv_expandable, R.drawable.gv_databinding, R.drawable.gv_databinding};


    private List<MySection> mCreateFragmentData;


    public static CreateFragment newInstance() {
        Bundle bundle = new Bundle();
        CreateFragment mainFragment = new CreateFragment();
        mainFragment.setArguments(bundle);
        return mainFragment;
    }

    public void initData(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mCreateFragmentData = DataImpl.getCreateSampleData();
    }

    public View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_create, container, false);
        mMyPlanRecyclerView = inflate.findViewById(R.id.recyclerview_my_plan);
        mMyPlanRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
        CreateSectionAdapter sectionAdapter = new CreateSectionAdapter(R.layout.item_create_fragment, R.layout.item_create_head,
                mCreateFragmentData);
        BaseQuickAdapter baseQuickAdapter = initAdapter(sectionAdapter);
        mMyPlanRecyclerView.setAdapter(baseQuickAdapter);
        return inflate;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

    }


    private BaseQuickAdapter initAdapter(BaseQuickAdapter baseQuickAdapter) {
        baseQuickAdapter.openLoadAnimation();
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MySection mySection = mCreateFragmentData.get(position);
                if (mySection.isHeader()) {
                    Log.i(TAG, "initAdapter1");
                } else {
                    if (mContext instanceof JumpInterface) {
                        DetailFragment targetFragment = DetailFragment.newInstance(mCreateFragmentData.get(position)
                                .getData().getTitle());
                        ((JumpInterface) mContext).jumpFragment(targetFragment);
                    }
                    Log.i(TAG, "initAdapter2");
                }
            }
        });
        return baseQuickAdapter;
    }

}
