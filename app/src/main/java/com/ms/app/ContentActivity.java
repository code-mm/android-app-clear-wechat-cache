package com.ms.app;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ContentActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ContentActivityViewModel contentActivityViewModel;
    private ContextRecyclerViewAdapter contextRecyclerViewAdapter;
    private List<String> datas = new ArrayList<>();


    private static final String TAG = "ContentActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content);
        recyclerView = findViewById(R.id.recyclerView);

        contentActivityViewModel = new ViewModelProvider(this).get(ContentActivityViewModel.class);

        contextRecyclerViewAdapter = new ContextRecyclerViewAdapter(ContentActivity.this, datas);
        recyclerView.setLayoutManager(new LinearLayoutManager(ContentActivity.this));
        recyclerView.setAdapter(contextRecyclerViewAdapter);


        contentActivityViewModel.fileSearchLiveData.observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> list) {

                Log.e(TAG, "onChanged: " + list);
                if (list != null) {
                    datas.clear();
                    datas.addAll(list);

                    contextRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });

        String type = getIntent().getStringExtra("type");
        contextRecyclerViewAdapter.setTag(type);
        contentActivityViewModel.fileSearchMutableLiveData.postValue(type);

    }
}
