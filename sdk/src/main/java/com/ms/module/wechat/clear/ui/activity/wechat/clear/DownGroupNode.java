package com.ms.module.wechat.clear.ui.activity.wechat.clear;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DownGroupNode extends BaseExpandNode {

    private List<BaseNode> datas;

    private long size;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public DownGroupNode(List<BaseNode> datas, long size) {
        this.datas = datas;
        this.size = size;
    }

    public DownGroupNode(List<BaseNode> datas) {
        this.datas = datas;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return datas;
    }
}
