package com.example.in_betweens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
//import android.location.LocationRequest;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.in_betweens.databinding.ActivityCarrierMapBinding;
import com.google.android.gms.location.LocationRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CarrierMapActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        com.google.android.gms.location.LocationListener {

    private GoogleMap mMap;
    GoogleApiClient googleApiClient;
    Location lastLocation;
    LocationRequest locationRequest;
    private ImageButton LogoutCarrierButton;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private boolean currentLogoutCarrierStatus=false;


    private ActivityCarrierMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();
        currentUser=mAuth.getCurrentUser();
        LogoutCarrierButton=(ImageButton)findViewById(R.id.logout);

        binding = ActivityCarrierMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        LogoutCarrierButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentLogoutCarrierStatus=true;
                DisconnectTheCarrier();
                mAuth.signOut();
               LogoutCarrier();
            }
       });
    }

    private void LogoutCarrier() {
        Intent logoutIntent=new Intent(CarrierMapActivity.this,LoginActivity.class);
        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logoutIntent);

                finish();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        buildGoogleApiClient();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);

    }

    @RequiresApi(api = Build.VERSION_CODES.S)

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(locationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);



    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        lastLocation;

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation=location;
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        String userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference CarrierAvailabilityRef= FirebaseDatabase.getInstance().getReference().child("Carriers Available");

        GeoFire geoFire=new GeoFire(CarrierAvailabilityRef);
        geoFire.setLocation(userID,new GeoLocation(location.getLatitude(),location.getLongitude()));

    }
    protected synchronized void buildGoogleApiClient()
    {
        googleApiClient =new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!currentLogoutCarrierStatus)
        {
            DisconnectTheCarrier();
        }
    }

    private void DisconnectTheCarrier() {
        String userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference CarrierAvailabilityRef= FirebaseDatabase.getInstance().getReference().child("Carriers Available");

        GeoFire geoFire=new GeoFire(CarrierAvailabilityRef);
        geoFire.removeLocation(userID);
    }
}