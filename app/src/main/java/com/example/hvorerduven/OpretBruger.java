package com.example.hvorerduven;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;


public class OpretBruger extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef = database.getReference("Bruger");


    private Button brugernavn;
    EditText editBruger;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_opret_bruger);
            MaterialButton opretNyBruger = (MaterialButton) findViewById(R.id.opretNyBruger);
            brugernavn = (Button)findViewById(R.id.opretNyBruger);
            editBruger   = (EditText)findViewById(R.id.username);
            editPassword   = (EditText)findViewById(R.id.password);

        brugernavn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        Log.v("TEST123", editBruger.getText().toString());
                        User bruger = new User();
                        bruger.setBrugernavn(editBruger.getText().toString());
                        bruger.setPassword(editPassword.getText().toString());
                        addBruger(bruger);
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
                    }
                });
    }
}



