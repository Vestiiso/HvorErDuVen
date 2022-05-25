package com.example.hvorerduven;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
                String cardName = editTextInsert.getText().toString();
                insertItem(cardName);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });

        //Sørg for at alle cards bliver opdateret, når der bliver oprettet nye brugere
        final DatabaseReference brugerRef = database.getReference("Bruger");
        brugerRef.orderByChild("brugernavn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot brugerSnapshot : dataSnapshot.getChildren()) { //for hver bruger

                    if (brugerSnapshot.child("cardID").getValue(int.class) == null){
                        break;
                    }
                    else {
                        mAdapter.notifyDataSetChanged();

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void insertItem(String cardName) {
        Card nytKort = new Card(cardName);
        //nytKort.setCardID(nytKort.generateCardID());
        addCardToDB(nytKort);
        mCardList.add(0, nytKort );
        mAdapter.notifyItemInserted(0);
    }

    public void addCardToDB(Card nytKort) { //fremgangsmåde herfra: https://stackoverflow.com/questions/37031222/firebase-add-new-child-with-specified-name
        Map<String, String> cardData = new HashMap<>();
        cardData.put("CardName", nytKort.getCardName());
        cardData.put("roomID", "hellow room");

        System.out.println("addcard modtager: " + nytKort.generateCardID());
        cardRef = cardRef.child(String.valueOf(nytKort.generateCardID())); //værdien her er hvad kortet kommer til at hedde, (eller hvad dens rens key er)
        //System.out.println("cardref får: " + String.valueOf(nytKort.generateCardID()));
        cardRef.setValue(cardData);
        cardRef = database.getReference("Card");

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