package com.qwerty.acer.facebook52;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by acer on 10/25/2016.
 */
public class MyLocationListner implements LocationListener {

    public static double mLatitude;
    public static double mLongitude;
    @Override
    public void onLocationChanged(Location location) {
        mLatitude=location.getLatitude();
        mLongitude=location.getLongitude();

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
