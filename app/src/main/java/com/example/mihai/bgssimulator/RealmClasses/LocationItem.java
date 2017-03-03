package com.example.mihai.bgssimulator.RealmClasses;

import io.realm.RealmObject;

/**
 * Created by silviu on 02.03.2017.
 */

public class LocationItem extends RealmObject {
    private long timeStamp;
    private double latitude;
    private double longitude;
    private double altitude;
    private double accuracy;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public LocationItem() {
    }

    public LocationItem(long _timeStamp, double _latitude, double _longitude, double _altitude, double _accuracy) {
        this.timeStamp = _timeStamp;
        this.latitude = _latitude;
        this.longitude = _longitude;
        this.altitude = _altitude;
        this.accuracy = _accuracy;
    }
}
