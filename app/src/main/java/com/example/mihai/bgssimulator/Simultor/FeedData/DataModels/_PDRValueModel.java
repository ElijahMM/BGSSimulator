package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

/**
 * Created by emi on 23.02.2017.
 */

public class _PDRValueModel  {

    private Long timeStamp = 0l;
    private Integer step = 0;

    public _PDRValueModel() {
    }

    public _PDRValueModel(Long timeStamp, Integer step) {
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
