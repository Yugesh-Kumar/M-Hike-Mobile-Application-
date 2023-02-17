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

public class hikedataview extends AppCompatActivity {

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
        setContentView(R.layout.activity_hikedataview);


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
        o1 = intent.getStringExtra("m5");


        preference = FirebaseDatabase.getInstance().getReference("hikedata/"+o1);

/**ValueEventListener eventListener=new ValueEventListener() {
@Override
public void onDataChange(@NonNull DataSnapshot snapshot) {
for(DataSnapshot ds : snapshot.getChildren()) {
String Name = ds.getKey();
}
}

@Override
public void onCancelled(@NonNull DatabaseError error) {

}
};*/


        /**   preference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
        String value = snapshot.getValue().toString();
        // System.out.println(value);
        // text.setText(value);
        for(DataSnapshot ds : snapshot.getChildren()) {
        String Name = ds.getKey();
        text.setText(Name);

        }


        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
        });*/

        preference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Userlist = new ArrayList<String>();
                for (DataSnapshot dsp : snapshot.getChildren()) {
                    //String n1= snapshot.child("Name").getValue().toString();
                    //Userlist.add(String.valueOf(dsp.child("Name").getValue()));

                    // z=String.valueOf(dsp.child("Name").getValue());
                    //   Userlist.add(z);

                    /** a=String.valueOf(dsp.child("Attraction").getValue());
                     b=String.valueOf(dsp.child("Date").getValue());
                     c=String.valueOf(dsp.child("Description").getValue());
                     d=String.valueOf(dsp.child("Distance").getValue());
                     e=String.valueOf(dsp.child("Level").getValue());
                     f=String.valueOf(dsp.child("Location").getValue());
                     g=String.valueOf(dsp.child("Name").getValue());
                     h=String.valueOf(dsp.child("Parking").getValue());
                     i=String.valueOf(dsp.child("suitability").getValue());

                     Userlist.add("Name : "+g);
                     Userlist.add("Attraction : "+a);
                     Userlist.add("Date : "+b);
                     Userlist.add("Description : "+c);
                     Userlist.add("Distance : "+d);
                     Userlist.add("Level : "+e);
                     Userlist.add("Location : "+f);
                     Userlist.add("Parking : "+h);
                     Userlist.add("suitability : "+i);*/


                    Userlist.add(String.valueOf(dsp.getValue())); //add result into array list


                    // String joined = TextUtils.join(" ", Userlist);
                    //text.setText(joined);
                    ArrayAdapter<String> adapter;
                    adapter=new ArrayAdapter<String>(hikedataview.this,
                            android.R.layout.simple_list_item_1,
                            Userlist);
                    //lv.setAdapter(adapter);

                }

                d1.setText(Userlist.get(0));
                d2.setText(Userlist.get(1));
                d8.setText(Userlist.get(2));
                d4.setText(Userlist.get(3));
                d5.setText(Userlist.get(4));
                d6.setText(Userlist.get(5));
                d7.setText(Userlist.get(7));
                d3.setText(Userlist.get(8));




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}