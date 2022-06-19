package com.example.hvorerduven;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class OpretBruger extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef = database.getReference("Bruger");


    private Button opretBrugerBtn;
    EditText editBruger;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_opret_bruger);
            MaterialButton opretNyBruger = (MaterialButton) findViewById(R.id.opretNyBruger);
            opretBrugerBtn = (Button)findViewById(R.id.opretNyBruger);
            editBruger   = (EditText)findViewById(R.id.username);
            editPassword   = (EditText)findViewById(R.id.password);

        opretBrugerBtn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        User bruger = new User();
                        bruger.setBrugernavn(editBruger.getText().toString());
                        bruger.setPassword(editPassword.getText().toString());
                        addBruger(bruger);

                        LokalBruger.getInstance().setBrugernavn(bruger.getBrugernavn());
                        LokalBruger.getInstance().setIsTheUserLoggedIn(true);

                        openHovedsiden();
                    }
/*

//det her er for at få den til at tjekke om brugeren allerede eksisterer, men jeg kan ikke få det til at virke ordentligt


                    ValueEventListener eventListener = new ValueEventListener() {
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (!dataSnapshot.exists()) {
                                User bruger = new User();
                                addBruger(bruger);
                                Toast.makeText(OpretBruger.this, "Bruger Oprettet",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Log.d("TEST123",error.getMessage());
                        Toast.makeText(OpretBruger.this, "Bruger eksisterer allerede!",
                                Toast.LENGTH_SHORT).show();
                    }
                };

 */


                    public void addBruger(User nyUser) {
                        Map<String, String> userData = new HashMap<>();
                        userData.put("brugernavn", nyUser.getBrugernavn());
                        userData.put("password", nyUser.getPassword());
                        userRef = userRef.child(String.valueOf(nyUser.getBrugernavn()));
                        userRef.setValue(userData);
                        Toast.makeText(OpretBruger.this, "Bruger Oprettet",
                                Toast.LENGTH_SHORT).show();
                        userRef = database.getReference("Bruger");

                    }
                });
    }

    public void openHovedsiden() {
        Intent hovedsidenIntent = new Intent(this, Hovedsiden.class);
        startActivity(hovedsidenIntent);
    }

}



