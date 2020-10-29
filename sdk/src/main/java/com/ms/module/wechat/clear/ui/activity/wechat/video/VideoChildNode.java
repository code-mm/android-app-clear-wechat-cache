package com.ms.module.wechat.clear.ui.activity.wechat.video;

import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VideoChildNode extends BaseNode {


    private String path;
    private  boolean check;


    public VideoChildNode(String path, boolean check) {
        this.path = path;
        this.check = check;
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

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }
}
