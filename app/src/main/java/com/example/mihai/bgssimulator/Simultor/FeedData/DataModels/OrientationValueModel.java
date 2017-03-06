package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

import io.realm.RealmObject;

/**
 * Created by emi on 23.02.2017.
 */

public class OrientationValueModel extends RealmObject {

    private Long timeStamp = 0l;
    private Float orientationValue =0f;

    public OrientationValueModel() {
    }

    public OrientationValueModel(Long timeStamp, Float orientationValue) {
        this.timeStamp = timeStamp;
        this.orientationValue = orientationValue;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = Long.valueOf(timeStamp);
    }

    public Float getOrientationValue() {
        return orientationValue;
    }

    public void setOrientationValue(Float orientationValue) {
        this.orientationValue = orientationValue;
    }
}
