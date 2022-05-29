package com.example.hvorerduven;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class Create_room extends AppCompatActivity {

    private Button back,Create_room,Join_room;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createroom);
        back = (Button) findViewById(R.id.back);
        Create_room = (Button) findViewById(R.id.Create_room);
        Join_room = (Button) findViewById(R.id.Join_room);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(Create_room.this, LoginScreen.class));
            }
        });

        Join_room.setOnClickListener(new View.OnClickListener() {
        @Override
           public void onClick(View view) {
              startActivity(new Intent(Create_room.this,Hovedsiden.class));
        }
       });

        Create_room.setOnClickListener(new View.OnClickListener() {
         @Override
            public void onClick(View view) {
                startActivity(new Intent(Create_room.this,Hovedsiden.class));
             }
        });

    }
}
