package com.ms.module.wechat.clear.ui.activity.wechat.image;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ImageChildNode  extends BaseNode {

    private String path;
    private boolean check;


    public String getPath() {
        return path;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ImageChildNode(String path, boolean check) {
        this.path = path;
        this.check = check;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
