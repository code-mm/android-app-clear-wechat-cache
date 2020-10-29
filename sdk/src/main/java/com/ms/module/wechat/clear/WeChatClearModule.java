package com.ms.module.wechat.clear;

import android.app.Activity;
import android.content.Intent;

import com.ms.module.wechat.clear.repository.WeChatScanDataRepository;
import com.ms.module.wechat.clear.activity.scan.WeChatClearScanIngActivity;
import com.ms.module.wechat.clear.utils.WeChatClearUtils;
import com.ms.module.wechat.clear.utils.WeChatUtils;

/**
 * 微信清理模块
 */
public class WeChatClearModule {

    private static IWeChatClearListener weChatClearCallBack;

    public static IWeChatClearListener getWeChatClearCallBack() {
        if (weChatClearCallBack != null) {
            return weChatClearCallBack;
        }
        return new WeChatClearListener();
    }

    public static void start(Activity activity, IWeChatClearListener callBack) {

        if (activity == null) {
            throw new NullPointerException("activity null");
        }
        weChatClearCallBack = callBack;

        // 判断是否安装了微信
        if (!WeChatUtils.isWeixinAvilible(activity)) {
            if (weChatClearCallBack != null) {
                weChatClearCallBack.onNotInstallWeChat();
            }
            return;
        }

        WeChatScanDataRepository.getInstance().clear();
        activity.startActivity(new Intent(activity, WeChatClearScanIngActivity.class));
    }


    /**
     * 获取当前垃圾总大小
     *
     * @return
     */
    public static long getCurrentGarbageSize() {
        if (WeChatScanDataRepository.getInstance().getMutableLiveDataTotalScanGarbageSize() == null) {
            return 0;
        }
        if (WeChatScanDataRepository.getInstance().getMutableLiveDataTotalScanGarbageSize().getValue() == null) {
            return 0;
        }
        return WeChatScanDataRepository.getInstance().getMutableLiveDataTotalScanGarbageSize().getValue();
    }
}
