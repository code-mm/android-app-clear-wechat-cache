package com.ms.module.wechat.clear.activity.file;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.repository.WeChatScanDataRepository;

import java.util.List;

public class FileDetailsActivityViewModel extends ViewModel {

    private LiveData<List<BaseNode>> liveDataFileData;
    private MutableLiveData<Void> mutableLiveDataFileData = new MutableLiveData<>();




    public FileDetailsActivityViewModel() {
        liveDataFileData = Transformations.switchMap(mutableLiveDataFileData, input -> WeChatScanDataRepository.getInstance().getFileData());

    }

    public LiveData<List<BaseNode>> getLiveDataFileData() {
        return liveDataFileData;
    }

    public MutableLiveData<Void> getMutableLiveDataFileData() {
        return mutableLiveDataFileData;
    }
}
