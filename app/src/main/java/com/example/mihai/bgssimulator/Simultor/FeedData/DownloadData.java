package com.example.mihai.bgssimulator.Simultor.FeedData;

import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.BarometerValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.GpsValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.OrientationValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.PDRValueModel;
import com.example.mihai.bgssimulator.Simultor.FileSensorLog;
import com.example.mihai.bgssimulator.Utils.AbsValues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mihai on 06.03.2017.
 */


public interface DownloadData {
    Queue<BarometerValueModel> getBarometerDatas();


    Queue<OrientationValueModel> getOrientationDatas();

    Queue<GpsValueModel> getGPSDatas();

    Queue<PDRValueModel> getPDRDatas();

    Long getFirstTimeStamp();

}
