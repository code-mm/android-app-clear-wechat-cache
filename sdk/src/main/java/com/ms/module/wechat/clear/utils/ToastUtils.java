package com.ms.module.wechat.clear.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.util.HashMap;


public class ToastUtils {


    private static Handler handler = new Handler(Looper.getMainLooper());


    private ToastUtils() {
    }

    private static final ToastUtils toastUtils = new ToastUtils();

    public static ToastUtils getInstance() {
        return toastUtils;
    }

    private static Toast toast;

    public static void show(Context context, final String s) {

        if (s == null) {
            return;
        }
        if ("main".equals(Thread.currentThread().getName())) {
            if (toast == null) {
                toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
            } else {
                toast.setText(s);
            }
            toast.show();
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (toast == null) {
                        toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
                    } else {
                        toast.setText(s);
                    }
                    toast.show();
                }
            });
        }
    }
}