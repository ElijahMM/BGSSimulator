package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

/**
 * Created by emi on 22.02.2017.
 */

import android.location.Location;

/**
 * This class represents the model of the GPS value as it is received from GPS Sensor
 */
public class GpsValueModel {

    Long timeStamp;
    private Location location = new Location("");

    public GpsValueModel() {
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = Long.valueOf(timeStamp);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
