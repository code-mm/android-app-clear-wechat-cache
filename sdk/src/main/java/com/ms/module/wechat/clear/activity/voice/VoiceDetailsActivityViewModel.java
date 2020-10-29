package com.ms.module.wechat.clear.activity.voice;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.repository.WeChatScanDataRepository;


import java.util.List;

public class VoiceDetailsActivityViewModel extends ViewModel {
    private LiveData<List<BaseNode>> liveDataVoiceData;
    private MutableLiveData<Void> mutableLiveDataVoiceData = new MutableLiveData<>();

    public VoiceDetailsActivityViewModel() {
        liveDataVoiceData = Transformations.switchMap(mutableLiveDataVoiceData, input -> WeChatScanDataRepository.getInstance().getVoiceData());
    }

    public LiveData<List<BaseNode>> getLiveDataVoiceData() {
        return liveDataVoiceData;
    }

    public MutableLiveData<Void> getMutableLiveDataVoiceData() {
        return mutableLiveDataVoiceData;
    }
}