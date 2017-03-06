package com.example.mihai.bgssimulator.Simultor.GatherData;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.GpsValueModel;
import com.example.mihai.bgssimulator.Simultor.FileSensorLog;
import com.example.mihai.bgssimulator.Utils.AbsValues;
import com.example.mihai.bgssimulator.Utils.Settings;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import io.realm.Realm;

/**
 * Created by mihai on 02.03.2017.
 */

public class GPSLocation implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest locationRequest;
    private Context context;
    private Realm realm;

    public void connectGoogleClient(Context context) {
        this.context = context;

        this.locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(AbsValues.GPS_REQUEST_INTERVAL_INCREASED)
                .setFastestInterval(AbsValues.GPS_REQUEST_FASTEST_INTERVAL_INCREASED)
                .setMaxWaitTime(AbsValues.GPS_REQUEST_INTERVAL_INCREASED);

        mGoogleApiClient = new GoogleApiClient.Builder(this.context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

    }

    @SuppressWarnings("MissingPermission")
    public void startLocationUpdates() {
        realm = Realm.getDefaultInstance();
        mGoogleApiClient.connect();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    locationRequest, this);
        }
    }

    public void stopLocationUpdate() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.flushLocations(mGoogleApiClient);
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);

            mGoogleApiClient.disconnect();
            locationRequest = null;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (Settings.isSimulatorOn) {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        handleNewGPSLocation(location);
    }

    public void handleNewGPSLocation(Location location) {
//        FileSensorLog.writeToGPSFile(location);
        if (realm == null) {
            return;
        }
        GpsValueModel gpsValueModel = new GpsValueModel();
        gpsValueModel.setTimeStamp(System.currentTimeMillis() + "");
        gpsValueModel.setLocation(location);
        realm.copyToRealm(gpsValueModel);
    }
}
