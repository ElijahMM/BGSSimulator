package com.example.mihai.bgssimulator.Simultor.FeedData;

import com.example.mihai.bgssimulator.RealmClasses.RealmModels.BarometerValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.GpsValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.OrientationValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.PDRValueModel;

import java.util.List;

/**
 * Created by mihai on 06.03.2017.
 */


public interface DownloadData {
    List<BarometerValueModel> getBarometerDatas();


    List<OrientationValueModel> getOrientationDatas();

    List<GpsValueModel> getGPSDatas();

    List<PDRValueModel> getPDRDatas();

    Long getFirstTimeStamp();

}
