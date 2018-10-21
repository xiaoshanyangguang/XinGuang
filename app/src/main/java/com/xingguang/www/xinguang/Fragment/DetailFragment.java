package com.xingguang.www.xinguang.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;
import com.xingguang.www.xinguang.R;
import com.xingguang.www.xinguang.adapter.DetailSectionAdapter;
import com.xingguang.www.xinguang.base.JumpInterface;
import com.xingguang.www.xinguang.datamanager.DataImpl;
import com.xingguang.www.xinguang.entity.DetailSection;

import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/6/3 12:34
 * @描述 TODO
 */

public class DetailFragment extends BaseFragment {
    private static final String              ID = "id";
    private              String              mString;
    private              List<DetailSection> mDetailData;

    public static DetailFragment newInstance(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mString = arguments.getString(ID);
    }


    @Override
    protected void initData(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        mDetailData = DataImpl.getDetailSampleData();
        super.initData(inflater, container, savedInstanceState);

    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_detail, container, false);
        CommonTitleBar commonTitleBar = inflate.findViewById(R.id.titlebar);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerview_detail);
        commonTitleBar.setTitle(mString);
        DetailSectionAdapter sectionAdapter = new DetailSectionAdapter(R.layout.item_detail_nomal, R.layout.item_detail_head,
                mDetailData);
        BaseQuickAdapter baseQuickAdapter = initAdapter(sectionAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,1));
        recyclerView.setAdapter(baseQuickAdapter);
        return inflate;
    }

    private BaseQuickAdapter initAdapter(BaseQuickAdapter baseQuickAdapter) {
        baseQuickAdapter.openLoadAnimation();
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DetailSection detailSection = mDetailData.get(position);
                if (detailSection.isHeader()) {
                } else {
                    if (mContext instanceof JumpInterface) {
                        ContentFragment targetFragment = ContentFragment.newInstance(detailSection.getData().getContent());
                        ((JumpInterface) mContext).jumpFragment(targetFragment);
                    }
                }
            }
        });
        return baseQuickAdapter;
    }
}
