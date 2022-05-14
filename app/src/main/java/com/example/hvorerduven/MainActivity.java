package com.example.hvorerduven;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button testHovedsiden;
    private Button testSendSMS;
    private Button testSettings;
    private Button testLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testHovedsiden = (Button) findViewById(R.id.testHovedsiden);
        testSendSMS = (Button) findViewById(R.id.testSendSMS);
        testSettings = (Button) findViewById(R.id.testSettings);
        testLogin = (Button) findViewById(R.id.testLogin);

        testHovedsiden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHovedsiden();
            }
        });


        testSendSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSendSMS();
            }
        });

        testSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSettings();
            }
        });

        testLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });


    }

    public void openHovedsiden() {
        Intent hovedsidenIntent = new Intent(this, Hovedsiden.class);
        startActivity(hovedsidenIntent);
    }

    public void openSendSMS () {
        Intent SendSMSIntent = new Intent(this, SendSMS.class);
        startActivity(SendSMSIntent);
    }

    public void openSettings() {
        Intent SettingsIntent = new Intent(this, Settings.class);
        startActivity(SettingsIntent);
    }

    public void openLogin() {
        Intent LoginIntent = new Intent(this, LoginScreenJoakim.class);
        startActivity(LoginIntent);
    }


}

