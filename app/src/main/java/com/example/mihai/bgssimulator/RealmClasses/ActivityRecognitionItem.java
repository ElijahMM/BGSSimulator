package com.example.mihai.bgssimulator.RealmClasses;

import io.realm.RealmObject;

/**
 * Created by silviu on 02.03.2017.
 */

public class ActivityRecognitionItem extends RealmObject {
    private long timeStamp;
    private String activityType;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getActivitytype() {
        return activityType;
    }

    public void setActivitytype(String activitytype) {
        this.activityType = activitytype;
    }

    public ActivityRecognitionItem() {
    }

    public ActivityRecognitionItem(long _timeStamp, String _activityType) {
        this.timeStamp = _timeStamp;
        this.activityType = _activityType;
    }
}
