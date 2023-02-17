package com.example.mhike;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginsqlite.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class reset extends AppCompatActivity {

    // creating variables for our list view.
    public ListView lv;
    ArrayList<String>  Userlist=new ArrayList<>();


    // creating a variable for database reference.
    DatabaseReference preference,d1;
    TextView text;
    String name,z,a,b,c,d,e,f,g,h,i;




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);


        lv = findViewById(R.id.listv);







        preference = FirebaseDatabase.getInstance().getReference("hikedata");




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
                    // String Name= snapshot.child("Name").getValue().toString();

                    z=String.valueOf(dsp.child("Name").getValue());
                    Userlist.add(z);

                    /**   a=String.valueOf(dsp.child("Attraction").getValue());
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



                    //  Userlist.add(String.valueOf(dsp.getValue())); //add result into array list


                    // String joined = TextUtils.join(" ", Userlist);
                    //text.setText(joined);
                    ArrayAdapter<String> adapter;
                    adapter=new ArrayAdapter<String>(reset.this,
                            android.R.layout.simple_list_item_1,
                            Userlist);
                    lv.setAdapter(adapter);

                }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name = (String) parent.getItemAtPosition(position);
                // Intent intent = new Intent(getApplicationContext(),hikedataview.class);
                //  intent.putExtra("m5", name);
                // startActivity(intent);
                d1=FirebaseDatabase.getInstance().getReference("hikedata/"+name);

                d1.child("Attraction").setValue("Null");
                d1.child("Date").setValue("Null");
                d1.child("Description").setValue("Null");
                d1.child("Distance").setValue("Null");
                d1.child("Level").setValue("Null");
                d1.child("Location").setValue("Null");

                d1.child("Parking").setValue("Null");
                d1.child("suitability").setValue("Null");



                Toast.makeText(reset.this, "Data Reseted", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),adminViewHike.class);
                //intent.putExtra("m5", name);
                startActivity(intent);

            }
        });





    }

}
