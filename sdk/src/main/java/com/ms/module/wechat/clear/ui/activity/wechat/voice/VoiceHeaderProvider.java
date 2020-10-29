package com.ms.module.wechat.clear.ui.activity.wechat.voice;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VoiceHeaderProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_details_voice_header;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {


        TextView textViewDate = baseViewHolder.findView(R.id.textViewDate);
        ImageView imageViewStatus = baseViewHolder.findView(R.id.imageViewStatus);
        TextView textViewSize = baseViewHolder.findView(R.id.textViewSize);
        TextView textViewCount = baseViewHolder.findView(R.id.textViewCount);
        ImageView imageViewCheck = baseViewHolder.findView(R.id.imageViewCheck);


        if (baseNode instanceof VoiceHeaderNode) {
            VoiceHeaderNode voiceHeaderNode = (VoiceHeaderNode) baseNode;

            textViewDate.setText(voiceHeaderNode.getDate());
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(voiceHeaderNode.getSize()));
            textViewCount.setText(voiceHeaderNode.getCount() + "项");
            imageViewCheck.setSelected(voiceHeaderNode.isCheck());

            if (voiceHeaderNode.isExpanded()) {
                ViewCompat.animate(imageViewStatus).setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .rotation(0f)
                        .start();
            } else {
                ViewCompat.animate(imageViewStatus).setDuration(200)
                        .setInterpolator(new DecelerateInterpolator())
                        .rotation(-90f)
                        .start();
            }

            imageViewCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List<BaseNode> childNode = voiceHeaderNode.getChildNode();
                    if (voiceHeaderNode.isCheck()) {

                        for (int i = 0; i < childNode.size(); i++) {
                            BaseNode baseNode1 = childNode.get(i);

                            if (baseNode1 instanceof VoiceChildNode) {
                                VoiceChildNode voiceChildNode = (VoiceChildNode) baseNode1;
                                voiceChildNode.setCheck(false);
                            }
                        }
                        voiceHeaderNode.setCheck(false);

                    } else {

                        for (int i = 0; i < childNode.size(); i++) {
                            BaseNode baseNode1 = childNode.get(i);

                            if (baseNode1 instanceof VoiceChildNode) {
                                VoiceChildNode voiceChildNode = (VoiceChildNode) baseNode1;
                                voiceChildNode.setCheck(true);
                            }
                        }
                        voiceHeaderNode.setCheck(true);
                    }

                    getAdapter().notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        getAdapter().expandOrCollapse(position);
        getAdapter().notifyDataSetChanged();
    }
}
