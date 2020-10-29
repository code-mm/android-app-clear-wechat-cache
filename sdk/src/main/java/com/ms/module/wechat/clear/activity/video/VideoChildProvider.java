package com.ms.module.wechat.clear.activity.video;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.activity.file.FileChildNode;


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

        ImageView imageViewCheck = baseViewHolder.findView(R.id.imageViewCheck);


        if (baseNode instanceof FileChildNode) {

            FileChildNode videoChildNode = (FileChildNode) baseNode;

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Glide.with(context)
                    .setDefaultRequestOptions(
                            new RequestOptions()
                                    .frame(5000000)
                                    .centerCrop()

                    )
                    .load(new File(videoChildNode.getPath()))
                    .into(imageView);

            imageViewCheck.setSelected(videoChildNode.isCheck());


            imageViewCheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageViewCheck.isSelected()) {
                        videoChildNode.setCheck(false);
                        imageViewCheck.setSelected(false);
                        VideoDetailsActivity.getInstance().updateSelectAll();
                    } else {
                        videoChildNode.setCheck(true);
                        imageViewCheck.setSelected(false);
                        VideoDetailsActivity.getInstance().updateSelectAll();
                    }
                }
            });


            baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    Uri uri = Uri.parse("file://" + videoChildNode.getPath());
//                    intent.setDataAndType(uri, "video/*");
//                    context.startActivity(intent);
                }
            });
        }
    }
}
