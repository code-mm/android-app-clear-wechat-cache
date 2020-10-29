package com.ms.module.wechat.clear.ui.activity.wechat.voice;

import com.chad.library.adapter.base.BaseNodeAdapter;
import com.chad.library.adapter.base.entity.node.BaseNode;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VoiceAdapter extends BaseNodeAdapter {


    public VoiceAdapter() {
        super();
        addFullSpanNodeProvider(new VoiceHeaderProvider());
        addNodeProvider(new VoiceChildProvider());
    }


    @Override
    protected int getItemType(@NotNull List<? extends BaseNode> list, int i) {

        BaseNode baseNode = list.get(i);

        if (baseNode instanceof VoiceHeaderNode) {
            return 1;
        } else if (baseNode instanceof VoiceChildNode) {
            return 2;
        }
        return 0;
    }
}
