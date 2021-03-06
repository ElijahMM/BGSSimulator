package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

import android.content.Intent;

import io.realm.RealmObject;

/**
 * Created by emi on 23.02.2017.
 */

public class PDRValueModel extends RealmObject {

    private Long timeStamp = 0l;
    private Integer step = 0;

    public PDRValueModel() {
    }

    public PDRValueModel(Long timeStamp, Integer step) {
        this.timeStamp = timeStamp;
        this.step = step;
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
