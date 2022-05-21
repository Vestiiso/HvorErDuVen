package com.example.hvorerduven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OpretBruger extends AppCompatActivity {

    Button Opret;
    EditText mEdit;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opret_bruger);

        Opret = (Button)findViewById(R.id.opretTilFirebase);
        mEdit   = (EditText)findViewById(R.id.username);

        Opret.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Log.v("EditText", mEdit.getText().toString());
                    }
                });
    }
}