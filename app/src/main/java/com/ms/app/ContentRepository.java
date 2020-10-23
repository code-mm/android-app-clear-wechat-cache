package com.ms.app;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContentRepository {


    private static final String TAG = "ContentRepository";

    public LiveData<List<String>> receivefiles(String type) {

        return new LiveData<List<String>>() {
            @Override
            protected void onActive() {
                if ("RECEIVEFILES".equals(type)) {
                    postValue(WeChatClearUtils.receiveFiles());
                } else if ("PNG".equals(type)) {
                    postValue(WeChatClearUtils.pngs());
                } else if ("EMOJI".equals(type)) {
                    postValue(WeChatClearUtils.emojis());
                } else if ("MP3".equals(type)) {
                    postValue(WeChatClearUtils.mp3s());
                } else if ("MP4".equals(type)) {
                    postValue(WeChatClearUtils.mp4s());
                } else if ("IMAGE".equals(type)) {
                    postValue(WeChatClearUtils.images());
                } else if ("VOICE2".equals(type)) {
                    postValue(WeChatClearUtils.voice2());
                }
            }
        };
    }
}
