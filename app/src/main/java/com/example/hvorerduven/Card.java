package com.example.hvorerduven;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Card {

    private int cardID;
    private String cardName;
    private ArrayList<User> usersInCard = new ArrayList<>();
    private ArrayList<String> userNamesInCard = new ArrayList<>();
    private Room belongsToRoom;

    //FirebaseDatabase db = FirebaseDatabase.getInstance();
    //DatabaseReference dbRefBruger = db.getReference("Bruger");

    private List<User> brugerListe = new ArrayList<>();

    public Card(String cardName) {
        this.cardName = cardName;

        //her skal vi tilføje navnene fra vores database, men jeg tilføjer dem bare manuelt for at teste

        usersInCard.add(new User("Simba"));
        usersInCard.add(new User("Nala"));
        usersInCard.add(new User("Timon"));

        /*//dbRefBruger.addValueEventListener();
        DatabaseReference dbBruger;
        dbBruger = FirebaseDatabase.getInstance().getReference("Bruger");
        //dbBruger.addChildEventListener(valueEventListener);

        Query query = FirebaseDatabase.getInstance().getReference("Bruger")
                .;

         */

        Query query = FirebaseDatabase.getInstance().getReference("Bruger");
        System.out.println(query);

        //query.addListenerForSingleValueEvent(brugerValueEventListener);

        ValueEventListener brugerValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                brugerListe.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        brugerListe.add(user);
                        System.out.printf("user");
                    }
                    //System.out.println(brugerListe.toString());
                    //CardAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };




        for (int i = 0; i < usersInCard.size(); i++ ){
            userNamesInCard.add(usersInCard.get(i).getBrugernavn() );
            //System.out.println(usersInCard.get(i).getBrugernavn());
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
