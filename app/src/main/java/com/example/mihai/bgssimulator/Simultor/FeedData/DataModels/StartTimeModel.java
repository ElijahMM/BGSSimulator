package com.example.mihai.bgssimulator.Simultor.FeedData.DataModels;

import io.realm.RealmObject;

/**
 * Created by silviu on 06.03.2017.
 */

public class StartTimeModel extends RealmObject {
    private Long timeStamp = 0l;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public StartTimeModel() {
    }

    public StartTimeModel(long _timeStamp) {
        this.timeStamp = _timeStamp;
    }
}
