package com.ms.module.wechat.clear.ui.activity.wechat.image;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.data.WeChatScanDataRepository;

import java.util.List;

public class ImageDetailsViewModel extends ViewModel {
    private LiveData<List<BaseNode>> liveDataImageData;
    private MutableLiveData<Void> mutableLiveDataImageData = new MutableLiveData<>();


    public ImageDetailsViewModel() {
        liveDataImageData = Transformations.switchMap(mutableLiveDataImageData, input -> WeChatScanDataRepository.getInstance().getImageData());
    }

    public LiveData<List<BaseNode>> getLiveDataImageData() {
        return liveDataImageData;
    }

    public MutableLiveData<Void> getMutableLiveDataImageData() {
        return mutableLiveDataImageData;
    }
}
