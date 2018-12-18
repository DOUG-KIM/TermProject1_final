package com.example.deokwook.termproject1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback, OnStreetViewPanoramaReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private double lat;
    private double lng;
    private String r_name;
    private String money;
    private String r_text;
    private String phone;


    private GoogleApiClient mGoogleApiClient;
    private static final LatLng jung = new LatLng(37.345080, 126.738171);
   // private static final LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);


    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent =getIntent();
        lat = intent.getDoubleExtra("latitude", 0);
        lng = intent.getDoubleExtra("longitude", 0);
        r_name = intent.getStringExtra("r_name");
        money = intent.getStringExtra("money");
        r_text = intent.getStringExtra("r_text");
        phone = intent.getStringExtra("phone");

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }




    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jung, 1000));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(200), 2000, null);
       /* CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
                .zoom(50)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);





        LatLng request = new LatLng(lat,lng);


        mMap.addMarker(new MarkerOptions().position(request).title("심부름: " + r_name + "(" + lat + ", " + lng + ")")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.myloc)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(request));



        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                String text = "[심부름 정보] " + " 요청: " + r_name + " 수수료: " + money + " 세부내용: " + r_text + " 전화번호: " + phone;
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(getApplicationContext(), lastList.class);
                intent.putExtra("latitude", lat);
                intent.putExtra("longitude", lng);
                intent.putExtra("r_name", r_name);
                intent.putExtra("money", money);
                intent.putExtra("r_text", r_text);
                intent.putExtra("phone", phone);
                startActivity(intent);

                return false;

            }

        });


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            // Show rationale and request permission.
        }






    }

    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
        /*panorama.setPosition(new LatLng(-33.87365, 151.20689));
        panorama.setPanningGesturesEnabled(true);
        panorama.setUserNavigationEnabled(true);*/
    }
}
