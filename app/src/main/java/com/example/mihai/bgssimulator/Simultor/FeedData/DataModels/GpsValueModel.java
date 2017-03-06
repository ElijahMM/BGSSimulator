package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

/**
 * Created by emi on 22.02.2017.
 */

import android.location.Location;

import io.realm.RealmObject;

/**
 * This class represents the model of the GPS value as it is received from GPS Sensor
 */
public class GpsValueModel extends RealmObject {

    private Long timeStamp = 0l;
    private Double lat = 0d;
    private Double lng = 0d;
    private Float acc = 0f;
    private Double alt = 0d;

    public GpsValueModel() {
    }


    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = Long.valueOf(timeStamp);
    }

    public Location getLocation() {
        Location location = new Location("");
        location.setLatitude(this.lat);
        location.setLongitude(this.lng);
        location.setAltitude(this.alt);
        location.setAccuracy(this.acc);
        return location;
    }

    public void setLocation(Location location) {
        this.lat = location.getLatitude();
        this.lng = location.getLongitude();
        this.acc = location.getAccuracy();
        this.alt = location.getAltitude();
    }
}
