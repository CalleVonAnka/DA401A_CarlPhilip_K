package com.example.callevonanka.assignment_4;


import android.location.Location;
import android.location.LocationListener;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;

import com.example.callevonanka.assignment_4.dialogs.Dialog1;
import com.example.callevonanka.assignment_4.dialogs.Dialog2;
import com.example.callevonanka.assignment_4.dialogs.Dialog3;
import com.example.callevonanka.assignment_4.dialogs.Dialog4;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks,
        com.google.android.gms.location.LocationListener
{

    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private Vibrator mVibrator;
    private MediaPlayer mSound;

    private ArrayList<LatLng> mLatLng = new ArrayList<>();
    private ArrayList<Marker> mMarker = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        mSound = MediaPlayer.create(this,R.raw.cling);
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
                UiSettings mUISettings = mMap.getUiSettings();

                mUISettings.setZoomControlsEnabled(true);
                mUISettings.setMyLocationButtonEnabled(false);
                mUISettings.setCompassEnabled(true);

                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                mMap.setMyLocationEnabled(true);

                LatLng first = new LatLng(55.596929, 12.950161);
                LatLng second = new LatLng(55.599245, 12.956706);
                LatLng third = new LatLng(55.602209, 12.967016);
                LatLng fourth = new LatLng(55.604615, 12.975406);

                mLatLng.add(first);
                mLatLng.add(second);
                mLatLng.add(third);
                mLatLng.add(fourth);

                Marker firstMarker = mMap.addMarker(new MarkerOptions().position(first).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_48dp)));
                Marker secondMarker = mMap.addMarker(new MarkerOptions().position(second).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_48dp)));
                Marker thirdMarker = mMap.addMarker(new MarkerOptions().position(third).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_48dp)));
                Marker fourthMarker = mMap.addMarker(new MarkerOptions().position(fourth).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location_on_black_48dp)));

                mMarker.add(firstMarker);
                mMarker.add(secondMarker);
                mMarker.add(thirdMarker);
                mMarker.add(fourthMarker);

                mMap.moveCamera(CameraUpdateFactory.newLatLng(first));
                mMap.moveCamera(CameraUpdateFactory.zoomTo(18));

                mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();


                        switch (marker.getTitle()) {
                            case "Question 1":
                                Dialog1 dialog1 = new Dialog1();
                                dialog1.show(fragmentTransaction, "");
                                break;
                            case "Question 2":
                                Dialog2 dialog2 = new Dialog2();
                                dialog2.show(fragmentTransaction, "");
                                break;
                            case "Question 3":
                                Dialog3 dialog3 = new Dialog3();
                                dialog3.show(fragmentTransaction,"");
                                break;
                            case "Question 4":
                                Dialog4 dialog4 = new Dialog4();
                                dialog4.show(fragmentTransaction, "");
                                break;
                        }
                        return true;
                    }
                });
            }
        });
        setUpMapIfNeeded();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        MarkerClickDialog markerClickDialog = new MarkerClickDialog();
        markerClickDialog.show(fragmentTransaction, "");
        mVibrator.vibrate(1000);
    }

    private void setUpMapIfNeeded(){
        if (mMap == null)
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i("MapsActivity", "On connected");
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        Location mLocation = new Location("Marker");

        for (int i = 0; i < mMarker.size(); i++) {

            mLocation.setLongitude(mLatLng.get(i).longitude);
            mLocation.setLatitude(mLatLng.get(i).latitude);

            float distance = location.distanceTo(mLocation);

            if(distance < 50){
                mSound.start();
                mVibrator.vibrate(500);
                mMarker.get(i).setTitle("Question "+(i+1));
                mMarker.get(i).setSnippet("Click me for question");
                mMarker.get(i).showInfoWindow();
            }
            else{
                mMarker.get(i).hideInfoWindow();
                mMarker.get(i).remove();
            }
        }
    }
}
