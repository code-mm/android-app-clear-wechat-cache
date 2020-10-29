package com.ms.module.wechat.clear.ui.activity.wechat.ing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.ui.activity.wechat.clear.WeChatClearActivity;
import com.ms.module.wechat.clear.ui.base.BaseAppCompatActivity;
import com.ms.module.wechat.clear.ui.base.RxView;
import com.ms.module.wechat.clear.ui.base.StatusBarUtil;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;
import com.ms.view.loading.FastChargeView;

import java.util.ArrayList;
import java.util.List;

/**
 * 扫描中页面
 */
public class WeChatClearScanIngActivity extends BaseAppCompatActivity implements RxView.Action1<View> {


    private static final String TAG = "WeChatClearScanIngActiv";
    private WeChatClearScanIngActivityViewModel weChatClearScanIngActivityViewModel;
    private TextView textViewTotalScanGarbageSize;
    private RecyclerView recyclerView;
    private WeChatScanIngRecyclerViewAdapter weChatScanIngRecyclerViewAdapter;
    private List<WeChatScanIngRecyclerViewAdapter.WeChatScanIngRecyclerViewAdapterItem> datas = new ArrayList<>();

    private List<String> rubbishList = new ArrayList<>();
    private List<String> voideList = new ArrayList<>();
    private List<String> imageList = new ArrayList<>();
    private List<String> voiceList = new ArrayList<>();
    private List<String> fileList = new ArrayList<>();
    private List<String> emojiList = new ArrayList<>();

    private Observer<Long> observerTotalScanGarbageSize;
    private Observer<List<String>> observerRubbish;
    private Observer<List<String>> observerMp4;
    private Observer<List<String>> observerImage;
    private Observer<List<String>> observerVoice;
    private Observer<List<String>> observerReceiveFile;
    private Observer<List<String>> observerEmoji;


    private boolean rubbishFinish = false;
    private boolean mp4Finish = false;
    private boolean imageFinish = false;
    private boolean voiceFinish = false;
    private boolean receiveFileFinish = false;
    private boolean emojiFinish = false;


    private ImageView imageViewBack;

    private FastChargeView fastChargeView;


    @Override
    protected void setStatusBar() {
        super.setStatusBar();

        StatusBarUtil.setColor(this, Color.parseColor("#4FACF2"), 0);
        StatusBarUtil.setDarkMode(this);

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_wechat_clear_scan_ing;
    }


    @Override
    protected void initView() {
        super.initView();
        textViewTotalScanGarbageSize = findViewById(R.id.textViewTotalScanGarbageSize);
        imageViewBack = findViewById(R.id.imageViewBack);
        fastChargeView = findViewById(R.id.fastChargeView);


        RxView.setOnClickListeners(this::onClick, imageViewBack);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        weChatClearScanIngActivityViewModel = new ViewModelProvider(this).get(WeChatClearScanIngActivityViewModel.class);
        weChatClearScanIngActivityViewModel.clearData();

        fastChargeView.setDuration(800);
        fastChargeView.startAnimation();

        observerTotalScanGarbageSize = new Observer<Long>() {
            @Override
            public void onChanged(Long size) {

                if (size != null) {

                    textViewTotalScanGarbageSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(size) + "");
                }
            }
        };
        weChatClearScanIngActivityViewModel.getMutableLiveDataTotalScanGarbageSize().observe(this, observerTotalScanGarbageSize);
//        weChatClearScanIngActivityViewModel.clearData();


        observerRubbish = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                if (!rubbishFinish) {

                    rubbishFinish = true;
                    datas.get(0).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.FINISH;
                    weChatClearScanIngActivityViewModel.getMutableLiveDataVideo().postValue(null);
                    datas.get(1).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.ING;
                    weChatScanIngRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };

        weChatClearScanIngActivityViewModel.getLiveDataRubbish().observe(this, observerRubbish);


        observerMp4 = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                if (!mp4Finish) {

                    mp4Finish = true;
                    datas.get(1).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.FINISH;
                    weChatClearScanIngActivityViewModel.getMutableLiveDataImage().postValue(null);
                    datas.get(2).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.ING;
                    weChatScanIngRecyclerViewAdapter.notifyDataSetChanged();
                }


            }
        };

        weChatClearScanIngActivityViewModel.getLiveDataVideo().observe(this, observerMp4);


        observerImage = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                if (!imageFinish) {

                    imageFinish = true;
                    datas.get(2).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.FINISH;
                    weChatClearScanIngActivityViewModel.getMutableLiveDataVoice().postValue(null);
                    datas.get(3).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.ING;
                    weChatScanIngRecyclerViewAdapter.notifyDataSetChanged();
                }


            }
        };

        weChatClearScanIngActivityViewModel.getLiveDataImage().observe(this, observerImage);

        observerVoice = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                if (!voiceFinish) {

                    voiceFinish = true;
                    datas.get(3).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.FINISH;
                    weChatClearScanIngActivityViewModel.getMutableLiveDataReceiveFile().postValue(null);
                    datas.get(4).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.ING;
                    weChatScanIngRecyclerViewAdapter.notifyDataSetChanged();
                }


            }
        };

        weChatClearScanIngActivityViewModel.getLiveDataVoice().observe(this, observerVoice);
        observerReceiveFile = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                if (!receiveFileFinish) {

                    receiveFileFinish = true;
                    datas.get(4).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.FINISH;
                    weChatClearScanIngActivityViewModel.getMutableLiveDataEmoji().postValue(null);
                    datas.get(5).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.ING;
                    weChatScanIngRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        };

        weChatClearScanIngActivityViewModel.getLiveDataReceiveFile().observe(this, observerReceiveFile);


        observerEmoji = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {


                if (!emojiFinish) {

                    emojiFinish = true;
                    datas.get(5).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.FINISH;
                    weChatScanIngRecyclerViewAdapter.notifyDataSetChanged();
                    Intent intent = new Intent(WeChatClearScanIngActivity.this, WeChatClearActivity.class);
                    startActivity(intent);
                    finish();

                }
            }
        };

        weChatClearScanIngActivityViewModel.getLiveDataEmoji().observe(this, observerEmoji);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        datas.add(new WeChatScanIngRecyclerViewAdapter.WeChatScanIngRecyclerViewAdapterItem("垃圾文件", R.drawable.image_rubbish));
        datas.add(new WeChatScanIngRecyclerViewAdapter.WeChatScanIngRecyclerViewAdapterItem("聊天小视频", R.drawable.image_video));
        datas.add(new WeChatScanIngRecyclerViewAdapter.WeChatScanIngRecyclerViewAdapterItem("聊天图片", R.drawable.image_image));
        datas.add(new WeChatScanIngRecyclerViewAdapter.WeChatScanIngRecyclerViewAdapterItem("聊天语音", R.drawable.image_voice));
        datas.add(new WeChatScanIngRecyclerViewAdapter.WeChatScanIngRecyclerViewAdapterItem("文件", R.drawable.image_file));
        datas.add(new WeChatScanIngRecyclerViewAdapter.WeChatScanIngRecyclerViewAdapterItem("聊天表情包", R.drawable.image_emoji));

        weChatScanIngRecyclerViewAdapter = new WeChatScanIngRecyclerViewAdapter(this, datas);
        recyclerView.setAdapter(weChatScanIngRecyclerViewAdapter);


        datas.get(0).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.ING;
        weChatClearScanIngActivityViewModel.getMutableLiveDataRubbish().postValue(null);
        weChatScanIngRecyclerViewAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();


        if (!weChatClearScanIngActivityViewModel.getLiveDataRubbish().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataRubbish().observe(this, observerRubbish);
        }
        if (!weChatClearScanIngActivityViewModel.getLiveDataVideo().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataVideo().observe(this, observerMp4);
        }
        if (!weChatClearScanIngActivityViewModel.getLiveDataImage().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataImage().observe(this, observerImage);
        }
        if (!weChatClearScanIngActivityViewModel.getLiveDataVoice().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataVoice().observe(this, observerVoice);
        }
        if (!weChatClearScanIngActivityViewModel.getLiveDataReceiveFile().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataReceiveFile().observe(this, observerReceiveFile);
        }
        if (!weChatClearScanIngActivityViewModel.getLiveDataEmoji().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataEmoji().observe(this, observerEmoji);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (weChatClearScanIngActivityViewModel.getLiveDataRubbish().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataRubbish().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataVideo().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataVideo().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataImage().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataImage().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataVoice().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataVoice().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataReceiveFile().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataReceiveFile().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataEmoji().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataEmoji().removeObservers(this);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (weChatClearScanIngActivityViewModel.getLiveDataRubbish().hasObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataRubbish().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataVideo().hasObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataVideo().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataImage().hasObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataImage().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataVoice().hasObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataVoice().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataReceiveFile().hasObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataReceiveFile().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataEmoji().hasObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataEmoji().removeObservers(this);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imageViewBack) {
            finish();
        }
    }
}
