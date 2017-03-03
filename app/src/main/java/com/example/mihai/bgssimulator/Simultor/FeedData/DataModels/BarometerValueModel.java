package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

/**
 * Created by mihai on 24.02.2017.
 */

public class BarometerValueModel {

    Long timeStamp;
    Double barometerValue;


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
