package com.example.mhike;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsqlite.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class addObservation extends AppCompatActivity {

    DatabaseReference myRef;
    FirebaseDatabase database;
    String hike_name, animal_selected, veg_selected, weather_selected,trail_selected;
    DBHelper myDB;
    Button btn,obs;
    TextView fa,fl,vg,we,tr;
    EditText obhike, fauna, flora, vegetation, weather, trail, comment;
    String date, s_fauna, s_flora, s_vegetation, s_weather, s_trail, s_comment;


    @SuppressLint("QueryPermissionsNeeded")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String hikename = intent.getStringExtra("m5");

        database = FirebaseDatabase.getInstance();

        setContentView(R.layout.activity_add_observation);
        myDB = new DBHelper(this);
        btn = (Button) findViewById(R.id.button);
        obs = (Button) findViewById(R.id.button2);
        obhike = (EditText) findViewById(R.id.date);
        fauna = (EditText) findViewById(R.id.fauna);
        flora = (EditText) findViewById(R.id.flora);
        vegetation = (EditText) findViewById(R.id.vegetation);
        weather = (EditText) findViewById(R.id.weather);
        trail = (EditText) findViewById(R.id.trail);
        comment = (EditText) findViewById(R.id.comment);

        fa= (TextView) findViewById(R.id.fa);
        fl = (TextView) findViewById(R.id.fl);
        vg = (TextView) findViewById(R.id.vg);
        we = (TextView) findViewById(R.id.we);
        tr = (TextView) findViewById(R.id.tr);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String currentDateAndTime = sdf.format(new Date());
        obhike.setText(currentDateAndTime);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //hikename = obhike.getText().toString();

                date = obhike.getText().toString();
                s_fauna = fauna.getText().toString();
                s_flora = flora.getText().toString();
                s_vegetation = vegetation.getText().toString();
                s_weather = weather.getText().toString();
                s_trail = trail.getText().toString();
                s_comment = comment.getText().toString();
                if (s_fauna.equals("") || s_flora.equals("") || s_vegetation.equals("")|| s_weather.equals("") || s_trail.equals("")) {
                    Toast.makeText(addObservation.this, "Fill all the required fields", Toast.LENGTH_SHORT).show();
                    fa.setTextColor(Color.RED);
                    fl.setTextColor(Color.RED);
                    vg.setTextColor(Color.RED);
                    we.setTextColor(Color.RED);
                    tr.setTextColor(Color.RED);



                } else {

                    myRef=database.getReference("hikedata").child(hikename).child("zobservation").child(date);


                    myRef.child("Comments").setValue(s_comment);
                    myRef.child("Fauna").setValue(s_fauna);
                    myRef.child("Flora").setValue(s_flora);
                    myRef.child("Vegetation").setValue(s_vegetation);
                    myRef.child("Weather").setValue(s_weather);
                    myRef.child("Date").setValue(date);
                    myRef.child("Trail Condition").setValue(s_trail);



                  //  myDB.insertObservation(hikename, date, s_fauna, s_flora, s_vegetation, s_weather, s_trail, s_comment);
                   // String data = s_fauna + s_flora + s_vegetation + s_weather + s_trail + s_comment;
                    Toast.makeText(addObservation.this," Added Observation", Toast.LENGTH_SHORT).show();

                }

            }
        });

        obs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                intent = new Intent(getApplicationContext(), display_observation.class);
                intent.putExtra("hn",hikename);
                startActivity(intent);

            }
        });

    }

    }

