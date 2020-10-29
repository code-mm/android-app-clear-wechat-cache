package com.ms.module.wechat.clear.activity.clear;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;


import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UpGroupProvider extends BaseNodeProvider {


    private static final String TAG = "UpGroupProvider";

    @Override
    public int getItemViewType() {
        return 1;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_wechat_clear_up_group;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {

        TextView textViewSize = baseViewHolder.findView(R.id.textViewSize);
        ImageView imageViewStatus = baseViewHolder.findView(R.id.imageViewStatus);
        ImageView imageViewCheck = baseViewHolder.findView(R.id.imageViewCheck);


        if (baseNode instanceof UpGroupNode) {
            UpGroupNode upGroupNode = (UpGroupNode) baseNode;
            if (upGroupNode.isExpanded()) {
                Glide.with(context).load(R.drawable.image_down_gray).into(imageViewStatus);
            } else {
                Glide.with(context).load(R.drawable.image_right_gray).into(imageViewStatus);
            }
            List<BaseNode> childNode = baseNode.getChildNode();

            boolean check = true;
            for (int i = 0; i < childNode.size(); i++) {
                BaseNode baseNode1 = childNode.get(i);
                if (baseNode1 instanceof UpChildNode) {
                    UpChildNode upChildNode = (UpChildNode) baseNode1;
                    if (!upChildNode.isCheck()) {
                        check = false;
                    }
                }
            }
            imageViewCheck.setSelected(check);
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(upGroupNode.getSize()));
            imageViewCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (upGroupNode.isCheck()) {
                        for (int i = 0; i < childNode.size(); i++) {
                            BaseNode baseNode1 = childNode.get(i);
                            if (baseNode1 instanceof UpChildNode) {
                                UpChildNode upChildNode = (UpChildNode) baseNode1;
                                upChildNode.setCheck(false);
                            }
                        }
                        upGroupNode.setCheck(false);

                    } else {
                        for (int i = 0; i < childNode.size(); i++) {
                            BaseNode baseNode1 = childNode.get(i);
                            if (baseNode1 instanceof UpChildNode) {
                                UpChildNode upChildNode = (UpChildNode) baseNode1;
                                upChildNode.setCheck(true);
                            }
                        }
                        upGroupNode.setCheck(true);
                    }
                    getAdapter().notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onClick(@NotNull BaseViewHolder helper, @NotNull View view, BaseNode data, int position) {
        // 这里使用payload进行增量刷新（避免整个item刷新导致的闪烁，不自然）
        getAdapter().expandOrCollapse(position, true, true, 110);
        getAdapter().notifyDataSetChanged();
    }
}
