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

    int nytCardID;

    private ArrayList<Card> mCardList;
    private RecyclerView mRecyclerView;
    private CardAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button buttonInsert;
    private EditText editTextInsert;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cardRef = database.getReference("Card");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedsiden);

        lavCardList();
        buildRecyclerView();
        setButtons(); //indsætter vores knapper

        //find det højeste cardID i databasen, og gør dette korts ID 1 højere
        final DatabaseReference cardRef = database.getReference("Card");
        cardRef.orderByKey().limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot cardSnapshot : dataSnapshot.getChildren()) { //for hver bruger
                    if (cardSnapshot.getKey() != null) {
                        nytCardID = Integer.parseInt(cardSnapshot.getKey())+1;
                    } else {
                        System.out.println("der gik noget galt i 'cardRef' i card.");
                        break;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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
        Card nytKort = new Card(cardName, nytCardID);
        addCardToDB(nytKort);
        mCardList.add(0, nytKort );
        mAdapter.notifyItemInserted(0);

    }

    public void addCardToDB(Card nytKort) { //fremgangsmåde herfra: https://stackoverflow.com/questions/37031222/firebase-add-new-child-with-specified-name
        Map<String, String> cardData = new HashMap<>();
        cardData.put("CardName", nytKort.getCardName());
        String midlertidigtRoomID = "roomID test";
        cardData.put("roomID", midlertidigtRoomID);

        cardRef = cardRef.child(String.valueOf(nytCardID)); //værdien her er hvad kortet kommer til at hedde, (eller hvad dens rens key er)
        cardRef.setValue(cardData);
        cardRef = database.getReference("Card");
        System.out.println("-----------\nNyt kort oprettet med ID: " + nytCardID + "\nKort navn: " + nytKort.getCardName() + "\nkortets roomID: " + midlertidigtRoomID + "\n-----------");
    }

    public void removeItem(int position) {
        mCardList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void changeItem(int position, String text) {
        mCardList.get(position).changeText1(text); //skifter navnet på overskriften
        mAdapter.notifyItemChanged(position);
    }

    public void lavCardList() { // skal ændres så den opretter kort der eksisterer i databasen
        mCardList = new ArrayList<>();
        mCardList.add(new Card("Camp området", 1));
        mCardList.add(new Card("main stage", 2));
        mCardList.add(new Card("baren",3));
        
    }

    public void buildRecyclerView() {
        //listen bliver givet til vores adapter, som giver den til vores viewholder
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new CardAdapter(mCardList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CardAdapter.OnItemClickListener() { //aktiverer når man klikker på et kort
            @Override
            public void onItemClick(int position) {
                changeItem(position, "Clicked");
            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position);
            }
        });
    }

    public void setButtons() {
        buttonInsert = findViewById(R.id.button_insert);
        editTextInsert = findViewById(R.id.edittext_insert);
        buttonInsert.setOnClickListener(new View.OnClickListener() { //når du klikker på indsæt knappen....
            @Override
            public void onClick(View view) {
                String cardName = editTextInsert.getText().toString();
                insertItem(cardName);

            }
        });
    }
}