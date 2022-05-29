package com.example.hvorerduven;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
    private ImageButton launchSettings;
    private Button buttonInsert;
    private EditText editTextInsert;
    public CardView mCardView;

    public TextView mTextview2;


    LokalBruger denneBruger = LokalBruger.getInstance();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cardRef = database.getReference("Card");
    DatabaseReference brugerRef = database.getReference("Bruger");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hovedsiden);
        launchSettings = (ImageButton) findViewById(R.id.settingsButton);

        lavCardList();
        buildRecyclerView();
        setButtons(); //indsætter vores knapper

        launchSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });

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

        //opdater vores adapter hvis der er ændringer under Card i databasen
        cardRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                mCardView = findViewById(R.id.cardViewID);

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //opdater vores adapter hvis der er ændringer under Bruger i databasen
        brugerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mAdapter.notifyDataSetChanged();

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
        mCardList.add(mCardList.size(), nytKort );
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
                mTextview2 = findViewById(R.id.textView2);

                //ændrer den indloggede brugers cardID i databasen til at være cardID'et for det kort der er klikket på
                DatabaseReference denneBrugerRef = database.getReference("Bruger/"+denneBruger.getLokalNavn()+"/cardID");
                denneBrugerRef.setValue(mCardList.get(position).getCardID());

                mAdapter.notifyDataSetChanged();

                for (Card kort : mCardList) {
                    mTextview2.setText(kort.getUserNamesAsArray().toString());

                    if (!kort.getUserNamesAsArray().contains(denneBruger.getLokalNavn())) {
                        mCardView.setCardBackgroundColor(Color.rgb(177, 253, 231));
                        System.out.println(kort.getCardName() + " indeholder ikke navnet: " + denneBruger.getLokalNavn());
                    }
                }

                mAdapter.notifyDataSetChanged();

                mCardView = findViewById(R.id.cardViewID);



            }

            @Override
            public void onDeleteClick(int position) {
                removeItem(position); //fjerner kortet lokalt

                brugerRef.child(String.valueOf(mCardList.get(position).getCardID())); //finder det child der hedder det samme som cardID for det kort der bliver klikket på

            }
        });
    }

    public void setButtons() { //tilføjer vores buttons
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

    public void openSettings() {
        Intent SettingsIntent = new Intent(this, Settings.class);
        startActivity(SettingsIntent);
    }
}