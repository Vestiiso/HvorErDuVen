package com.example.hvorerduven;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class LoginScreenJoakim extends AppCompatActivity {

    private Button opretBruger;

    private FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_joakim);

        opretBruger = (Button) findViewById(R.id.opretbutton);

        //move to new activity
        opretBruger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOpretBruger();
            }
        });

        // Write a message to the database

        String username = "kristoffer";
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

    public void openOpretBruger() {
        Intent OpretBrugerIntent = new Intent(this, OpretBruger.class);
        startActivity(OpretBrugerIntent);
    }
}