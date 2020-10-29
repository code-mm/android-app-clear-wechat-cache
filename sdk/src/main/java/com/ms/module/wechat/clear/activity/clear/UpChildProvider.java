package com.ms.module.wechat.clear.activity.clear;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;


import org.jetbrains.annotations.NotNull;

public class UpChildProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 3;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_wechat_clear_up_child;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {


        ImageView imageViewIcon = baseViewHolder.findView(R.id.imageViewIcon);
        TextView textViewName = baseViewHolder.findView(R.id.textViewName);
        TextView textViewSize = baseViewHolder.findView(R.id.textViewSize);

        ImageView imageViewCheck = baseViewHolder.findView(R.id.imageViewCheck);


        if (baseNode instanceof UpChildNode) {

            UpChildNode upChildNode = (UpChildNode) baseNode;
            textViewName.setText(upChildNode.getName());
            Glide.with(getContext()).load(upChildNode.getIcon()).into(imageViewIcon);
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(upChildNode.getSize()));
            imageViewCheck.setSelected(upChildNode.isCheck());

            imageViewCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (upChildNode.isCheck()) {
                        upChildNode.setCheck(false);
                        imageViewCheck.setSelected(false);

                    } else {
                        upChildNode.setCheck(true);
                        imageViewCheck.setSelected(true);
                    }

                    getAdapter().notifyDataSetChanged();
                }
            });
        }
    }
}
