package com.ms.app.ui.activity.wechat.ing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ms.app.R;


import java.util.List;

public class WeChatScanIngRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<WeChatScanIngRecyclerViewAdapterItem> datas;


    public static class WeChatScanIngRecyclerViewAdapterItem {
        public String name;
        public int icon;
        // 0 默认状态
        // 1 扫描中
        // 2 扫描完成
        public SCAN_STATUS status = SCAN_STATUS.DEFAULT;

        public WeChatScanIngRecyclerViewAdapterItem(String name, int icon) {
            this.name = name;
            this.icon = icon;
        }
    }

    public static enum SCAN_STATUS {
        DEFAULT,
        ING,
        FINISH
    }


    public WeChatScanIngRecyclerViewAdapter(Context context, List<WeChatScanIngRecyclerViewAdapterItem> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_wechat_clear_scan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.textViewName.setText(datas.get(position).name);
        Glide.with(context).load(datas.get(position).icon).into(viewHolder.imageViewLogo);

        if (datas.get(position).status == SCAN_STATUS.DEFAULT) {
            Glide.with(context).load(R.drawable.image_no_start).into(viewHolder.imageViewStatus);

        } else if (datas.get(position).status == SCAN_STATUS.ING) {
            Glide.with(context).load(R.drawable.image_loading)
                    .into(viewHolder.imageViewStatus);

            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_loading_ing);
            viewHolder.imageViewStatus.startAnimation(animation);

        } else if (datas.get(position).status == SCAN_STATUS.FINISH) {
            Glide.with(context).load(R.drawable.image_finish).into(viewHolder.imageViewStatus);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private ImageView imageViewLogo;
        private ImageView imageViewStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            imageViewLogo = itemView.findViewById(R.id.imageViewLogo);
            imageViewStatus = itemView.findViewById(R.id.imageViewStatus);
        }
    }
}