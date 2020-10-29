package com.ms.module.wechat.clear.ui.activity.wechat.clear;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.ui.activity.wechat.emoji.EmojiDetailsActivity;
import com.ms.module.wechat.clear.ui.activity.wechat.file.FileDetailsActivity;
import com.ms.module.wechat.clear.ui.activity.wechat.image.ImageDetailsActivity;
import com.ms.module.wechat.clear.ui.activity.wechat.video.VideoDetailsActivity;
import com.ms.module.wechat.clear.ui.activity.wechat.voice.VoiceDetailsActivity;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;


import org.jetbrains.annotations.NotNull;

public class DownChildProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 4;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_wechat_clear_down_child;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {

        ImageView imageViewIcon = baseViewHolder.findView(R.id.imageViewIcon);
        TextView textViewName = baseViewHolder.findView(R.id.textViewName);
        TextView textViewSize = baseViewHolder.findView(R.id.textViewSize);
        ImageView imageViewEnter = baseViewHolder.findView(R.id.imageViewEnter);


        if (baseNode instanceof DownChildNode) {
            DownChildNode downChildNode = (DownChildNode) baseNode;
            Glide.with(getContext()).load(downChildNode.getIcon()).into(imageViewIcon);
            textViewName.setText(downChildNode.getName());
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(downChildNode.getSize()));
        }

        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("聊天图片".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, ImageDetailsActivity.class));
                } else if ("聊天表情包".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, EmojiDetailsActivity.class));
                } else if ("文件".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, FileDetailsActivity.class));
                } else if ("聊天语音".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, VoiceDetailsActivity.class));
                } else if ("聊天小视频".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, VideoDetailsActivity.class));
                }
            }
        });


        imageViewEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ("聊天图片".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, ImageDetailsActivity.class));
                } else if ("聊天表情包".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, EmojiDetailsActivity.class));
                } else if ("文件".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, FileDetailsActivity.class));
                } else if ("聊天语音".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, VoiceDetailsActivity.class));
                } else if ("聊天小视频".equals(textViewName.getText().toString())) {
                    context.startActivity(new Intent(context, VideoDetailsActivity.class));
                }
            }
        });
    }
}
