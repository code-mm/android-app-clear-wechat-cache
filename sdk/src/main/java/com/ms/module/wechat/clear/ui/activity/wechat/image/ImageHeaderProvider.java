package com.ms.module.wechat.clear.ui.activity.wechat.image;

import android.view.View;
import android.view.animation.DecelerateInterpolator;
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


        if (baseNode instanceof ImageHeaderNode) {
            ImageHeaderNode imageHeaderNode = (ImageHeaderNode) baseNode;

            textViewDate.setText(imageHeaderNode.getDate());
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(imageHeaderNode.getSize()));
            textViewCount.setText(imageHeaderNode.getCount() + "项");

            if (imageHeaderNode.isExpanded()) {

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

            List<BaseNode> childNode = imageHeaderNode.getChildNode();

            imageViewCheck.setSelected(imageHeaderNode.isCheck());

            imageViewCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (imageHeaderNode.isCheck()) {
                        for (int i = 0; i < childNode.size(); i++) {
                            BaseNode baseNode1 = childNode.get(i);
                            if (baseNode1 instanceof ImageChildNode) {
                                ImageChildNode imageChildNode = (ImageChildNode) baseNode1;
                                imageChildNode.setCheck(false);
                            }
                        }

                        imageHeaderNode.setCheck(false);

                    } else {
                        for (int i = 0; i < childNode.size(); i++) {
                            BaseNode baseNode1 = childNode.get(i);
                            if (baseNode1 instanceof ImageChildNode) {
                                ImageChildNode imageChildNode = (ImageChildNode) baseNode1;
                                imageChildNode.setCheck(true);
                            }
                        }

                        imageHeaderNode.setCheck(true);
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
