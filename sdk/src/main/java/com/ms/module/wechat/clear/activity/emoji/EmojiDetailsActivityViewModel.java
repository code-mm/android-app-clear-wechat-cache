package com.ms.module.wechat.clear.activity.emoji;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.repository.WeChatScanDataRepository;

import java.util.List;

public class EmojiDetailsActivityViewModel extends ViewModel {

    private LiveData<List<BaseNode>> liveDataEmojiData;
    private MutableLiveData<Void> mutableLiveDataEmojiData = new MutableLiveData<>();

    public EmojiDetailsActivityViewModel() {
        liveDataEmojiData = Transformations.switchMap(mutableLiveDataEmojiData, input -> WeChatScanDataRepository.getInstance().getEmojiData());
    }

    public LiveData<List<BaseNode>> getLiveDataEmojiData() {
        return liveDataEmojiData;
    }

    public MutableLiveData<Void> getMutableLiveDataEmojiData() {
        return mutableLiveDataEmojiData;
    }

}