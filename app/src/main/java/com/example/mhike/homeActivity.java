package com.example.mhike;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.loginsqlite.R;

public class homeActivity extends AppCompatActivity {
    
    Button viewHike, viewObservation, jsonbt, jsonbt2;
    String chkUser = LoginActivity.user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewHike=(Button) findViewById(R.id.viewHike);
        viewObservation=(Button) findViewById(R.id.viewObservation);
        jsonbt=(Button) findViewById(R.id.jsonbt);
        jsonbt2=(Button) findViewById(R.id.jsonbt2);

        viewHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkUser.equals("admin")){
                Intent intent = new Intent(getApplicationContext(),adminViewHike.class);
                startActivity(intent);
            }
                else{
                    Intent intent = new Intent(getApplicationContext(),hikedataretrive.class);
                    startActivity(intent);
                }
        }});
        viewObservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),choosehike.class);
                startActivity(intent);
            }
        });

        jsonbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),json.class);
                startActivity(intent);
            }
        });

        jsonbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),NonAdminViewHike.class);
                startActivity(intent);
            }
        });

    }
}