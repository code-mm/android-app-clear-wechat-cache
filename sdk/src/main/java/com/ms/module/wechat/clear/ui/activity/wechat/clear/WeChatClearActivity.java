package com.ms.module.wechat.clear.ui.activity.wechat.clear;

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
import com.ms.module.wechat.clear.data.WeChatScanDataRepository;
import com.ms.module.wechat.clear.ui.base.BaseAppCompatActivity;
import com.ms.module.wechat.clear.ui.base.RxView;
import com.ms.module.wechat.clear.ui.base.StatusBarUtil;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;


import java.util.ArrayList;
import java.util.List;

/**
 * 清理
 */
public class WeChatClearActivity extends BaseAppCompatActivity implements RxView.Action1<View> {

    private static final String TAG = "WeChatClearActivity";

    private RecyclerView recyclerView;

    private Adapter adapter;

    private List<BaseNode> groupDatas = new ArrayList<>();

    private WeChatClearActivityViewModel weChatClearActivityViewModel;

    private TextView textViewTotalScanGarbageSize;

    private TextView textViewClear;

    private TextView textViewSizeUnit;
    private Observer<List<BaseNode>> observerDatas;

    private ImageView imageViewBack;


    @Override
    protected int getLayout() {
        return R.layout.activity_wechat_clear_finsh;
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setColor(this, Color.parseColor("#4FACF2"), 0);
        StatusBarUtil.setDarkMode(this);
    }

    @Override
    protected void initView() {
        super.initView();
        recyclerView = findViewById(R.id.recyclerView);
        textViewTotalScanGarbageSize = findViewById(R.id.textViewTotalScanGarbageSize);
        textViewClear = findViewById(R.id.textViewClear);
        textViewSizeUnit = findViewById(R.id.textViewSizeUnit);
        imageViewBack = findViewById(R.id.imageViewBack);
        RxView.setOnClickListeners(this::onClick, textViewClear, imageViewBack);


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weChatClearActivityViewModel = new ViewModelProvider(this).get(WeChatClearActivityViewModel.class);

        try {

            if (WeChatScanDataRepository.getInstance().getMutableLiveDataTotalScanGarbageSize() != null) {
                Long value = WeChatScanDataRepository.getInstance().getMutableLiveDataTotalScanGarbageSize().getValue();
                if (value == null) {
                    value = new Long(0);
                }
                String[] res = ByteSizeToStringUnitUtils.byteToStringUnitS(value);
                textViewTotalScanGarbageSize.setText(res[0]);
                textViewSizeUnit.setText(res[1] + "\n可清理");
            } else {

            }

            textViewClear.setText("立即清理");

        } catch (Exception e) {
            e.printStackTrace();
        }


        adapter = new Adapter();
        adapter.setList(groupDatas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(WeChatClearActivity.this, 1));
        adapter.notifyDataSetChanged();

        observerDatas = new Observer<List<BaseNode>>() {
            @Override
            public void onChanged(List<BaseNode> baseNodes) {
                groupDatas.clear();
                groupDatas.addAll(baseNodes);
                adapter.setList(groupDatas);
                adapter.notifyDataSetChanged();
            }
        };

        weChatClearActivityViewModel.getLiveDataDatas().observe(this, observerDatas);
        weChatClearActivityViewModel.getMutableLiveDataDatas().postValue(null);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.textViewClear) {

        } else if (view.getId() == R.id.imageViewBack) {
            finish();
        }
    }
}
