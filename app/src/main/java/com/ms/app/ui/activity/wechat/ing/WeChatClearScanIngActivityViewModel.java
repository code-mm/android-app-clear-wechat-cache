package com.ms.app.ui.activity.wechat.ing;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class WeChatClearScanIngActivityViewModel extends ViewModel {

    public static class RubbishScanResult {
        public long resultSize;
        public List<String> result;
    }

    public MutableLiveData<Long> getMutableLiveDataTotalScanGarbageSize() {
        return mutableLiveDataTotalScanGarbageSize;
    }

    private WeChatClearScanIngRepository weChatClearScanIngRepository;
    private MutableLiveData<Long> mutableLiveDataTotalScanGarbageSize = new MutableLiveData<>();


    // 垃圾文件
    private LiveData<List<String>> liveDataRubbish;
    private MutableLiveData<Void> mutableLiveDataRubbish = new MutableLiveData<>();


    // 聊天小视频
    private LiveData<List<String>> liveDataMp4;
    private MutableLiveData<Void> mutableLiveDataMp4 = new MutableLiveData<>();
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


    public WeChatClearScanIngActivityViewModel() {
        weChatClearScanIngRepository = new WeChatClearScanIngRepository(mutableLiveDataTotalScanGarbageSize);
        liveDataRubbish = Transformations.switchMap(mutableLiveDataRubbish, input -> weChatClearScanIngRepository.rubbish());
        liveDataMp4 = Transformations.switchMap(mutableLiveDataMp4, input -> weChatClearScanIngRepository.mp4());
        liveDataVoice = Transformations.switchMap(mutableLiveDataVoice, input -> weChatClearScanIngRepository.voice());
        liveDataImage = Transformations.switchMap(mutableLiveDataImage, input -> weChatClearScanIngRepository.image());
        liveDataReceiveFile = Transformations.switchMap(mutableLiveDataReceiveFile, input -> weChatClearScanIngRepository.receiveFile());
        liveDataEmoji = Transformations.switchMap(mutableLiveDataEmoji, input -> weChatClearScanIngRepository.emoji());
    }

    public LiveData<List<String>> getLiveDataMp4() {
        return liveDataMp4;
    }

    public MutableLiveData<Void> getMutableLiveDataMp4() {
        return mutableLiveDataMp4;
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
