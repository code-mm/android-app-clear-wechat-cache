package com.ms.module.wechat.clear;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 微信清理回调
 */
public interface IWeChatClearListener {

    // 回调 页面Acttivity
    // 广告位

    /**
     * 未安装微信
     */
    void onNotInstallWeChat();


    /**
     * 启动时间戳
     *
     * @param timestamp
     */
    void onStartTimestamp(long timestamp);


    /**
     * 进入微信清理扫描页面
     */
    void onEnterWeChatClearScan(Activity activity);

    /**
     * 扫描完成
     *
     * @param activity
     */
    void onScanFinish(Activity activity);


    /**
     * 立即清理
     *
     * @param activity
     */
    void onClearUpNow(Activity activity);

    /**
     * 立即清理
     *
     * @param activity
     */
    void onClearUpNow(Activity activity, FrameLayout view);


    /**
     * 删除文件回调
     *
     * @param size
     */
    void onDeleteFile(Activity activity, long size);


    /**
     * 关闭 微信清理模块
     *
     * @param activity
     */
    void onClose(Activity activity, FrameLayout view);

    /**
     * 关闭 微信清理模块
     *
     * @param activity
     */
    void onClose(Activity activity);

    /**
     * 当前扫描垃圾大小
     *
     * @param length
     */
    void onCurrentGarbageSize(long length);

}
