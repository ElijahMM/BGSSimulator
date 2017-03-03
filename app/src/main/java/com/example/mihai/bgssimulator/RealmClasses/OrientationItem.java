package com.example.mihai.bgssimulator.RealmClasses;

import io.realm.RealmObject;

/**
 * Created by silviu on 02.03.2017.
 */

public class OrientationItem extends RealmObject {
    private long timeStamp;
    private double angle;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public OrientationItem() {

    }

    public OrientationItem(long _timeStamp, double _angle) {
        this.timeStamp = _timeStamp;
        this.angle = _angle;
    }
}
