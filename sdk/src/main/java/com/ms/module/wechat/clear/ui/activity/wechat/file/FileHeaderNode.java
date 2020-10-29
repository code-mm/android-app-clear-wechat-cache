package com.ms.module.wechat.clear.ui.activity.wechat.file;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.entity.node.NodeFooterImp;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FileHeaderNode extends BaseExpandNode implements NodeFooterImp {


    // 时间
    private String date;

    private long size;

    private int count;

    private boolean check;

    private List<BaseNode> childNode;


    public FileHeaderNode(String date, long size, int count, boolean check, List<BaseNode> childNode) {
        this.date = date;
        this.size = size;
        this.count = count;
        this.check = check;
        this.childNode = childNode;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setChildNode(List<BaseNode> childNode) {
        this.childNode = childNode;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return childNode;
    }

    @Nullable
    @Override
    public BaseNode getFooterNode() {
        return null;
    }
}
