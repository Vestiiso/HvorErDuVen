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
    String roomName = "camp";

    private ArrayList<User> usersInCard = new ArrayList<>();
    private ArrayList<String> userNamesInCard = new ArrayList<>();
    private Room belongsToRoom;


    FirebaseDatabase database = FirebaseDatabase.getInstance();


    public Card(String cardName) {
        this.cardName = cardName;

        final DatabaseReference brugerRef = database.getReference("Bruger");
        brugerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot brugerSnapshot : dataSnapshot.getChildren()) { //for hver bruger

                    if (brugerSnapshot.child("cardID").getValue(int.class) == null){
                        break;
                    }
                    if (brugerSnapshot.child("cardID").getValue(int.class) == 1) {
                        String bruger = brugerSnapshot.getKey();
                        userNamesInCard.add(bruger);

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*//find det højeste cardID i databasen, og gør dette korts ID 1 højere
        final DatabaseReference cardRef = database.getReference("Card");
        cardRef.orderByKey().limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String str = dataSnapshot.getChildren();

                System.out.println("*****************************" + dataSnapshot.getKey() );
                Log.d("tag", "*********************************************************" + dataSnapshot.getChildren() + str);
                //cardID = Integer.parseInt(cardRef.getKey())+1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */

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
