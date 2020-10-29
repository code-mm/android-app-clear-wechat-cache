package com.ms.module.wechat.clear.ui.activity.wechat.clear;

import android.widget.TextView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;


import org.jetbrains.annotations.NotNull;

public class DownGroupProvider extends BaseNodeProvider {

    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_wechat_clear_down_group;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {
        TextView textViewSize = baseViewHolder.findView(R.id.textViewSize);
        if (baseNode instanceof DownGroupNode) {
            DownGroupNode downGroupNode = (DownGroupNode) baseNode;
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(downGroupNode.getSize()));
        }
    }
}
