package com.example.deokwook.termproject1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.drm.DrmManagerClient;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
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

import java.text.NumberFormat;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnStreetViewPanoramaReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private double lat;
    private double lng;
    private String r_name;
    private String money;
    private String r_text;
    private String phone;



    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private static final LatLng jungwang = new LatLng(37.345080, 126.738171);
   // private static final LatLng MOUNTAIN_VIEW = new LatLng(37.4, -122.1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        /*LatLng request = new LatLng(lat,lng);

         Marker marker = mMap.addMarker(new MarkerOptions()
                .position(request)
                .title("심부름: "+r_name+money+r_text+phone));
        marker.showInfoWindow();*/

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(jungwang, 15));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
       /* CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(MOUNTAIN_VIEW)      // Sets the center of the map to Mountain View
                .zoom(17)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)); */

        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);


        LatLng request = new LatLng(lat,lng);


        mMap.addMarker(new MarkerOptions().position(request).title("심부름: " + r_name + "(" + lat + ", " + lng + ")")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.myloc)));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(request));


       /* private Marker addMarker() {

        LatLng request = new LatLng(lat,lng);


            String formatted = NumberFormat.getCurrencyInstance().format((r_name));

            tv_marker.setText(formatted);


        tv_marker.setBackgroundResource(R.drawable.ic_marker_phone_blue);
        tv_marker.setTextColor(Color.WHITE);


            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.title("[요청제목] " + r_name);
            markerOptions.position(request);
            //markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(this, marker_root_view)));


            return mMap.addMarker(markerOptions);

        } */

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                String text = "[심부름 정보] " + " 요청: " + r_name + " 수수료: " + money + " 세부내용: " + r_text + " 전화번호: " + phone;
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG)
                        .show();
                Intent intent = new Intent(getApplicationContext(),lastList.class);
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
       /* panorama.setPosition(new LatLng(-33.87365, 151.20689));
        panorama.setPanningGesturesEnabled(true);
        panorama.setUserNavigationEnabled(true); */
    }
}
