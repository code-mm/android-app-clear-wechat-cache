package com.ms.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

public class ContextRecyclerViewAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> datas;

    private String tag;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public ContextRecyclerViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        if (datas == null || datas.size() == 0) {

            viewHolder.textViewFileName.setText("没有文件");

        } else {
            File file = new File(datas.get(position));
            viewHolder.textViewFileName.setText("文件名称: " + file.getName());
            if ("EMOJI".equals(tag)
                    || "IMAGE".equals(tag)
                    || "PNG".equals(tag)
                    || "JPEG".equals(tag)
            ) {
                Glide.with(context).load(file).into(viewHolder.imageViewLogo);
            }


        }
    }

    @Override
    public int getItemCount() {

        if (datas == null || datas.size() == 0) {
            return 1;
        }

        return datas.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {


        private ImageView imageViewLogo;
        private TextView textViewFileName;
        private TextView textViewFileType;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewLogo = itemView.findViewById(R.id.imageViewLogo);
            textViewFileName = itemView.findViewById(R.id.textViewFileName);
        }
    }

}
