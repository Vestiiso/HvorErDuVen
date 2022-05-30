package com.example.hvorerduven;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class LoginScreen extends AppCompatActivity {

    private Button opretBruger;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    String username = "kristoffer";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference kodeRef;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        opretBruger = (Button) findViewById(R.id.opretbutton);

        MaterialButton loginbutton = (MaterialButton) findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {

            @Override //tjek om brugeren findes i databasen og om det indtastet password er korrekt (når man trykker på login knappen
            public void onClick(View view) {

                final DatabaseReference brugerRef = database.getReference("Bruger");
                brugerRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        System.out.println("findesBrugeren burde levere: " + snapshot.hasChild(getIndtastetUsername()));

                        if (snapshot.hasChild(getIndtastetUsername())
                                && getIndtastetPassword().equals(snapshot.child(getIndtastetUsername()).child("password").getValue())) {
                            openLogin();

                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });


        opretBruger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOpretBruger();
            }
        });
        // Write a message to the database


        int pass = 12345;
        int roomID = 001;
        String roomName = "camp";


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        DatabaseReference user = database.getReference("Bruger/"+username+"/brugernavn");
        DatabaseReference password = database.getReference("Bruger/"+username+"/password");
        DatabaseReference roomNum = database.getReference("Bruger/"+username+"/roomID");
        DatabaseReference roomNam = database.getReference("Room/"+Integer.toString(roomID)+"/roomID");


        user.setValue(username);
        password.setValue(pass);
        roomNum.setValue(roomID);
        roomNam.setValue(roomName);





        // Read from the database
        user.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
        roomNum.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Long value = dataSnapshot.getValue(Long.class);
                Log.d("TAG", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });
    }



    private void checkIfUserExists() {
        DatabaseReference user = FirebaseDatabase.getInstance().getReference("Bruger");
        user.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    username = (String) dataSnapshot.child("Bruger").getValue();
                    System.out.println(dataSnapshot.child("Bruger").getValue());




                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    public void openLogin() {
        Intent LoginIntent = new Intent(this, Hovedsiden.class);
        startActivity(LoginIntent);
    }

    public void openOpretBruger() {
        Intent OpretBrugerIntent = new Intent(this, OpretBruger.class);
        startActivity(OpretBrugerIntent);
    }

    public String getIndtastetUsername () {
        TextView input = findViewById(R.id.username);
        String inputUsername = String.valueOf(input.getText());
        //System.out.println("getindtast... modtager: " + String.valueOf(input.getText()));

        return inputUsername;
    }

    public String getIndtastetPassword() {
        TextView input = findViewById(R.id.password);
        String inputPassword = String.valueOf(input.getText());

        return inputPassword;

    }

}