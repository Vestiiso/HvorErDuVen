package com.example.hvorerduven;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //test opdaterer den?


    private EditText number, message;
    private Button send;
    private Button testHovedsiden;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = findViewById(R.id.number);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);
        testHovedsiden = (Button) findViewById(R.id.testHovedsiden);

        testHovedsiden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHovedsiden();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){

                        sendSMS();
                    }
                    else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1);

                    }
                }

            }
        });
    }

    public void openHovedsiden() {
        Intent hovedsidenIntent = new Intent(this, Hovedsiden.class);
        startActivity(hovedsidenIntent);
    }


    private void sendSMS() {
        String phoneNo = number.getText().toString().trim();
        String SMS = message.getText().toString().trim();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null,SMS,null,null);
            Toast.makeText(this, "SMS Sent!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to send SMS", Toast.LENGTH_SHORT).show();
        }


    }
}

