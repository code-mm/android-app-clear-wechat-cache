package com.ms.module.wechat.clear.ui.activity.wechat.voice;

import android.graphics.Color;
import android.os.Bundle;
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
import com.ms.module.wechat.clear.ui.activity.wechat.video.VideoChildNode;
import com.ms.module.wechat.clear.ui.activity.wechat.video.VideoHeaderNode;
import com.ms.module.wechat.clear.ui.base.BaseAppCompatActivity;
import com.ms.module.wechat.clear.ui.base.RxView;
import com.ms.module.wechat.clear.ui.base.StatusBarUtil;


import java.util.ArrayList;
import java.util.List;

public class VoiceDetailsActivity extends BaseAppCompatActivity implements RxView.Action1<View> {


    private static final String TAG = "VoiceDetailsActivity";


    @Override
    protected int getLayout() {
        return R.layout.activity_details_voice;
    }

    private RecyclerView recyclerView;

    private VoiceAdapter voiceAdapter;

    private VoiceDetailsActivityViewModel voiceDetailsActivityViewModel;

    private List<BaseNode> datas = new ArrayList<>();

    private ImageView imageViewBack;


    private CheckBox checkBoxSelectAll;


    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"), 0);
        //StatusBarUtil.setDarkMode(this);
        StatusBarUtil.setLightMode(this);
    }

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

                        if (baseNode instanceof VoiceHeaderNode) {
                            VoiceHeaderNode headerNode = (VoiceHeaderNode) baseNode;
                            headerNode.setCheck(true);
                            List<BaseNode> childNode = headerNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof VideoChildNode) {
                                    VideoChildNode childNode1 = (VideoChildNode) baseNode1;
                                    childNode1.setCheck(true);
                                }
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < datas.size(); i++) {
                        BaseNode baseNode = datas.get(i);

                        if (baseNode instanceof VoiceHeaderNode) {
                            VoiceHeaderNode headerNode = (VoiceHeaderNode) baseNode;
                            headerNode.setCheck(false);
                            List<BaseNode> childNode = headerNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof VideoChildNode) {
                                    VideoChildNode childNode1 = (VideoChildNode) baseNode1;
                                    childNode1.setCheck(false);
                                }
                            }
                        }
                    }
                }

                voiceAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        voiceDetailsActivityViewModel = new ViewModelProvider(this).get(VoiceDetailsActivityViewModel.class);

        voiceDetailsActivityViewModel.getLiveDataVoiceData().observe(this, new Observer<List<BaseNode>>() {
            @Override
            public void onChanged(List<BaseNode> baseNodes) {
                datas.clear();
                datas.addAll(baseNodes);
                // 删除空数据
                for (int i = 0; i < datas.size(); i++) {
                    BaseNode baseNode = datas.get(i);
                    if (baseNode instanceof VoiceHeaderNode) {
                        VoiceHeaderNode headerNode = (VoiceHeaderNode) baseNode;
                        List<BaseNode> childNode = headerNode.getChildNode();
                        if (childNode == null || childNode.size() == 0) {
                            datas.remove(i);
                        }
                    }
                }

                if (voiceAdapter == null) {
                    voiceAdapter = new VoiceAdapter();
                    recyclerView.setLayoutManager(new GridLayoutManager(VoiceDetailsActivity.this, 1));
                    voiceAdapter.setList(datas);
                    recyclerView.setAdapter(voiceAdapter);
                } else {
                    voiceAdapter.notifyDataSetChanged();
                }
            }
        });
        voiceDetailsActivityViewModel.getMutableLiveDataVoiceData().postValue(null);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imageViewBack) {
            finish();
        }

    }
}
