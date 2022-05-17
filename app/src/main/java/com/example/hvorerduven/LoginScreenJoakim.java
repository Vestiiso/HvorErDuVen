package com.example.hvorerduven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class LoginScreenJoakim extends AppCompatActivity {

    private FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen_joakim);
        // Write a message to the database

        String username = "kristoffer";
        int pass = 12345;
        int roomID = 001;
        String roomName = "camp";


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference brugernavn = database.getReference("Bruger/"+username+"/brugernavn");
        DatabaseReference password = database.getReference("Bruger/"+username+"/password");
        DatabaseReference updateRoom = database.getReference("Bruger/"+username+"/roomID");


        brugernavn.setValue(username);
        password.setValue(pass);
        updateRoom.setValue(roomID);



        DatabaseReference Room = database.getReference("Room/"+roomID);


        brugernavn.setValue(roomName);

        // Read from the database
        brugernavn.addValueEventListener(new ValueEventListener() {
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
        // Create a Cloud Storage reference from the app
    //    StorageReference storageRef = storage.getReference();
        // Create a reference to "mountains.jpg"
    //    StorageReference mountainsRef = storageRef.child("mountains.jpg");

// Create a reference to 'images/mountains.jpg'
 //       StorageReference mountainImagesRef = storageRef.child("images/mountains.jpg");

// While the file names are the same, the references point to different files
  //      mountainsRef.getName().equals(mountainImagesRef.getName());    // true
  //      mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

        // Create a reference with an initial file path and name
    //    StorageReference pathReference = storageRef.child("images/stars.jpg");

// Create a reference to a file from a Cloud Storage URI
   //     StorageReference gsReference = storage.getReferenceFromUrl("gs://bucket/images/stars.jpg");

// Create a reference from an HTTPS URL
// Note that in the URL, characters are URL escaped!
   //     StorageReference httpsReference = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/b/bucket/o/images%20stars.jpg");
    }
}