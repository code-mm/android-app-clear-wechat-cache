package com.ms.module.wechat.clear;

import android.app.Activity;
import android.content.Intent;

import com.ms.module.wechat.clear.ui.activity.wechat.ing.WeChatClearScanIngActivity;

public class WeChatClearSdk {


    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, WeChatClearScanIngActivity.class));
    }


}
