package com.example.mhike;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsqlite.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
import java.util.Map;

public class display_observation extends AppCompatActivity {

    // creating variables for our list view.
    public ListView lv;
    TextView d1,d2,d3,d4,d5,d6,d7,d8;
    ArrayList<String>  Userlist=new ArrayList<>();



    // creating a variable for database reference.
    DatabaseReference preference;
    TextView text;
    String o1,z,a,b,c,d,e,f,g,h,i;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_observation);


        lv = findViewById(R.id.listv);


        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        d3 = findViewById(R.id.d3);
        d4 = findViewById(R.id.d4);
        d5 = findViewById(R.id.d5);
        d6 = findViewById(R.id.d6);
        d7 = findViewById(R.id.d7);
        d8 = findViewById(R.id.d8);




        Intent intent = getIntent();
        o1 = intent.getStringExtra("hn");


        preference = FirebaseDatabase.getInstance().getReference("hikedata").child(o1).child("zobservation");


        preference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Userlist = new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()) {

                                    Userlist.add(String.valueOf(dsp.getValue())); //add result into array list

                    ArrayAdapter<String> adapter;
                    adapter=new ArrayAdapter<String>(display_observation.this,
                            android.R.layout.simple_list_item_1,
                            Userlist);

                    lv.setAdapter(adapter);

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}