package com.ms.module.wechat.clear.activity.file;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FileAdapter extends BaseNodeAdapter {

    public FileAdapter() {
        super();
        addFullSpanNodeProvider(new FileHeaderProvider());
        addNodeProvider(new FileChildProvider());
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


    public static final int EXPAND_COLLAPSE_PAYLOAD = 0xff;
}
