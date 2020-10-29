package com.ms.module.wechat.clear.activity.image;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.WeChatClearModule;
import com.ms.module.wechat.clear.activity.file.FileChildNode;
import com.ms.module.wechat.clear.activity.file.FileHeaderNode;
import com.ms.module.wechat.clear.base.BaseAppCompatActivity;
import com.ms.module.wechat.clear.base.RxView;
import com.ms.module.wechat.clear.base.StatusBarUtil;
import com.ms.module.wechat.clear.dialog.DialogDelete;
import com.ms.module.wechat.clear.repository.WeChatScanDataRepository;
import com.ms.module.wechat.clear.utils.ListDataUtils;


import java.util.ArrayList;
import java.util.List;

public class ImageDetailsActivity extends BaseAppCompatActivity implements RxView.Action1<View> {

    private RecyclerView recyclerView;

    private ImageAdapter adapter;

    private ImageDetailsViewModel imageDetailsViewModel;

    private List<BaseNode> datas = new ArrayList<>();

    private ImageView imageViewBack;

    private TextView textViewDelete;

    private ImageView imageViewCheck;

    private static ImageDetailsActivity instance;

    public static ImageDetailsActivity getInstance() {
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
        textViewDelete = findViewById(R.id.textViewDelete);

        imageViewCheck.setSelected(true);
        RxView.setOnClickListeners(this::onClick, imageViewBack, textViewDelete);

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
                adapter.notifyDataSetChanged();
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
        instance = this;

        imageDetailsViewModel = new ViewModelProvider(this).get(ImageDetailsViewModel.class);


        imageDetailsViewModel.getLiveDataImageData().observe(this, new Observer<List<BaseNode>>() {
            @Override
            public void onChanged(List<BaseNode> baseNodes) {
                datas.clear();
                datas.addAll(baseNodes);
                // 删除空数据
                for (int i = 0; i < datas.size(); i++) {
                    BaseNode baseNode = datas.get(i);
                    if (baseNode instanceof FileHeaderNode) {
                        FileHeaderNode headerNode = (FileHeaderNode) baseNode;
                        List<BaseNode> childNode = headerNode.getChildNode();
                        if (childNode == null || childNode.size() == 0) {
                            datas.remove(i);
                        }
                    }
                }

                if (adapter == null) {
                    adapter = new ImageAdapter();
                    recyclerView.setLayoutManager(new GridLayoutManager(ImageDetailsActivity.this, 4));
                    adapter.setList(datas);
                    recyclerView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }
        });
        imageDetailsViewModel.getMutableLiveDataImageData().postValue(null);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageViewBack) {
            WeChatClearModule.getWeChatClearCallBack().onClose(this, null);
            finish();
        } else if (view.getId() == R.id.textViewDelete) {

            DialogDelete.show(ImageDetailsActivity.this, new DialogDelete.Call() {
                @Override
                public void onCancel() {
                }

                @Override
                public void onDelete() {
                    // 去删除页面
                    WeChatClearModule.getWeChatClearCallBack().onDeleteFile(ImageDetailsActivity.this, WeChatScanDataRepository.getInstance().getFilesLength(datas));
                    WeChatScanDataRepository.getInstance().deleteFile(datas, adapter);
                }
            });
        }
    }
}
