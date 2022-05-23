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
    private ArrayList<User> usersInCard = new ArrayList<>();
    private ArrayList<String> userNamesInCard = new ArrayList<>();
    private Room belongsToRoom;

    //FirebaseDatabase db = FirebaseDatabase.getInstance();
    //DatabaseReference dbRefBruger = db.getReference("Bruger");


    String username = "kristoffer";
    int pass = 12345;
    int roomID = 1;
    int cardID = 1;
    String roomName = "camp";

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference user = database.getReference("Bruger/"+username+"/brugernavn");
    DatabaseReference password = database.getReference("Bruger/"+username+"/password");
    DatabaseReference roomNum = database.getReference("Bruger/"+username+"/roomID");
    DatabaseReference roomNam = database.getReference("Room/"+Integer.toString(roomID)+"/roomID");

    //private List<User> brugerListe = new ArrayList<>();

    public Card(String cardName) {
        this.cardName = cardName;

        //her skal vi tilføje navnene fra vores database, men jeg tilføjer dem bare manuelt for at teste

        //usersInCard.add(new User("Simba"));
        //usersInCard.add(new User("Nala"));
        //usersInCard.add(new User("Timon"));


        final DatabaseReference brugerRef = database.getReference("Bruger");
        brugerRef.orderByChild("brugernavn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot brugerSnapshot : dataSnapshot.getChildren()) { //for hver bruger


                    if (brugerSnapshot.child("cardID").getValue(int.class) == 1) {
                        String bruger = brugerSnapshot.getKey();
                        userNamesInCard.add(bruger);
                        System.out.println(bruger);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






        /*for (int i = 0; i < usersInCard.size(); i++ ){
            userNamesInCard.add(usersInCard.get(i).getBrugernavn() );
            //System.out.println(usersInCard.get(i).getBrugernavn());
        }

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
