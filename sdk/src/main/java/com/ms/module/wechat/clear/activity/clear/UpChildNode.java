package com.ms.module.wechat.clear.activity.clear;

import com.chad.library.adapter.base.entity.node.BaseExpandNode;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UpChildNode extends BaseExpandNode {

    private String name;
    private int icon;
    private long size;
    private boolean check;

    private List<String> paths;



    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public long getSize() {
        return size;
    }

    public boolean isCheck() {
        return check;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public UpChildNode(String name, int icon, long size, boolean check, List<String> paths) {
        this.name = name;
        this.icon = icon;
        this.size = size;
        this.check = check;
        this.paths = paths;
    }

    @Nullable
    @Override
    public List<BaseNode> getChildNode() {
        return null;
    }

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
    }
}
