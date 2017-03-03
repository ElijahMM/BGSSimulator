package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

import android.content.Intent;

/**
 * Created by emi on 23.02.2017.
 */

public class PDRValueModel {

    private Long timeStamp;
    private Integer step;

    public PDRValueModel(String timeStamp, int step) {
    }

    public PDRValueModel() {
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = Long.valueOf(timeStamp);
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
