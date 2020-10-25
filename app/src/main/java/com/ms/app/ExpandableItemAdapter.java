package com.ms.app;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ExpandableItemAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {




    public ExpandableItemAdapter(@Nullable List<MultiItemEntity> data) {
        super(data);
        addItemType(0, R.layout.adapter_wechat_clear_group);
    }



    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, MultiItemEntity multiItemEntity) {




    }
}
