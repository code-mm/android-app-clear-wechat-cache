package com.ms.module.wechat.clear.activity.image;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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
import com.ms.module.wechat.clear.activity.emoji.EmojiDetailsActivity;
import com.ms.module.wechat.clear.activity.file.FileChildNode;


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
        ImageView imageViewCheck = baseViewHolder.findView(R.id.imageViewCheck);


        if (baseNode instanceof FileChildNode) {
            FileChildNode childNode = (FileChildNode) baseNode;
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
                    .into(imageView);
            imageViewCheck.setSelected(childNode.isCheck());


            imageViewCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (childNode.isCheck()) {
                        imageViewCheck.setSelected(false);
                        childNode.setCheck(false);
                    } else {
                        imageViewCheck.setSelected(true);
                        childNode.setCheck(true);
                    }

                    getAdapter().notifyDataSetChanged();
                    if (ImageDetailsActivity.getInstance() != null) {
                        ImageDetailsActivity.getInstance().updateSelectAll();
                    }
                    if (EmojiDetailsActivity.getInstance() != null) {
                        EmojiDetailsActivity.getInstance().updateSelectAll();
                    }
                }
            });


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
