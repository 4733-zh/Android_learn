package com.example.basequickadaptertest;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter4.BaseQuickAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BaseQuickAdapter.OnItemChildClickListener<TestData> {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mTestAdapter;
    private ArrayList<TestData> mTestData;

    @Override
    protected void onCreate(Bundle   savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1, GridLayoutManager.VERTICAL, false));
        mTestData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            TestData testData = new TestData();
            testData.setData01("setData01：" + i);
            testData.setData02("setData02：" + i);
            mTestData.add(testData);
        }

        mTestAdapter = new RecyclerViewAdapter();
        mTestAdapter.addAll(mTestData);
        mRecyclerView.setAdapter(mTestAdapter);

        mTestAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener<TestData>() {
            @Override
            public void onClick(@NonNull BaseQuickAdapter<TestData, ?> baseQuickAdapter, @NonNull View view, int i) {
                Toast("被点击的数据：" + mTestData.get(i).data01);
            }
        });

        mTestAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener<TestData>() {
            @Override
            public boolean onLongClick(@NonNull BaseQuickAdapter<TestData, ?> baseQuickAdapter, @NonNull View view, int i) {
                Toast("被长按的数据：" + mTestData.get(i).data01);
                return false;
            }
        });

        mTestAdapter.addOnItemChildClickListener(R.id.ItemChildView,this);

    }

    private void Toast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(@NonNull BaseQuickAdapter<TestData, ?> baseQuickAdapter, @NonNull View view, int i) {
        if (view.getId() == R.id.ItemChildView) {
            Toast("被点击的子控件：" + mTestData.get(i).data01);
        }
    }
}