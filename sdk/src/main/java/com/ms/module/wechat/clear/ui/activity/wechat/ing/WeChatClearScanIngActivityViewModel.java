package com.ms.module.wechat.clear.ui.activity.wechat.ing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;


import com.ms.module.wechat.clear.data.WeChatScanDataRepository;

import java.util.List;


public class WeChatClearScanIngActivityViewModel extends ViewModel {

    public static class RubbishScanResult {
        public long resultSize;
        public List<String> result;
    }

    public MutableLiveData<Long> getMutableLiveDataTotalScanGarbageSize() {
        return mutableLiveDataTotalScanGarbageSize;
    }


    private MutableLiveData<Long> mutableLiveDataTotalScanGarbageSize = new MutableLiveData<>();


    // 垃圾文件
    private LiveData<List<String>> liveDataRubbish;
    private MutableLiveData<Void> mutableLiveDataRubbish = new MutableLiveData<>();


    // 聊天小视频
    private LiveData<List<String>> liveDataVideo;
    private MutableLiveData<Void> mutableLiveDataVideo = new MutableLiveData<>();
    // 图片
    private LiveData<List<String>> liveDataImage;
    private MutableLiveData<Void> mutableLiveDataImage = new MutableLiveData<>();

    // 语音
    private LiveData<List<String>> liveDataVoice;
    private MutableLiveData<Void> mutableLiveDataVoice = new MutableLiveData<>();


    // 文件
    private LiveData<List<String>> liveDataReceiveFile;
    private MutableLiveData<Void> mutableLiveDataReceiveFile = new MutableLiveData<>();

    // 表情
    private LiveData<List<String>> liveDataEmoji;
    private MutableLiveData<Void> mutableLiveDataEmoji = new MutableLiveData<>();


    public void clearData() {
        WeChatScanDataRepository.getInstance().clear();
    }


    public WeChatClearScanIngActivityViewModel() {


        WeChatScanDataRepository.getInstance().setMutableLiveDataTotalScanGarbageSize(mutableLiveDataTotalScanGarbageSize);


        liveDataRubbish = Transformations.switchMap(mutableLiveDataRubbish, input -> WeChatScanDataRepository.getInstance().rubbish());
        liveDataVideo = Transformations.switchMap(mutableLiveDataVideo, input -> WeChatScanDataRepository.getInstance().video());
        liveDataVoice = Transformations.switchMap(mutableLiveDataVoice, input -> WeChatScanDataRepository.getInstance().voice());
        liveDataImage = Transformations.switchMap(mutableLiveDataImage, input -> WeChatScanDataRepository.getInstance().image());
        liveDataReceiveFile = Transformations.switchMap(mutableLiveDataReceiveFile, input -> WeChatScanDataRepository.getInstance().receiveFile());
        liveDataEmoji = Transformations.switchMap(mutableLiveDataEmoji, input -> WeChatScanDataRepository.getInstance().emoji());

    }

    public LiveData<List<String>> getLiveDataVideo() {
        return liveDataVideo;
    }

    public MutableLiveData<Void> getMutableLiveDataVideo() {
        return mutableLiveDataVideo;
    }

    public LiveData<List<String>> getLiveDataEmoji() {
        return liveDataEmoji;
    }

    public LiveData<List<String>> getLiveDataImage() {
        return liveDataImage;
    }

    public MutableLiveData<Void> getMutableLiveDataEmoji() {
        return mutableLiveDataEmoji;
    }

    public MutableLiveData<Void> getMutableLiveDataImage() {
        return mutableLiveDataImage;
    }

    public LiveData<List<String>> getLiveDataReceiveFile() {
        return liveDataReceiveFile;
    }

    public MutableLiveData<Void> getMutableLiveDataReceiveFile() {
        return mutableLiveDataReceiveFile;
    }

    public LiveData<List<String>> getLiveDataRubbish() {
        return liveDataRubbish;
    }

    public MutableLiveData<Void> getMutableLiveDataRubbish() {
        return mutableLiveDataRubbish;
    }

    public LiveData<List<String>> getLiveDataVoice() {
        return liveDataVoice;
    }

    public MutableLiveData<Void> getMutableLiveDataVoice() {
        return mutableLiveDataVoice;
    }
}
