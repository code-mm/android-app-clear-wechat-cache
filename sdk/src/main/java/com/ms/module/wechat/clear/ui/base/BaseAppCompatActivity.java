package com.ms.module.wechat.clear.ui.base;


import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class BaseAppCompatActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        if (isFullScreen()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

            final View decorView = getWindow().getDecorView();
            final int uiOption = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

            decorView.setSystemUiVisibility(uiOption);


            decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                        decorView.setSystemUiVisibility(uiOption);
                    }
                }
            });
        }

        super.onCreate(savedInstanceState);

        if (getLayout() != 0) {
            setContentView(getLayout());
            // 设置沉浸式
            setStatusBar();
            // 初始化控件
            initView();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected void initView() {
    }


    protected int getLayout() {
        return 0;
    }

    protected boolean isFullScreen() {
        return false;
    }

    protected void setStatusBar() {
        //这里做了两件事情，1.使状态栏透明并使contentView填充到状态栏 2.预留出状态栏的位置，防止界面上的控件离顶部靠的太近。这样就可以实现开头说的第二种情况的沉浸式状态栏了
//        StatusBarUtil.setTransparent(this);
//        StatusBarUtil.setColor(this, Color.parseColor("#4FACF2"));

//        StatusBarUtil.setColor(this, Color.parseColor("#F58533"), 0);
//        StatusBarUtil.setDarkMode(this);

    }
}