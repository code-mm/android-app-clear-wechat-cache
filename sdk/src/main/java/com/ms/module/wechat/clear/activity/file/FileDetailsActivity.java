package com.ms.module.wechat.clear.activity.file;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.base.BaseAppCompatActivity;
import com.ms.module.wechat.clear.base.RxView;
import com.ms.module.wechat.clear.base.StatusBarUtil;
import com.ms.module.wechat.clear.utils.ListDataUtils;


import java.util.ArrayList;
import java.util.List;

public class FileDetailsActivity extends BaseAppCompatActivity implements RxView.Action1<View> {


    private static final String TAG = "FileDetailsActivity";

    @Override
    protected int getLayout() {
        return R.layout.activity_details_file;
    }

    private RecyclerView recyclerView;

    private FileAdapter fileAdapter;

    private FileDetailsActivityViewModel fileDetailsActivityViewModel;

    private List<BaseNode> datas = new ArrayList<>();

    private ImageView imageViewBack;


    private ImageView imageViewCheck;


    private static FileDetailsActivity instance;

    public static FileDetailsActivity getInstance() {
        return instance;
    }

    public void updateSelectAll() {
        imageViewCheck.setSelected(ListDataUtils.checkAllNode(datas));
    }


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
        imageViewCheck = findViewById(R.id.imageViewCheck);

        imageViewCheck.setSelected(true);

        RxView.setOnClickListeners(this::onClick, imageViewBack);


        imageViewCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean selected = imageViewCheck.isSelected();

                if (selected) {
                    for (int i = 0; i < datas.size(); i++) {
                        BaseNode baseNode = datas.get(i);

                        if (baseNode instanceof FileHeaderNode) {
                            FileHeaderNode headerNode = (FileHeaderNode) baseNode;
                            headerNode.setCheck(false);
                            List<BaseNode> childNode = headerNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof FileChildNode) {
                                    FileChildNode childNode1 = (FileChildNode) baseNode1;
                                    childNode1.setCheck(false);
                                }
                            }
                        }
                    }
                    imageViewCheck.setSelected(false);
                } else {
                    for (int i = 0; i < datas.size(); i++) {
                        BaseNode baseNode = datas.get(i);

                        if (baseNode instanceof FileHeaderNode) {
                            FileHeaderNode headerNode = (FileHeaderNode) baseNode;
                            headerNode.setCheck(true);
                            List<BaseNode> childNode = headerNode.getChildNode();
                            for (int j = 0; j < childNode.size(); j++) {
                                BaseNode baseNode1 = childNode.get(j);
                                if (baseNode1 instanceof FileChildNode) {
                                    FileChildNode childNode1 = (FileChildNode) baseNode1;
                                    childNode1.setCheck(true);
                                }
                            }
                        }
                    }
                    imageViewCheck.setSelected(true);
                }
                fileAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        fileDetailsActivityViewModel = new ViewModelProvider(this).get(FileDetailsActivityViewModel.class);

        fileDetailsActivityViewModel.getLiveDataFileData().observe(this, new Observer<List<BaseNode>>() {
            @Override
            public void onChanged(List<BaseNode> baseNodes) {
                datas.clear();
                datas.addAll(baseNodes);
                // 删除空数据
                for (int i = 0; i < datas.size(); i++) {
                    BaseNode baseNode = datas.get(i);
                    if (baseNode instanceof FileHeaderNode) {
                        FileHeaderNode videoHeaderNode = (FileHeaderNode) baseNode;
                        List<BaseNode> childNode = videoHeaderNode.getChildNode();
                        if (childNode == null || childNode.size() == 0) {
                            datas.remove(i);
                        }
                    }
                }


                if (fileAdapter == null) {
                    fileAdapter = new FileAdapter();
                    recyclerView.setLayoutManager(new GridLayoutManager(FileDetailsActivity.this, 1));
                    fileAdapter.setList(datas);
                    recyclerView.setAdapter(fileAdapter);
                } else {
                    fileAdapter.notifyDataSetChanged();
                }
            }
        });
        fileDetailsActivityViewModel.getMutableLiveDataFileData().postValue(null);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageViewBack) {
            finish();
        }
    }
}
