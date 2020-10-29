package com.ms.module.wechat.clear.activity.image;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnMatrixChangedListener;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.OnSingleFlingListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.base.BaseAppCompatActivity;
import com.ms.module.wechat.clear.base.RxView;
import com.ms.module.wechat.clear.base.StatusBarUtil;


import java.io.File;

/**
 * 图片查看页面
 */
public class ImageActivity extends BaseAppCompatActivity implements RxView.Action1<View> {
    private PhotoView photoView;

    private ImageView imageViewBack;


    @Override
    protected int getLayout() {
        return R.layout.activity_image;
    }

    @Override
    protected void setStatusBar() {
        super.setStatusBar();
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"), 0);
        //StatusBarUtil.setDarkMode(this);
        StatusBarUtil.setLightMode(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageViewBack = findViewById(R.id.imageViewBack);
        RxView.setOnClickListeners(this::onClick, imageViewBack);

        photoView = findViewById(R.id.photoView);
        String path = getIntent().getStringExtra("path");
        Glide.with(this).load(new File(path)).into(photoView);


        // Lets attach some listeners, not required though!
        photoView.setOnMatrixChangeListener(new MatrixChangeListener());
        photoView.setOnPhotoTapListener(new PhotoTapListener());
        photoView.setOnSingleFlingListener(new SingleFlingListener());

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imageViewBack) {
            finish();
        }

    }

    private class PhotoTapListener implements OnPhotoTapListener {

        @Override
        public void onPhotoTap(ImageView view, float x, float y) {
            float xPercentage = x * 100f;
            float yPercentage = y * 100f;
        }
    }

    private class MatrixChangeListener implements OnMatrixChangedListener {

        @Override
        public void onMatrixChanged(RectF rect) {
        }
    }

    private class SingleFlingListener implements OnSingleFlingListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return true;
        }
    }
}
