package com.ms.module.wechat.clear.activity.file;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FileChildNode  extends BaseNode {


    private String path;
    private boolean check;
    private long size;


    public FileChildNode(String path, boolean check, long size) {
        this.path = path;
        this.check = check;
        this.size = size;
    }


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
