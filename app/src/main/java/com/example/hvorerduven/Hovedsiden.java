package com.example.hvorerduven;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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

        /*ArrayList<ExampleItem> exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_alarm, "linje 1", "linje 2"));
        exampleList.add(new ExampleItem(R.drawable.ic_android, "linje 1.1", "linje 2.1"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline, "linje 1.2", "linje 2.2"));

         */


        ArrayList<Card> cardList = new ArrayList<>();
        cardList.add(new Card("Camp området"));
        cardList.add(new Card("main stage"));
        cardList.add(new Card("baren"));


        //listen bliver givet til vores adapter, som giver den til vores viewholder
        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setHasFixedSize(true); //hvis vi ved at listen ikke ændrer størrelse, kan denne linje optimere performance
        mLayoutManager = new LinearLayoutManager(this);
        //mAdapter = new CardAdapter(exampleList);
        mAdapter = new CardAdapter(cardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}