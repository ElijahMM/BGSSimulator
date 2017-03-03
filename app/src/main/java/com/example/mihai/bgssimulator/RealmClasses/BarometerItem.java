package com.example.mihai.bgssimulator.RealmClasses;

import io.realm.RealmObject;

/**
 * Created by silviu on 02.03.2017.
 */

public class BarometerItem extends RealmObject {
    private long timeStamp;
    private double height;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public BarometerItem() {
    }

    public BarometerItem(long _timeStamp, double _height) {
        this.timeStamp = _timeStamp;
        this.height = _height;
    }
}
