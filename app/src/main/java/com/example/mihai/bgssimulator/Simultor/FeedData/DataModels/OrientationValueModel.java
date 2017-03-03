package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

/**
 * Created by emi on 23.02.2017.
 */

public class OrientationValueModel {

    private Long timeStamp;
    private Float orientationValue;

    public OrientationValueModel() {
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
