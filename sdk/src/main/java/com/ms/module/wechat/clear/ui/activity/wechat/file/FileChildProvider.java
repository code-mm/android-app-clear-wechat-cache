package com.ms.module.wechat.clear.ui.activity.wechat.file;

import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.entity.node.BaseNode;
import com.chad.library.adapter.base.provider.BaseNodeProvider;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ms.module.wechat.clear.R;
import com.ms.module.wechat.clear.utils.ByteSizeToStringUnitUtils;


import org.jetbrains.annotations.NotNull;

import java.io.File;

public class FileChildProvider extends BaseNodeProvider {
    @Override
    public int getItemViewType() {
        return 2;
    }

    @Override
    public int getLayoutId() {
        return R.layout.provider_details_file_child;
    }

    @Override
    public void convert(@NotNull BaseViewHolder baseViewHolder, BaseNode baseNode) {

        ImageView iamgeViewLogo = baseViewHolder.findView(R.id.iamgeViewLogo);
        TextView textViewName = baseViewHolder.findView(R.id.textViewName);
        TextView textViewSize = baseViewHolder.findView(R.id.textViewSize);
        CheckBox checkBoxSelect = baseViewHolder.findView(R.id.checkBoxSelect);

        if (baseNode instanceof FileChildNode) {
            FileChildNode fileChildNode = (FileChildNode) baseNode;
            File file = new File(fileChildNode.getPath());
            String name = file.getName();
            long length = file.length();
            textViewName.setText(name);
            textViewSize.setText(ByteSizeToStringUnitUtils.byteToStringUnit(length));
            checkBoxSelect.setChecked(fileChildNode.isCheck());

            if (file.getName().endsWith(".txt")) {
                Glide.with(context).load(R.drawable.image_txt).into(iamgeViewLogo);
            } else if (file.getName().endsWith(".ppt")) {
                Glide.with(context).load(R.drawable.image_ppt).into(iamgeViewLogo);
            } else if (file.getName().endsWith(".pdf")) {
                Glide.with(context).load(R.drawable.image_pdf).into(iamgeViewLogo);
            } else if (file.getName().endsWith(".mp4")) {
                Glide.with(context).load(R.drawable.image_video).into(iamgeViewLogo);
            } else if (file.getName().endsWith(".mp3")) {
                Glide.with(context).load(R.drawable.image_music).into(iamgeViewLogo);
            } else if (file.getName().endsWith(".xls")) {
                Glide.with(context).load(R.drawable.image_xls).into(iamgeViewLogo);
            } else if (file.getName().endsWith(".doc") || file.getName().endsWith(".docx")) {
                Glide.with(context).load(R.drawable.image_doc).into(iamgeViewLogo);
            } else {
                Glide.with(context).load(R.drawable.image_other).into(iamgeViewLogo);
            }
        }
    }
}
