package com.example.hvorerduven;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Hovedsiden extends AppCompatActivity {

    private ArrayList<Card> mCardList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cardRef = database.getReference("Card");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedsiden);

        lavCardList();
        buildRecyclerView();

        buttonInsert = findViewById(R.id.button_insert);
        buttonRemove = findViewById(R.id.button_remove);
        editTextInsert = findViewById(R.id.edittext_insert);
        editTextRemove = findViewById(R.id.edittext_remove);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });
    }

    public void insertItem(int position) {
        Card nytKort = new Card("nyt kort navn");
        nytKort.setCardID(5);
        addCardToDB(nytKort);
        mCardList.add(position, nytKort );
        mAdapter.notifyItemInserted(position);
    }

    public void addCardToDB(Card nytKort) { //fremgangsmåde herfra: https://stackoverflow.com/questions/37031222/firebase-add-new-child-with-specified-name
        Map<String, String> cardData = new HashMap<>();
        cardData.put("CardName", nytKort.getCardName());
        cardData.put("roomID", "hellow room");

        cardRef = cardRef.child(String.valueOf(nytKort.cardID));
        cardRef.setValue(cardData);

    }

    public void removeItem(int position) {
        mCardList.remove(position-1);
        mAdapter.notifyItemRemoved(position);

    }

    public void lavCardList() {
        mCardList = new ArrayList<>();
        mCardList.add(new Card("Camp området"));
        mCardList.add(new Card("main stage"));
        mCardList.add(new Card("baren"));


        
    }

    public void buildRecyclerView() {
        //listen bliver givet til vores adapter, som giver den til vores viewholder
        mRecyclerView = findViewById(R.id.recyclerView);
        //mRecyclerView.setHasFixedSize(true); //hvis vi ved at listen ikke ændrer størrelse, kan denne linje optimere performance
        mLayoutManager = new LinearLayoutManager(this);
        //mAdapter = new CardAdapter(exampleList);
        mAdapter = new CardAdapter(mCardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

}