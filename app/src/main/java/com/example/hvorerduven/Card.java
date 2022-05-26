package com.example.hvorerduven;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Card {

    private String cardName;
    int pass = 12345;
    int roomID = 1;
    int cardID = 1;
    int nytCardID;
    String roomName = "camp";

    private ArrayList<User> usersInCard = new ArrayList<>();
    private ArrayList<String> userNamesInCard = new ArrayList<>();
    private Room belongsToRoom;


    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public Card(String cardName, int cardID) {
        this.cardName = cardName;
        this.cardID = cardID;

        //tilføj bruger med et hvis cardID til userNamesInCard, der bruges til at vise brugere i kortet
        final DatabaseReference brugerRef = database.getReference("Bruger");
        brugerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot brugerSnapshot : dataSnapshot.getChildren()) { //for hver bruger

                    if (brugerSnapshot.child("cardID").getValue(int.class) == null){
                        break;
                    }
                    if (brugerSnapshot.child("cardID").getValue(int.class) == cardID) { //her indsættes det tal man vil lede efter under en brugers cardID
                        String bruger = brugerSnapshot.getKey();
                        userNamesInCard.add(bruger);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    //skift kortets overskrift
    public void changeText1(String text) { //den her skal modificeres fra guiden, så den ikke ændrer teksten
        cardName = text;
    }

    public int getCardID() {
        return cardID;
    }

    public void setCardID(int cardID) {
        this.cardID = cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public ArrayList<User> getUsersInCard() {
        return usersInCard;
    }

    public String getUserNamesInCard() {
        //System.out.println(userNamesInCard.toString());
        return userNamesInCard.toString();
    }

    public void setUsersInCard(ArrayList<User> usersInCard) {
        this.usersInCard = usersInCard;
    }

    public Room getBelongsToRoom() {
        return belongsToRoom;
    }

    public void setBelongsToRoom(Room belongsToRoom) {
        this.belongsToRoom = belongsToRoom;
    }
}
