package com.stvjbz.sampleapp10;

import android.location.Address;
import android.location.Location;
import android.location.LocationListener;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Chronometer;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,
        LoaderManeger.LoaderCallbacks<Address> {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    private static final int ADDRESSLOADER_ID = 0;
    // INTERVAL:500, FASTESTINTERVAL:16 できれいな線が描ける
    private static final int FASTERSINTERVAL = 16;


    private GoogleMap mMap;
    private GoogleAPiClient mGoogleApiClient;
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(INTERVAL)  // 位置情報の更新間隔をミリ秒で設定
            .setFastestInterval(FASTERSINTERVAL)
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY); // 位置情報取得要求の優先順位
    private FusedLocationProviderApi mFusedLocationProviderApi = LocationServices.FusedLocationApi;
    private List<LatLng> mRunList = new ArrayList<LatLng>();
    private WifiManager mWifi;
    private boolean mWifi;
    private boolean mWifiOff = false;
    private long mStartTimeMillis;
    private double mMeter = 0.0;
    private double mSpeed = 0.0;
    private DatabaseHelper mDbHelper;
    private boolean mStart = false;
    private boolean mFirst = false;
    private boolean mStop = false;
    private boolean mAsked = false;
    private Chronometer mChronometer;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // メンバー変数が初期化されることへの対処
        outState.putBoolean("ASKED", mAsked);
   }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAsked = savedInstanceState.getBoolean("ASKED");
    }

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
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void wifiComfirm() {
        mWifi = (WifiManager)getSystemService(WIFI_SERVICE);

        if(mWifi.isWifiEnable()) {
            wifiComfirmDialog();
        }
    }

    public void wifiOff() {
        mWifi.setWifiEnabled(false);
        mWifiOff=true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
