package com.ms.module.wechat.clear.ui.activity.wechat.video;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.data.WeChatScanDataRepository;


import java.util.List;

public class VideoDetailsActivityViewModel extends ViewModel {

    private LiveData<List<BaseNode>> liveDataVideoData;
    private MutableLiveData<Void> mutableLiveDataVideoData = new MutableLiveData<>();


    public VideoDetailsActivityViewModel() {
        liveDataVideoData = Transformations.switchMap(mutableLiveDataVideoData, input -> WeChatScanDataRepository.getInstance().getVideoData());
    }

    public LiveData<List<BaseNode>> getLiveDataVideoData() {
        return liveDataVideoData;
    }

    public MutableLiveData<Void> getMutableLiveDataVideoData() {
        return mutableLiveDataVideoData;
    }
}
