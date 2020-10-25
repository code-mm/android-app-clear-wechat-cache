package com.ms.app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

public class ExActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ExpandableItemAdapter adapter;

    private List<MultiItemEntity> datas = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex);
        recyclerView = findViewById(R.id.recyclerView);


        datas.add(new ExItem());
        datas.add(new ExItem());
        datas.add(new ExItem());
        datas.add(new ExItem());
        datas.add(new ExItem());

        adapter = new ExpandableItemAdapter(datas);

        recyclerView.setAdapter(adapter);
        final GridLayoutManager manager = new GridLayoutManager(this, 1);

        recyclerView.setLayoutManager(manager);

    }
}
