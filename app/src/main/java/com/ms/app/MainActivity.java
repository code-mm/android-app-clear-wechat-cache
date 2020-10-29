package com.ms.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ms.module.wechat.clear.WeChatClearListener;
import com.ms.module.wechat.clear.WeChatClearModule;

public class MainActivity extends AppCompatActivity {

    private void show(String text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    private TextView textViewWeChatClear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        textViewWeChatClear = findViewById(R.id.textViewWeChatClear);
        textViewWeChatClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeChatClearModule.start(MainActivity.this, new WeChatClearListener() {
                    /**
                     * 未安装微信
                     */
                    @Override
                    public void onNotInstallWeChat() {
                        super.onNotInstallWeChat();
                        show("未安装微信");
                    }

                    /**
                     * 进入扫描页面
                     * @param activity
                     */
                    @Override
                    public void onEnterWeChatClearScan(Activity activity) {
                        super.onEnterWeChatClearScan(activity);
                        show("进入扫描页面");
                    }

                    /**
                     * 扫描完成
                     * @param activity
                     */
                    @Override
                    public void onScanFinish(Activity activity) {
                        super.onScanFinish(activity);
                        show("扫描完成");
                    }

                    /**
                     * 立即清理
                     * @param activity
                     */
                    @Override
                    public void onClearUpNow(Activity activity) {
                        super.onClearUpNow(activity);

                        show("立即清理");

                    }

                    /**
                     *
                     * @param activity
                     * @param view
                     */
                    @Override
                    public void onClearUpNow(Activity activity, FrameLayout view) {
                        super.onClearUpNow(activity, view);

                        show("立即清理,带广告位");
                    }

                    /**
                     *
                     * @param activity
                     */
                    @Override
                    public void onClose(Activity activity) {
                        super.onClose(activity);

                        show("微信清理关闭");
                    }

                    /**
                     *  关闭微信清理模块
                     * @param activity
                     * @param view
                     */
                    @Override
                    public void onClose(Activity activity, FrameLayout view) {
                        super.onClose(activity, view);
                    }

                    /**
                     * 删除文件回调
                     * @param activity
                     * @param size
                     */
                    @Override
                    public void onDeleteFile(Activity activity, long size) {
                        super.onDeleteFile(activity, size);
                    }
                });
            }
        });
    }
}
