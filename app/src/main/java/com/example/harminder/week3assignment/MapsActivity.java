package com.example.harminder.week3assignment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.harminder.week3assignment.model.Reservation;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import io.realm.Realm;
import io.realm.RealmResults;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

/**
 * Request code for location permission request.
 *
 * @see #onRequestPermissionsResult(int, String[], int[])
 */
private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

private GoogleMap mMap;
private boolean mPermissionDenied;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_maps);
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this);

    BottomNavigationView bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);
    bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.action_search:
                    Toast.makeText(MapsActivity.this, "Action Search clicked", Toast.LENGTH_SHORT).show();
                    break;
                /**
                 * Gets lat and lng as input and returns free parking locations.
                 METHOD: GET

                 Input params:

                 lat -> Latitude of current location
                 lng -> Longitude of current location
                 */

                case R.id.action_reservation:
                    Toast.makeText(MapsActivity.this, "Action Reservation clicked", Toast.LENGTH_SHORT).show();
                    break;
                /**
                 * User Story 3:
                 As a user I must be able to reserve the parking location.
                 API to ping:
                 METHOD: = POST
                 last screen with RESERVED UNTIL NULL
                 */
            }
            return true;
        }
    });
}

    /**
     *
     * Database added from
     *
     */
    /**
     * 2nd time

    Realm realm = Realm.getDefaultInstance();

    saveReservation(realm);

    retrievingReservations(realm);
    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
    mapFragment.getMapAsync(this); // it automatically added the onMapReadyCallback before this
}

    private void retrievingReservations(Realm realm) {
        RealmResults<Reservation> results = realm.where(Reservation.class).findAllAsync();
        if (results.load()) {
            for (Reservation reservation : results) {
                System.out.println(reservation);
            }
        }
    }

    private void saveReservation(Realm realm) {
        realm.beginTransaction();

        Reservation reservation = new Reservation();
        reservation.setIs_reserved(true);

        realm.copyToRealm(reservation);

        realm.commitTransaction();
    }
     2nd time*/

    /**
     * Database added to
     */


       // before 2 lines}this closing bracket was after mapFragment.getMapAsync(this); moved back to oncreate method with this before mapFragment. (SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);)

@Override
public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //mMap.addMarker(new MarkerOptions().title("Hello From Map").position(new LatLng(51.5084, -0.1255)));
        mMap.addMarker(new MarkerOptions().title("Reserved").position(new LatLng(51.5074, 0.1278)));

//        mMap.setOnMyLocationButtonClickListener(this);
//        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
        }

/**
 * Enables the My Location layer if the fine location permission has been granted.
 */
private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
        //Show a dialog
        } else {
        // Location permission has not been granted yet, request it.
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

        }

        } else if (mMap != null) {
        // Access to the location has been granted to the app.
        mMap.setMyLocationEnabled(true);
        }
        }

@Override
public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
        return;
        }

        if (isPermissionGranted(permissions, grantResults,
        Manifest.permission.ACCESS_FINE_LOCATION)) {
        // Enable the my location layer if the permission has been granted.
        enableMyLocation();
        } else {
        // Display the missing permission error dialog when the fragments resume.
        mPermissionDenied = true;
        }
        }


public boolean isPermissionGranted(String[] grantPermissions, int[] grantResults,
        String permission) {
        for (int i = 0; i < grantPermissions.length; i++) {
        if (permission.equals(grantPermissions[i])) {
        return grantResults[i] == PackageManager.PERMISSION_GRANTED;
        }
        }
        return false;
        }
        }

    /**

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
    /**
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
*/

