package com.ms.app.ui.activity.wechat.ing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ms.app.R;
import com.ms.app.ui.activity.wechat.clear.WeChatClearActivity;
import com.ms.app.utils.ByteSizeToStringUnitUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 扫描中页面
 */
public class WeChatClearScanIngActivity extends AppCompatActivity {


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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wechat_clear_scan_ing);
        textViewTotalScanGarbageSize = findViewById(R.id.textViewTotalScanGarbageSize);
        weChatClearScanIngActivityViewModel = new ViewModelProvider(this).get(WeChatClearScanIngActivityViewModel.class);

        observerTotalScanGarbageSize = new Observer<Long>() {
            @Override
            public void onChanged(Long size) {
                Log.e(TAG, "onChanged: " + size);
                if (size != null) {
                    textViewTotalScanGarbageSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(size) + "");
                }
            }
        };

        weChatClearScanIngActivityViewModel.getMutableLiveDataTotalScanGarbageSize().observe(this, observerTotalScanGarbageSize);


        observerRubbish = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {

                if (!rubbishFinish) {
                    rubbishFinish = true;
                    datas.get(0).status = WeChatScanIngRecyclerViewAdapter.SCAN_STATUS.FINISH;
                    weChatClearScanIngActivityViewModel.getMutableLiveDataMp4().postValue(null);
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

        weChatClearScanIngActivityViewModel.getLiveDataMp4().observe(this, observerMp4);


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
                    startActivity(new Intent(WeChatClearScanIngActivity.this, WeChatClearActivity.class));
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
    protected void onRestart() {
        super.onRestart();


        if (!weChatClearScanIngActivityViewModel.getLiveDataRubbish().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataRubbish().observe(this, observerRubbish);
        }
        if (!weChatClearScanIngActivityViewModel.getLiveDataMp4().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataMp4().observe(this, observerMp4);
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
    protected void onPause() {
        super.onPause();

        if (weChatClearScanIngActivityViewModel.getLiveDataRubbish().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataRubbish().removeObservers(this);
        }
        if (weChatClearScanIngActivityViewModel.getLiveDataMp4().hasActiveObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataMp4().removeObservers(this);
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
        if (weChatClearScanIngActivityViewModel.getLiveDataMp4().hasObservers()) {
            weChatClearScanIngActivityViewModel.getLiveDataMp4().removeObservers(this);
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
}
