package com.example.mhike;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.loginsqlite.R;
import com.example.loginsqlite.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {

    public String a,markerName="";
    EditText c;
    TextView label,loc;
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LatLng SEAFORD = new LatLng(50.770303, 0.096972);
    LatLng Leith_Hill = new LatLng(51.17654986624762, -0.3714794284438108);
    LatLng RICKMANSWORTH = new LatLng(51.650804243266066, -0.4857083526749762);
    LatLng CHESHAM = new LatLng(51.69834656068977, -0.6078956885765372);
    LatLng Hassocks = new LatLng(50.92472463107201, -0.14561137274531452);
    LatLng Lewes = new LatLng(50.868584010854434, 0.008430704010893327);
    LatLng London = new LatLng(51.501815069622445, -0.12086200833592975);

    float zoomLevel = (float) 8.50;

    // two array list for our lat long and location Name;
    private ArrayList<LatLng> latLngArrayList;
    private ArrayList<String> locationNameArraylist;
    Button nexttoobser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // initializing our array lists.
        latLngArrayList = new ArrayList<>();
        locationNameArraylist = new ArrayList<>();

        // on below line we are adding
        // data to our array list.
        latLngArrayList.add(SEAFORD);
        locationNameArraylist.add("SEAFORD");
        latLngArrayList.add(Leith_Hill);
        locationNameArraylist.add("Leith Hill");
        latLngArrayList.add(RICKMANSWORTH);
        locationNameArraylist.add("RICKMANSWORTH");
        latLngArrayList.add(CHESHAM);
        locationNameArraylist.add("CHESHAM-The chess valley park");
        latLngArrayList.add(Hassocks);
        locationNameArraylist.add("Hassocks");
        latLngArrayList.add(Lewes);
        locationNameArraylist.add("Lewes");
        c=findViewById(R.id.hikena);

        label=findViewById(R.id.label);
        loc=findViewById(R.id.location);
        nexttoobser = (Button) findViewById(R.id.nex);

        nexttoobser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a= c.getText().toString();

                if (a.equals("")||markerName.equals("")) {
                    Toast.makeText(MapsActivity.this, "Fill hike name and location", Toast.LENGTH_SHORT).show();
                    label.setTextColor(Color.RED);
                    loc.setTextColor(Color.RED);

                } else {
                    Intent in = new Intent(getApplicationContext(), addhikedata.class);
                    in.putExtra("f1", a);
                    in.putExtra("f2", markerName);
                    startActivity(in);


                }

            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


        }
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);

        // below line is to add marker to google maps
        for (int i = 0; i < latLngArrayList.size(); i++) {

            // adding marker to each location on google maps
            mMap.addMarker(new MarkerOptions().position(latLngArrayList.get(i)).title(locationNameArraylist.get(i)));

            // below line is use to move camera.
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(London, zoomLevel));
        }


        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                markerName = marker.getTitle();
                Toast.makeText(MapsActivity.this, "Clicked location is " + markerName, Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT)
                .show();
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG)
                .show();


    }

}
