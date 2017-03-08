package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

/**
 * Created by mihai on 24.02.2017.
 */

public class _BarometerValueModel  {

    Long timeStamp = 0l;
    Double barometerValue = 0d;


    public _BarometerValueModel() {
    }

    public _BarometerValueModel(Long _timeStamp, Double _barometerValue) {
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
