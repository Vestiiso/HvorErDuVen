package com.example.hvorerduven;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Settings extends AppCompatActivity {

   private Button Logout,Security,Back;
   private FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        firebaseAuth = FirebaseAuth.getInstance();
        Logout = (Button) findViewById(R.id.logout);
        Security = (Button) findViewById(R.id.Security);
        Back = (Button) findViewById(R.id.back);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(Settings.this, SendSMS.class));
            }
        });

        Security.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.opentext.co.uk/about/copyright-information/site-privacy"));
                startActivity(browserIntent);
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Settings.this,MainActivity.class));
            }
        });
    }

}