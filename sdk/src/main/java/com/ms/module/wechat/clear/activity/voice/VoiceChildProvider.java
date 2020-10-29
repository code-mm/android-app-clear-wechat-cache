package com.ms.module.wechat.clear.activity.voice;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.activity.file.FileChildNode;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;
import com.ms.module.wechat.clear.utils.ToastUtils;


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
        ImageView imageViewCheck = baseViewHolder.findView(R.id.imageViewCheck);
        if (baseNode instanceof FileChildNode) {
            FileChildNode voiceChildNode = (FileChildNode) baseNode;
            File file = new File(voiceChildNode.getPath());
            textViewName.setText(file.getName());
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(file.length()));
            imageViewCheck.setSelected(voiceChildNode.isCheck());


            imageViewCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (voiceChildNode.isCheck()) {
                        imageViewCheck.setSelected(false);
                        voiceChildNode.setCheck(false);
                    } else {
                        imageViewCheck.setSelected(true);
                        voiceChildNode.setCheck(true);
                    }

                    getAdapter().notifyDataSetChanged();

                    if (VoiceDetailsActivity.getInstance() != null) {
                        VoiceDetailsActivity.getInstance().updateSelectAll();
                    }
                }
            });


            baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 播放音频文件
                    ToastUtils.show(context, "微信加密限制，暂不支持播放");
                }
            });
        }
    }
}
