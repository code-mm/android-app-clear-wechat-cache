package com.ms.module.wechat.clear.ui.activity.wechat.video;

import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;


import org.jetbrains.annotations.NotNull;

import java.io.File;

public class VideoChildProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_details_video_child;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {


        ImageView imageView = baseViewHolder.findView(R.id.imageView);

        CheckBox checkBoxSelect = baseViewHolder.findView(R.id.checkBoxSelect);


        if (baseNode instanceof VideoChildNode) {

            VideoChildNode videoChildNode = (VideoChildNode) baseNode;

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context)
                    .setDefaultRequestOptions(
                            new RequestOptions()
                                    .frame(4000000)
                                    .centerCrop()

                    )
                    .load(new File(videoChildNode.getPath()))
                    .into(imageView);

            checkBoxSelect.setChecked(videoChildNode.isCheck());
        }
    }
}
