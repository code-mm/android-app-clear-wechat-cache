package com.ms.module.wechat.clear.activity.voice;

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
import com.ms.module.wechat.clear.WeChatClearModule;
import com.ms.module.wechat.clear.repository.WeChatScanDataRepository;
import com.ms.module.wechat.clear.activity.file.FileChildNode;
import com.ms.module.wechat.clear.activity.file.FileHeaderNode;
import com.ms.module.wechat.clear.base.BaseAppCompatActivity;
import com.ms.module.wechat.clear.base.RxView;
import com.ms.module.wechat.clear.base.StatusBarUtil;
import com.ms.module.wechat.clear.dialog.DialogDelete;


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

    private ImageView imageViewCheck;

    private TextView textViewDelete;

    private static VoiceDetailsActivity instance;

    public static VoiceDetailsActivity getInstance() {
        return instance;
    }

    public void updateSelectAll() {

        boolean check = true;
        for (int i = 0; i < datas.size(); i++) {
            BaseNode baseNode = datas.get(i);

            if (baseNode instanceof FileHeaderNode) {
                FileHeaderNode headerNode = (FileHeaderNode) baseNode;
                List<BaseNode> childNode = headerNode.getChildNode();
                for (int j = 0; j < childNode.size(); j++) {
                    BaseNode baseNode1 = childNode.get(j);
                    if (baseNode1 instanceof FileChildNode) {
                        FileChildNode childNode1 = (FileChildNode) baseNode1;
                        if (!childNode1.isCheck()) {
                            check = false;
                        }
                    }
                }
            }
        }
        imageViewCheck.setSelected(check);
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
        RxView.setOnClickListeners(this::onClick, imageViewBack, textViewDelete);


        imageViewCheck.setSelected(true);


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
                voiceAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;

        voiceDetailsActivityViewModel = new ViewModelProvider(this).get(VoiceDetailsActivityViewModel.class);

        voiceDetailsActivityViewModel.getLiveDataVoiceData().observe(this, new Observer<List<BaseNode>>() {
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
        } else if (view.getId() == R.id.textViewDelete) {

            DialogDelete.show(this, new DialogDelete.Call() {
                @Override
                public void onCancel() {

                }
                @Override
                public void onDelete() {
                    long filesLength = WeChatScanDataRepository.getInstance().getFilesLength(datas);
                    WeChatClearModule.getWeChatClearCallBack().onDeleteFile(VoiceDetailsActivity.this, filesLength);
                    WeChatScanDataRepository.getInstance().deleteFile(datas, voiceAdapter);
                }
            });
        }
    }
}
