package com.ms.module.wechat.clear.activity.image;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.activity.emoji.EmojiDetailsActivity;
import com.ms.module.wechat.clear.activity.file.FileAdapter;
import com.ms.module.wechat.clear.activity.file.FileChildNode;
import com.ms.module.wechat.clear.activity.file.FileHeaderNode;
import com.ms.module.wechat.clear.activity.voice.VoiceDetailsActivity;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;
import com.ms.module.wechat.clear.utils.ListDataUtils;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageHeaderProvider extends BaseNodeProvider {

    private static final String TAG = "ImageHeaderProvider";

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_details_image_header;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {

        TextView textViewDate = baseViewHolder.findView(R.id.textViewDate);
        ImageView imageViewStatus = baseViewHolder.findView(R.id.imageViewStatus);
        TextView textViewSize = baseViewHolder.findView(R.id.textViewSize);
        TextView textViewCount = baseViewHolder.findView(R.id.textViewCount);

        ImageView imageViewCheck = baseViewHolder.findView(R.id.imageViewCheck);


        if (baseNode instanceof FileHeaderNode) {
            FileHeaderNode headerNode = (FileHeaderNode) baseNode;

            textViewDate.setText(headerNode.getDate());
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(headerNode.getSize()));
            textViewCount.setText(headerNode.getCount() + "项");

            if (headerNode.isExpanded()) {
                Glide.with(context).load(R.drawable.image_down_gray).into(imageViewStatus);
            } else {
                Glide.with(context).load(R.drawable.image_right_gray).into(imageViewStatus);
            }

            List<BaseNode> childNode = headerNode.getChildNode();
            imageViewCheck.setSelected(ListDataUtils.checkFileChildNode(childNode));

            imageViewCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    List<BaseNode> childNode = headerNode.getChildNode();
                    if (headerNode.isCheck()) {
                        ListDataUtils.setCheck(childNode, false);
                        headerNode.setCheck(false);
                    } else {
                        ListDataUtils.setCheck(childNode, true);
                        headerNode.setCheck(true);
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
        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        getAdapter().expandOrCollapse(position, true, true, FileAdapter.EXPAND_COLLAPSE_PAYLOAD);
        getAdapter().notifyDataSetChanged();
    }
}
