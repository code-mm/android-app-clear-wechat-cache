package com.ms.module.wechat.clear.ui.activity.wechat.clear;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.data.WeChatScanDataRepository;

import java.util.List;

public class WeChatClearActivityViewModel extends ViewModel {

    private LiveData<List<BaseNode>> liveDataDatas;

    private MutableLiveData<Void> mutableLiveDataDatas = new MutableLiveData<>();

    public LiveData<List<BaseNode>> getLiveDataDatas() {
        return liveDataDatas;
    }

    public MutableLiveData<Void> getMutableLiveDataDatas() {
        return mutableLiveDataDatas;
    }

    public WeChatClearActivityViewModel() {
        liveDataDatas = Transformations.switchMap(mutableLiveDataDatas, input -> WeChatScanDataRepository.getInstance().getDatas());
    }
}
