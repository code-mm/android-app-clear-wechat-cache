package com.ms.module.wechat.clear.ui.activity.wechat.voice;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;


import org.jetbrains.annotations.NotNull;

import java.io.File;

public class VoiceChildProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_details_voice_child;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {

        TextView textViewName = baseViewHolder.findView(R.id.textViewName);
        TextView textViewSize = baseViewHolder.findView(R.id.textViewSize);
        CheckBox checkBoxSelect = baseViewHolder.findView(R.id.checkBoxSelect);
        if (baseNode instanceof VoiceChildNode) {
            VoiceChildNode voiceChildNode = (VoiceChildNode) baseNode;
            File file = new File(voiceChildNode.getPath());
            textViewName.setText(file.getName());
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(file.length()));
            checkBoxSelect.setChecked(voiceChildNode.isCheck());
            baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 播放音频文件
                }
            });
        }
    }
}
