package com.ms.app;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class ContentActivityViewModel extends ViewModel {

    public MutableLiveData<String> fileSearchMutableLiveData = new MutableLiveData<>();
    public LiveData<List<String>> fileSearchLiveData;
    ContentRepository repository;


    public ContentActivityViewModel() {
        repository = new ContentRepository();
        fileSearchLiveData = Transformations.switchMap(fileSearchMutableLiveData, input -> repository.receivefiles(input));
    }
}
