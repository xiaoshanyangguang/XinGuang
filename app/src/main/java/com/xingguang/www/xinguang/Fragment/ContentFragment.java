package com.xingguang.www.xinguang.Fragment;

import android.app.Activity;
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
import com.xingguang.www.xinguang.adapter.MultipleItemQuickAdapter;
import com.xingguang.www.xinguang.base.CameraRefreshInterface;
import com.xingguang.www.xinguang.datamanager.DataImpl;
import com.xingguang.www.xinguang.entity.MultipleItem;
import com.xingguang.www.xinguang.util.CommonUtil;

import java.util.List;

/**
 * @创建者 pengbo
 * @创建时间 2018/10/16 6:43
 * @描述 TODO
 */
public class ContentFragment extends BaseFragment implements CameraRefreshInterface {
    private static final String                  TAG                  = ContentFragment.class.getName();
    private static final String PLAN_CONTENT_PIC_ID = "id";
    private              String mString;

    private List<MultipleItem> mMultipleItems;
    private MultipleItemQuickAdapter mMultipleItemQuickAdapter;

    @Override
    protected void initData(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        super.initData(inflater, container, savedInstanceState);
        mMultipleItems = DataImpl.getMultipleItemData(mString);
    }

    public static ContentFragment newInstance(String id) {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        mString = arguments.getString(PLAN_CONTENT_PIC_ID);
        CommonUtil.verifyStoragePermissions((Activity)mContext);
        Log.i(TAG, "mString" + mString);
    }

    @Override
    protected View initView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle
            savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_content, container, false);
        CommonTitleBar commonTitleBar = inflate.findViewById(R.id.titlebar);
        RecyclerView recyclerView = inflate.findViewById(R.id.recyclerview_content);
        commonTitleBar.setTitle(mString);
        mMultipleItemQuickAdapter = new MultipleItemQuickAdapter(mContext, mMultipleItems,mString);
        //        BaseQuickAdapter baseQuickAdapter = initAdapter(sectionAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 1));
        recyclerView.setAdapter(mMultipleItemQuickAdapter);
        return inflate;
    }

    private BaseQuickAdapter initAdapter(BaseQuickAdapter baseQuickAdapter) {
        baseQuickAdapter.openLoadAnimation();
        //        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
        //            @Override
        //            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //                DetailSection detailSection = mDetailData.get(position);
        //                if (detailSection.isHeader) {
        //                    Log.i(TAG, "initAdapter1");
        //                } else {
        //                    if (mContext instanceof JumpInterface) {
        //                        //                        DetailFragment targetFragment = DetailFragment
        // .newInstance(detailSection.get(position)
        //                        //                                .data.getTitle());
        //                        //                        ((JumpInterface) mContext).jumpFragment(targetFragment);
        //                    }
        //                    Log.i(TAG, "initAdapter2");
        //                }
        //            }
        //        });
        return baseQuickAdapter;
    }

    @Override
    public void refreshPictureData(String filePath) {
        mMultipleItemQuickAdapter.refreshCameraPicture(filePath);
    }
}
