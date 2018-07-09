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
import com.xingguang.www.xinguang.activity.MainActivity;
import com.xingguang.www.xinguang.entity.HomeItem;
import com.xingguang.www.xinguang.homeadapter.HomeAdapter;

import java.util.ArrayList;

/**
 * @创建者 pengbo
 * @创建时间 2017/12/15 23:37
 * @描述 计划信息(掌握内容, 学习方式, 评价方式)展示页面
 */

public class CreateFragment extends BaseFragment implements View.OnClickListener {

    private Button mNextButton;
    private RecyclerView mRecyclerView;
    private ArrayList<HomeItem> mDataList;
    private static final String[] TITLE = {"Animation", "MultipleItem", "Header/Footer", "PullToRefresh", "Section", "EmptyView", "DragAndSwipe", "ItemClick", "ExpandableItem", "DataBinding", "UpFetchData"};
    private static final Class<?>[] ACTIVITY = {MainActivity.class, MainActivity.class, MainActivity.class, MainActivity.class, MainActivity.class, MainActivity.class, MainActivity.class, MainActivity.class, MainActivity.class, MainActivity.class,MainActivity.class};
    private static final int[] IMG = {R.drawable.gv_animation, R.drawable.gv_multipleltem, R.drawable.gv_header_and_footer, R.drawable.gv_pulltorefresh, R.drawable.gv_section, R.drawable.gv_empty, R.drawable.gv_drag_and_swipe, R.drawable.gv_item_click, R.drawable.gv_expandable, R.drawable.gv_databinding,R.drawable.gv_databinding};


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
        initData();
        initAdapter();
        Log.i(TAG, "onCreateView");
        return inflate;
    }

    private void initView(View inflate) {
//        mNextButton = (Button) inflate.findViewById(R.id.bt_create_jump);
        mRecyclerView = (RecyclerView) inflate.findViewById(R.id.recyclerview);
//        mNextButton.setOnClickListener(this);
    }

    private void initAdapter() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.activity_success, mDataList);
        homeAdapter.openLoadAnimation();
        View top = getLayoutInflater().inflate(R.layout.top_view, (ViewGroup) mRecyclerView.getParent(), false);
        homeAdapter.addHeaderView(top);
        homeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Intent intent = new Intent(HomeActivity.this, ACTIVITY[position]);
//                startActivity(intent);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(homeAdapter);
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

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[i]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
    }
}
