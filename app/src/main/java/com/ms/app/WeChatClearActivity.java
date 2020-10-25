package com.ms.app;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 清理
 */
public class WeChatClearActivity extends AppCompatActivity {

    private static final String TAG = "WeChatClearActivity";

    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wechat_clear);
        expandableListView = findViewById(R.id.expandableListView);

        List<GroupBean> datas = new ArrayList<>();

        GroupBean groupBeanAuto = new GroupBean();
        groupBeanAuto.name = "放心清理";
        groupBeanAuto.datas = new ArrayList<>();
        groupBeanAuto.datas.add(new ChildBean("垃圾文件", R.drawable.image_rubbish, 0));
        groupBeanAuto.datas.add(new ChildBean("缓冲文件", R.drawable.image_cache, 0));
        groupBeanAuto.datas.add(new ChildBean("朋友圈缓冲", R.drawable.image_firend, 0));
        datas.add(groupBeanAuto);


        GroupBean groupBeanManual = new GroupBean();
        groupBeanManual.name = "放心清理";
        groupBeanManual.datas = new ArrayList<>();
        groupBeanManual.datas.add(new ChildBean("聊天小视频", R.drawable.image_video, 0));
        groupBeanManual.datas.add(new ChildBean("聊天图片", R.drawable.image_image, 0));
        groupBeanManual.datas.add(new ChildBean("聊天语音", R.drawable.image_voice, 0));
        groupBeanManual.datas.add(new ChildBean("文件", R.drawable.image_file, 0));
        groupBeanManual.datas.add(new ChildBean("聊天表情包", R.drawable.image_emoji, 0));
        datas.add(groupBeanManual);

        expandableListView.setAdapter(new AddressAdapter(this, datas));
        expandableListView.expandGroup(0);
        expandableListView.expandGroup(1);
    }

    static class GroupBean {
        public String name;
        public long size;
        public List<ChildBean> datas;
    }

    static class ChildBean {
        public String name;
        public int icon;
        public long size;
        public boolean select = true;

        public ChildBean(String name, int icon, long size) {
            this.name = name;
            this.icon = icon;
            this.size = size;
        }
    }


    static class AddressAdapter extends BaseExpandableListAdapter {

        private Context context;

        List<GroupBean> datas;

        //用来记录所有checkbox的状态
        private Map<Integer, Boolean> checkStatus = new HashMap<>();


        public AddressAdapter(Context context, List<GroupBean> datas) {
            this.context = context;
            this.datas = datas;


        }

        @Override
        public int getGroupCount() {
            return datas.size();
        }

        @Override
        public int getChildrenCount(int i) {
            return datas.get(i).datas.size();
        }

        @Override
        public Object getGroup(int i) {
            return null;
        }

        @Override
        public Object getChild(int i, int i1) {
            return null;
        }

        @Override
        public long getGroupId(int i) {
            return 0;
        }

        @Override
        public long getChildId(int i, int i1) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {

            GroupViewHolder groupViewHolder = null;

            if (view == null) {
                groupViewHolder = new GroupViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.adapter_wechat_clear_group, viewGroup, false);


                groupViewHolder.checkBoxSelectAll = view.findViewById(R.id.checkBoxSelectAll);
                groupViewHolder.textViewName = view.findViewById(R.id.textViewName);
                groupViewHolder.textViewSize = view.findViewById(R.id.textViewSize);
                groupViewHolder.imageViewLogo = view.findViewById(R.id.imageViewLogo);
                groupViewHolder.textViewHint = view.findViewById(R.id.textViewHint);
                groupViewHolder.imageViewDown = view.findViewById(R.id.imageViewDown);

                view.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) view.getTag();
            }

            if (i == 0) {
                groupViewHolder.textViewHint.setVisibility(View.VISIBLE);
            } else {
                groupViewHolder.textViewHint.setVisibility(View.GONE);
                groupViewHolder.checkBoxSelectAll.setVisibility(View.GONE);
                groupViewHolder.imageViewDown.setVisibility(View.GONE);
                groupViewHolder.textViewSize.setVisibility(View.GONE);
            }


            groupViewHolder.imageViewDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });


            groupViewHolder.checkBoxSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        List<ChildBean> datas = AddressAdapter.this.datas.get(i).datas;
                        for (int i = 0; i < datas.size(); i++) {
                            datas.get(i).select = true;
                        }
                        notifyDataSetChanged();

                    } else {
                        List<ChildBean> datas = AddressAdapter.this.datas.get(i).datas;

                        for (int i = 0; i < datas.size(); i++) {
                            datas.get(i).select = false;
                        }

                        notifyDataSetChanged();
                    }
                }
            });
            return view;
        }

        @Override
        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
            ChildViewHolder childViewHolder = null;
            if (view == null) {
                childViewHolder = new ChildViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.adapter_wechat_clear_clild, viewGroup, false);

                childViewHolder.imageViewLogo = view.findViewById(R.id.imageViewLogo);
                childViewHolder.checkBoxSelect = view.findViewById(R.id.checkBoxSelect);
                childViewHolder.textViewName = view.findViewById(R.id.textViewName);
                childViewHolder.textViewSize = view.findViewById(R.id.textViewSize);
                childViewHolder.imageViewEnter = view.findViewById(R.id.imageViewEnter);
                view.setTag(childViewHolder);

            } else {
                childViewHolder = (ChildViewHolder) view.getTag();
            }

            Glide.with(context).load(datas.get(i).datas.get(i1).icon).into(childViewHolder.imageViewLogo);

            childViewHolder.textViewName.setText(datas.get(i).datas.get(i1).name);

            childViewHolder.checkBoxSelect.setChecked(datas.get(i).datas.get(i1).select);

            if (i == 0) {
                childViewHolder.checkBoxSelect.setVisibility(View.VISIBLE);
                childViewHolder.imageViewEnter.setVisibility(View.GONE);
            } else {
                childViewHolder.imageViewEnter.setVisibility(View.VISIBLE);
                childViewHolder.checkBoxSelect.setVisibility(View.INVISIBLE);
            }
            return view;
        }

        @Override
        public boolean isChildSelectable(int i, int i1) {
            return false;
        }
    }

    static class GroupViewHolder {
        private ImageView imageViewLogo;
        private TextView textViewName;
        private TextView textViewSize;
        private CheckBox checkBoxSelectAll;
        private ImageView imageViewDown;
        private TextView textViewHint;
    }

    static class ChildViewHolder {
        private ImageView imageViewLogo;
        private TextView textViewName;
        private TextView textViewSize;
        private CheckBox checkBoxSelect;
        private ImageView imageViewEnter;
    }
}
