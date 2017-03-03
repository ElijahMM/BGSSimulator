package com.example.mihai.bgssimulator.RealmClasses;

import io.realm.RealmObject;

/**
 * Created by silviu on 02.03.2017.
 */

public class StepItem extends RealmObject {
    private long timeStamp;
    private int stepNumber;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }

    public StepItem() {
    }

    public StepItem(long _timeStamp, int _stepNumber) {
        this.timeStamp = _timeStamp;
        this.stepNumber = _stepNumber;
    }
}
