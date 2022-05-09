package com.example.hvorerduven;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Hovedsiden extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedsiden);

        ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_alarm, "linje 1", "linje 2"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "linje 1.1", "linje 2.2"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline, "linje 1.2", "linje 2.2"));
    }
}