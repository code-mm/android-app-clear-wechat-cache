package com.ms.module.wechat.clear.ui.activity.wechat.video;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.ui.base.BaseAppCompatActivity;
import com.ms.module.wechat.clear.ui.base.RxView;
import com.ms.module.wechat.clear.ui.base.StatusBarUtil;


import java.util.ArrayList;
import java.util.List;

public class VideoDetailsActivity extends BaseAppCompatActivity implements RxView.Action1<View> {


    private static final String TAG = "VideoDetailsActivity";


    private RecyclerView recyclerView;

    private VideoDetailsActivityViewModel videoDetailsActivityViewModel;

    private List<BaseNode> datas = new ArrayList<>();

    private VideoAdapter videoAdapter;


    private ImageView imageViewBack;


    private CheckBox checkBoxSelectAll;


    @Override
    protected void initView() {
        super.initView();

        recyclerView = findViewById(R.id.recyclerView);
        imageViewBack = findViewById(R.id.imageViewBack);
        checkBoxSelectAll = findViewById(R.id.checkBoxSelectAll);

        RxView.setOnClickListeners(this::onClick, imageViewBack);

        checkBoxSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    for (int i = 0; i < datas.size(); i++) {
                        BaseNode baseNode = datas.get(i);

                        if (baseNode instanceof VideoHeaderNode) {
                            VideoHeaderNode videoHeaderNode = (VideoHeaderNode) baseNode;
                            videoHeaderNode.setCheck(true);
                            List<BaseNode> childNode = videoHeaderNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof VideoChildNode) {
                                    VideoChildNode videoChildNode = (VideoChildNode) baseNode1;
                                    videoChildNode.setCheck(true);
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < datas.size(); i++) {
                        BaseNode baseNode = datas.get(i);

                        if (baseNode instanceof VideoHeaderNode) {
                            VideoHeaderNode videoHeaderNode = (VideoHeaderNode) baseNode;
                            videoHeaderNode.setCheck(false);
                            List<BaseNode> childNode = videoHeaderNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof VideoChildNode) {
                                    VideoChildNode videoChildNode = (VideoChildNode) baseNode1;
                                    videoChildNode.setCheck(false);
                                }
                            }
                        }
                    }
                }
                videoAdapter.notifyDataSetChanged();








            }
        });
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"), 0);
        //StatusBarUtil.setDarkMode(this);
        StatusBarUtil.setLightMode(this);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_details_video;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        videoDetailsActivityViewModel = new ViewModelProvider(this).get(VideoDetailsActivityViewModel.class);


        videoAdapter = new VideoAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(VideoDetailsActivity.this, 4));
        videoAdapter.setList(datas);
        recyclerView.setAdapter(videoAdapter);


        videoDetailsActivityViewModel.getLiveDataVideoData().observe(this, new Observer<List<BaseNode>>() {
            @Override
            public void onChanged(List<BaseNode> baseNodes) {


                Log.e(TAG, "onChanged: " + baseNodes);
                Log.e(TAG, "onChanged: " + baseNodes.size());

                datas.clear();
                datas.addAll(baseNodes);


                videoAdapter.setList(datas);


                Log.e(TAG, "onChanged: " + datas);

                videoAdapter.notifyDataSetChanged();
            }
        });

        videoDetailsActivityViewModel.getMutableLiveDataVideoData().postValue(null);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageViewBack) {
            finish();
        }
    }
}
