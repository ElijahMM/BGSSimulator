package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

import io.realm.RealmObject;

/**
 * Created by mihai on 24.02.2017.
 */

public class BarometerValueModel extends RealmObject {

    Long timeStamp = 0l;
    Double barometerValue = 0d;


    public BarometerValueModel() {
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
