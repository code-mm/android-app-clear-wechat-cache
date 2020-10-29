package com.ms.module.wechat.clear.ui.activity.wechat.image;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;


import org.jetbrains.annotations.NotNull;

public class ImageChildProvider extends BaseNodeProvider {

    private static final String TAG = "ImageChildProvider";

    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_details_image_child;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {
        ImageView imageView = baseViewHolder.findView(R.id.imageView);
        CheckBox checkBoxSelect = baseViewHolder.findView(R.id.checkBoxSelect);


        if (baseNode instanceof ImageChildNode) {
            ImageChildNode childNode = (ImageChildNode) baseNode;
            Glide.with(context).load(childNode.getPath())
                    .addListener(new RequestListener<Drawable>() {

                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            e.printStackTrace();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imageView)

            ;
            checkBoxSelect.setChecked(childNode.isCheck());

            baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ImageActivity.class);
                    intent.putExtra("path", childNode.getPath());
                    context.startActivity(intent);
                }
            });

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ImageActivity.class);
                    intent.putExtra("path", childNode.getPath());
                    context.startActivity(intent);
                }
            });
        }

    }
}
