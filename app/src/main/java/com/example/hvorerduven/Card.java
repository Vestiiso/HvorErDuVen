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

        //tilføj bruger med et hvis cardID til userNamesInCard, der bruges til at vise brugere i kortet//
        final DatabaseReference brugerRef = database.getReference("Bruger");
        brugerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot brugerSnapshot : dataSnapshot.getChildren()) { //for hver bruger

                    if (brugerSnapshot.child("cardID").getValue(int.class) == null){

                        break;
                    }
                    if (brugerSnapshot.child("cardID").getValue(int.class) == cardID) { //her indsættes det tal man vil lede efter under en brugers cardID//
                        String bruger = brugerSnapshot.getKey();

                        if (!userNamesInCard.contains(bruger)) {
                            userNamesInCard.add(bruger);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    //slet alle navne fra kortet
    public void sletBrugernavnFraUserNamesInCard(String navn) {
        if (usersInCard.contains(navn)) {
            usersInCard.remove(navn);
        }
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

    public ArrayList<String> getUserNamesAsArray() {
        return userNamesInCard;
    }

    public String getUserNamesInCard() {

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

                        if (!userNamesInCard.contains(bruger)) {
                            userNamesInCard.add(bruger);
                        }
                    }

                    if (brugerSnapshot.child("cardID").getValue(int.class) != cardID) { //her indsættes det tal man vil lede efter under en brugers cardID
                        String bruger = brugerSnapshot.getKey();

                        if (userNamesInCard.contains(bruger)) {
                            userNamesInCard.remove(bruger);
                        }
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


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
