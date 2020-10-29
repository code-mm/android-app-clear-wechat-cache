package com.ms.module.wechat.clear.ui.activity.wechat.video;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VideoAdapter extends BaseNodeAdapter {


    public VideoAdapter() {

        super();
        addFullSpanNodeProvider(new VideoHeaderProvider());
        addNodeProvider(new VideoChildProvider());

    }


    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {


        BaseNode baseNode = list.get(i);

        if (baseNode instanceof VideoHeaderNode) {
            return 1;
        } else if (baseNode instanceof VideoChildNode) {
            return 2;
        }
        return 0;
    }
}
