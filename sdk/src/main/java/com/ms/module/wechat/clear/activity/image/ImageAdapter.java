package com.ms.module.wechat.clear.activity.image;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.ms.module.wechat.clear.activity.file.FileChildNode;
import com.ms.module.wechat.clear.activity.file.FileHeaderNode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageAdapter extends BaseNodeAdapter {

    public ImageAdapter() {
        super();
        addFullSpanNodeProvider(new ImageHeaderProvider());
        addNodeProvider(new ImageChildProvider());
    }

    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {

        BaseNode baseNode = list.get(i);

        if (baseNode instanceof FileHeaderNode) {
            return 1;
        } else if (baseNode instanceof FileChildNode) {
            return 2;
        }

        return 0;
    }
}
