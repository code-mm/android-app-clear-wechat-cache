package com.ms.module.wechat.clear.ui.activity.wechat.image;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

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

public class ImageDetailsActivity extends BaseAppCompatActivity implements RxView.Action1<View> {

    private RecyclerView recyclerView;

    private ImageAdapter imageAdapter;

    private ImageDetailsViewModel imageDetailsViewModel;

    private List<BaseNode> datas = new ArrayList<>();

    private ImageView imageViewBack;

    private CheckBox checkBoxSelectAll;

    private TextView textViewDelete;

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
        textViewDelete = findViewById(R.id.textViewDelete);

        RxView.setOnClickListeners(this::onClick, imageViewBack, textViewDelete);

        checkBoxSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    for (int i = 0; i < datas.size(); i++) {
                        BaseNode baseNode = datas.get(i);

                        if (baseNode instanceof ImageHeaderNode) {
                            ImageHeaderNode headerNode = (ImageHeaderNode) baseNode;
                            headerNode.setCheck(true);
                            List<BaseNode> childNode = headerNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof ImageChildNode) {
                                    ImageChildNode childNode1 = (ImageChildNode) baseNode1;
                                    childNode1.setCheck(true);
                                }
                            }
                        }
                    }

                } else {
                    for (int i = 0; i < datas.size(); i++) {
                        BaseNode baseNode = datas.get(i);

                        if (baseNode instanceof ImageHeaderNode) {
                            ImageHeaderNode headerNode = (ImageHeaderNode) baseNode;
                            headerNode.setCheck(false);
                            List<BaseNode> childNode = headerNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof ImageChildNode) {
                                    ImageChildNode childNode1 = (ImageChildNode) baseNode1;
                                    childNode1.setCheck(false);
                                }
                            }
                        }
                    }
                }

                imageAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_details_image;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageDetailsViewModel = new ViewModelProvider(this).get(ImageDetailsViewModel.class);


        imageDetailsViewModel.getLiveDataImageData().observe(this, new Observer<List<BaseNode>>() {
            @Override
            public void onChanged(List<BaseNode> baseNodes) {
                datas.clear();
                datas.addAll(baseNodes);
                // 删除空数据
                for (int i = 0; i < datas.size(); i++) {
                    BaseNode baseNode = datas.get(i);
                    if (baseNode instanceof ImageHeaderNode) {
                        ImageHeaderNode headerNode = (ImageHeaderNode) baseNode;
                        List<BaseNode> childNode = headerNode.getChildNode();
                        if (childNode == null || childNode.size() == 0) {
                            datas.remove(i);
                        }
                    }
                }

                if (imageAdapter == null) {
                    imageAdapter = new ImageAdapter();
                    recyclerView.setLayoutManager(new GridLayoutManager(ImageDetailsActivity.this, 4));
                    imageAdapter.setList(datas);
                    recyclerView.setAdapter(imageAdapter);
                } else {
                    imageAdapter.notifyDataSetChanged();
                }
            }
        });
        imageDetailsViewModel.getMutableLiveDataImageData().postValue(null);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageViewBack) {
            finish();
        } else if (view.getId() == R.id.textViewDelete) {





        }
    }
}
