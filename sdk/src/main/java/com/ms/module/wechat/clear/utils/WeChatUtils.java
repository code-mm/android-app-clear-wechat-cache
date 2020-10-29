package com.ms.module.wechat.clear.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class WeChatUtils {
    /**
     * 微信包名
     */
    private static final String WECHAT_PACKAGE_NAME = "com.tencent.mm";

    /**
     * 判断是否安装了微信
     *
     * @param context
     * @return
     */
    public static boolean isWeixinAvilible(Context context) {
        // 获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        // 获取所有已安装程序的包信息
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (WECHAT_PACKAGE_NAME.equals(pn)) {
                    return true;
                }
            }
        }
        return false;
    }
}
