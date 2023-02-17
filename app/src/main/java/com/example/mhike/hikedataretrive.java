package com.example.mhike;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import com.example.loginsqlite.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class hikedataretrive extends AppCompatActivity {

    // creating variables for our list view.
    public ListView lv;
    ArrayList<String>  Userlist=new ArrayList<>();


    // creating a variable for database reference.
    DatabaseReference preference,d1;
    TextView text;
    String Name,z,a,b,c,d,e,f,g,h,i;
    ArrayAdapter<String> adapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hikedataretrive);


        lv = findViewById(R.id.listv);




        preference = FirebaseDatabase.getInstance().getReference("hikedata");
        d1=FirebaseDatabase.getInstance().getReference("hikedata/seaford3");


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

                    adapter=new ArrayAdapter<String>(hikedataretrive.this,
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
                String name = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(),hikedataview.class);
                intent.putExtra("m5", name);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu with items using MenuInflator
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu, menu);

        // Initialise menu item search bar
        // with id and take its object
        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        // attach setOnQueryTextListener
        // to search view defined above
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // Override onQueryTextSubmit method which is call when submit query is searched
            @Override
            public boolean onQueryTextSubmit(String query) {
                // If the list contains the search query than filter the adapter
                // using the filter method with the query as its argument
                if (Userlist.contains(query)) {
                    adapter.getFilter().filter(query);
                } else {
                    // Search query not found in List View
                    Toast.makeText(hikedataretrive.this, "Not found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            // This method is overridden to filter the adapter according
            // to a search query when the user is typing search
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

}