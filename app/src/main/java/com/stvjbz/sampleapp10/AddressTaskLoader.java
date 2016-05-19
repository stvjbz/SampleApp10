package com.stvjbz.sampleapp10;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;
import java.util.Locale;

/**
 * Created by stvjbz on 16/05/18.
 */
public class AddressTaskLoader extends AsyncTaskLoader {
    private Geocoder mGeocoder = null;
    private double mLat;
    private double mLon;

    public AddressTaskLoader(Context context, double lat, double lon) {
        super(context);
        mGeocoder = new Geocoder(context, Locale.getDefault());

        mLat = lat;
        mLon = lon;
    }

    @Override
    public Address loadInBackground() {
        Address result = null;
        try {
            List<Address> results = mGeocoder.getFromLocation(mLat, mLon, 1); // 1は候補数
            if (results != null && !results.isEmpty()) {
                result = results.get(0);
            }
        } catch (IOException e) {
            Log.e("AddressTaskLoader", e.getMessage());
        }
        return result;
    }

    @Override
    protected void onStartLoading () {
        forceLoad();
    }

}
