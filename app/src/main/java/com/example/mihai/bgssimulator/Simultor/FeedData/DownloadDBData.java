package com.example.mihai.bgssimulator.Simultor.FeedData;

import android.content.Context;

import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.BarometerValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.GpsValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.OrientationValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.PDRValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.StartTimeModel;

import java.util.LinkedList;
import java.util.Queue;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by mihai on 06.03.2017.
 */

public class DownloadDBData implements DownloadData {

    public DownloadDBData() {
    }

    @Override
    public Queue<BarometerValueModel> getBarometerDatas() {
        Queue<BarometerValueModel> barometerValueQueue = new LinkedList<>();
        RealmResults<BarometerValueModel> barometerQueryResult = Realm.getDefaultInstance().where(BarometerValueModel.class).findAll();
        for (int i = 0; i < barometerQueryResult.size(); i++) {
            barometerValueQueue.add(barometerQueryResult.get(i));
        }
        return barometerValueQueue;
    }

    @Override
    public Queue<OrientationValueModel> getOrientationDatas() {
        Queue<OrientationValueModel> orientationValueQueue = new LinkedList<>();
        RealmResults<OrientationValueModel> orientationQueryResult = Realm.getDefaultInstance().where(OrientationValueModel.class).findAll();
        for (int i = 0; i < orientationQueryResult.size(); i++) {
            orientationValueQueue.add(orientationQueryResult.get(i));
        }
        return orientationValueQueue;
    }

    @Override
    public Queue<GpsValueModel> getGPSDatas() {
        Queue<GpsValueModel> gpsValueQueue = new LinkedList<>();
        RealmResults<GpsValueModel> gpsQueryResult = Realm.getDefaultInstance().where(GpsValueModel.class).findAll();
        for (int i = 0; i < gpsQueryResult.size(); i++) {
            gpsValueQueue.add(gpsQueryResult.get(i));
        }
        return gpsValueQueue;
    }

    @Override
    public Queue<PDRValueModel> getPDRDatas() {
        Queue<PDRValueModel> pdrValueQueue = new LinkedList<>();
        RealmResults<PDRValueModel> pdrQueryResult = Realm.getDefaultInstance().where(PDRValueModel.class).findAll();
        for (int i = 0; i < pdrQueryResult.size(); i++) {
            pdrValueQueue.add(pdrQueryResult.get(i));
        }
        return pdrValueQueue;
    }

    @Override
    public Long getFirstTimeStamp() {
        RealmResults<StartTimeModel> realmResults = Realm.getDefaultInstance().where(StartTimeModel.class).findAll();
        return realmResults.get(0).getTimeStamp();
    }
}
