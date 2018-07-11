package com.xingguang.www.xinguang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xingguang.www.xinguang.FragmentUtil.FragmentConfig;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.datamanager.DataImpl;
import com.xingguang.www.xinguang.entity.CreateFragmentBean;
import com.xingguang.www.xinguang.homeadapter.MyPlanAdapter;
import com.xingguang.www.xinguang.homeadapter.RecommendPlanAdapter;

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


    private CreateFragmentBean mCreateFragmentData;


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
        initData();
        initView(inflate);
        Log.i(TAG, "onCreateView");
        return inflate;
    }

    private void initData() {
        mCreateFragmentData = DataImpl.getCreateFragmentData();
    }

    private void initView(View inflate) {
        mMyPlanRecyclerView = inflate.findViewById(R.id.recyclerview_my_plan);
        BaseQuickAdapter homeAdapter = new MyPlanAdapter(R.layout.item_create_fragment, mCreateFragmentData
                .getMyplans());
        initRecycleView(mMyPlanRecyclerView, initAdapter(homeAdapter));

        mRecommendRecyclerView = inflate.findViewById(R.id.recyclerview_recommedn_plan);
        BaseQuickAdapter recommendPlanAdapter = new RecommendPlanAdapter(R.layout.item_create_fragment,
                mCreateFragmentData.getRecommendplans());
        initRecycleView(mRecommendRecyclerView, initAdapter(recommendPlanAdapter));
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


    private BaseQuickAdapter initAdapter(BaseQuickAdapter baseQuickAdapter) {
        baseQuickAdapter.openLoadAnimation();
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //                Intent intent = new Intent(HomeActivity.this, ACTIVITY[position]);
                //                startActivity(intent);
            }
        });
        return baseQuickAdapter;
    }

    private void initRecycleView(RecyclerView recyclerView, BaseQuickAdapter baseQuickAdapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(baseQuickAdapter);
    }


}
