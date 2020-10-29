package com.ms.module.wechat.clear.ui.activity.wechat.clear;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter extends BaseNodeAdapter {
    public Adapter() {
        super();

        addNodeProvider(new UpGroupProvider());
        addNodeProvider(new UpChildProvider());
        addNodeProvider(new DownGroupProvider());
        addNodeProvider(new DownChildProvider());
    }


    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {
        BaseNode baseNode = list.get(i);
        if (baseNode instanceof UpGroupNode) {
            return 1;
        } else if (baseNode instanceof DownGroupNode) {
            return 2;
        } else if (baseNode instanceof UpChildNode) {
            return 3;
        } else if (baseNode instanceof DownChildNode) {

            return 4;
        }
        return -1;
    }
}
