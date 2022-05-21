package com.example.hvorerduven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;



public class OpretBruger extends AppCompatActivity {

    private Button brugernavn;
    EditText editBruger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_bruger);

        brugernavn = (Button)findViewById(R.id.opretNyBruger);
        editBruger   = (EditText)findViewById(R.id.username);

        brugernavn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.v("TEST123", editBruger.getText().toString());
                    }
                });
    }

            }