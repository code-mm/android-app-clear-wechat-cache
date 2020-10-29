package com.ms.module.wechat.clear.activity.clear;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpGroupNode extends BaseExpandNode {
    private List<BaseNode> childNode;
    private long size;
    private boolean check;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public UpGroupNode(List<BaseNode> childNode, long size, boolean check) {
        this.childNode = childNode;
        this.size = size;
        this.check = check;
    }

    public UpGroupNode(List<BaseNode> datas) {
        this.childNode = datas;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
