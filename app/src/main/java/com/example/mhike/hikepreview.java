package com.example.mhike;




import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.example.loginsqlite.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class hikepreview extends AppCompatActivity {
    TextView t1, t2, t3, t4, t5, t6, t7, k1, k2, k3, k4, k5, k6, k7;
    Button submit;
    TextView h1,h2;


    FirebaseDatabase database;
    DatabaseReference myRef;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hikepreview);

        database=FirebaseDatabase.getInstance();


        submit = findViewById(R.id.submit);


        h1 = findViewById(R.id.helo1);
        h2 = findViewById(R.id.helo2);

        t1 = findViewById(R.id.helo3);
        t2 = findViewById(R.id.helo4);
        t3 = findViewById(R.id.helo5);
        t4 = findViewById(R.id.helo6);
        t5 = findViewById(R.id.helo7);
        t6 = findViewById(R.id.helo8);
        t7 = findViewById(R.id.helo9);

        k1 = findViewById(R.id.he1);
        k2 = findViewById(R.id.he2);
        k3 = findViewById(R.id.he3);
        k4 = findViewById(R.id.he4);
        k5 = findViewById(R.id.he5);
        k6 = findViewById(R.id.he6);
        k7 = findViewById(R.id.he7);



        Intent intent = getIntent();
        final String o1 = intent.getStringExtra("g1");
        final String o2 = intent.getStringExtra("g2");

        final String s1 = intent.getStringExtra("m1");
        final String s2 = intent.getStringExtra("m2");
        final String s3 = intent.getStringExtra("m3");
        final String s4 = intent.getStringExtra("m4");
        final String s5 = intent.getStringExtra("m5");
        final String s6 = intent.getStringExtra("m6");
        final String s7 = intent.getStringExtra("m7");
        h1.setText(o1);
        h2.setText(o2);
        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
        t4.setText(s4);
        t5.setText(s5);
        t6.setText(s6);
        t7.setText(s7);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /**  System.out.println("st" + o1);
               System.out.println("yt" + o2);
                System.out.println("at" + s1);
                System.out.println("bt" + s2);
                System.out.println("ct" + s3);
                System.out.println("dt" + s4);
                System.out.println("et" + s5);
                System.out.println("ft" + s6);
                System.out.println("gt" + s7);
                SharedPreferences insertSP = getSharedPreferences("First", MODE_PRIVATE);
                SharedPreferences.Editor insterEditor = insertSP.edit();
                insterEditor.putString("HN", o1);
                insterEditor.putString("IN", o2);
                insterEditor.putString("AN", s1);
                insterEditor.putString("BN", s2);
                insterEditor.putString("CN", s3);
                insterEditor.putString("DN", s4);
                insterEditor.putString("EN", s5);
                insterEditor.putString("FN", s6);
                insterEditor.putString("GN", s7);
                insterEditor.commit();
               // Toast.makeText(hikepreview.this, "Inserted Success", Toast.LENGTH_SHORT).show();*/

               // mm.insertData(o1,o2,s1,s2,s3,s4,s5,s6,s7);
                //Toast.makeText(hikepreview.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                   // Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    //startActivity(intent);
                myRef=database.getReference("hikedata").child(o1);
                myRef.child("Name").setValue(o1);

                myRef.child("Location").setValue(o2);
                myRef.child("Date").setValue(s1);
                myRef.child("Distance").setValue(s2);
                myRef.child("Level").setValue(s3);
                myRef.child("suitability").setValue(s4);
                myRef.child("Attraction").setValue(s5);
                myRef.child("Parking").setValue(s6);
                myRef.child("Description").setValue(s7);
                Toast.makeText(hikepreview.this, "Data Added", Toast.LENGTH_SHORT).show();
                Intent intent;
                intent = new Intent(getApplicationContext(), homeActivity.class);
                startActivity(intent);

            }
        });

    }

}
