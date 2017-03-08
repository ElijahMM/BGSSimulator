package com.example.mihai.bgssimulator.RealmClasses.RealmModels;

import io.realm.RealmObject;

/**
 * Created by mihai on 24.02.2017.
 */

public class BarometerValueModel extends RealmObject {

    Long timeStamp = 0l;
    Double barometerValue = 0d;


    public BarometerValueModel() {
    }

    public BarometerValueModel(Long _timeStamp, Double _barometerValue) {
        this.timeStamp = _timeStamp;
        this.barometerValue = _barometerValue;
    }

    public Long getTimeStamp() {

        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = Long.valueOf(timeStamp);
    }

    public Double getBarometerValue() {
        return barometerValue;
    }

    public void setBarometerValue(Double barometerValue) {
        this.barometerValue = barometerValue;
    }
}
