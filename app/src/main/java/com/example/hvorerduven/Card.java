package com.example.hvorerduven;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Card {

    private int cardID;
    private String cardName;
    private ArrayList<User> usersInCard = new ArrayList<>();
    private ArrayList<String> userNamesInCard = new ArrayList<>();
    private Room belongsToRoom;

    //FirebaseDatabase db = FirebaseDatabase.getInstance();
    //DatabaseReference dbRefBruger = db.getReference("Bruger");


    String username = "kristoffer";
    int pass = 12345;
    int roomID = 001;
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

        usersInCard.add(new User("Simba"));
        usersInCard.add(new User("Nala"));
        usersInCard.add(new User("Timon"));


        final DatabaseReference brugerRef = database.getReference("brugerTest");
                brugerRef.orderByChild("brugernavn").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot brugerSnapshot : dataSnapshot.getChildren()) { //for hver bruger
                    String bruger = brugerSnapshot.getValue(String.class);
                    userNamesInCard.add(bruger);
                    System.out.println(bruger);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        /*
        final DatabaseReference brugerRef = database.getReference("brugerTest");
        brugerRef.orderByChild("brugernavn").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                //User bruger = dataSnapshot.getValue(User.class);
                //User bruger = dataSnapshot.getValue(User.class);
                //String bruger = dataSnapshot.toString();
                //System.out.println(dataSnapshot.getKey() + dataSnapshot.getValue());
                //System.out.println(bruger.getBrugernavn());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         */


        /*
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                System.out.println("********************************** SVARET ER: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "FEJL I VED ONDATACHANGE.", error.toException());
            }
        });


        //dbRefBruger.addValueEventListener();
        DatabaseReference dbBruger;
        dbBruger = FirebaseDatabase.getInstance().getReference("Bruger");
        //dbBruger.addChildEventListener(valueEventListener);

        Query query = FirebaseDatabase.getInstance().getReference("Bruger")
                .;


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
        */


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
