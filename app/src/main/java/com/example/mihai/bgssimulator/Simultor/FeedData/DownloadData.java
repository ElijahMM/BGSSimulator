package com.example.mihai.bgssimulator.Simultor.FeedData;

import android.content.Context;

import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.BarometerValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.GpsValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.OrientationValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.PDRValueModel;
import com.example.mihai.bgssimulator.Simultor.FileSensorLog;
import com.example.mihai.bgssimulator.Utils.AbsValues;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by mihai on 03.03.2017.
 */

/**
 * get data sensor data from server and store them as queues
 */
public class DownloadData {
    private Context context;

    /**
     *the timestamp when the reading stated (the first line of every file will contain the same timestamp)
     */
    private Long firstTimeStamp;

    public DownloadData(Context context) {
        this.context = context;
    }

    public Queue<BarometerValueModel> getBarometerDatas() {
        Queue<BarometerValueModel> barometerValueQueue = new LinkedList<>();
        String[] allData = FileSensorLog.readFileContent(context, AbsValues.fileAltitudeName).split("\\$");
        firstTimeStamp = Long.valueOf(allData[0]);
        for (int i = 1; i < allData.length; i++) {
            String values[] = allData[i].split("\\|");
            BarometerValueModel barometer;
            barometer = new BarometerValueModel();
            barometer.setTimeStamp(values[0].trim());
            barometer.setBarometerValue(Double.valueOf(values[1]));
            barometerValueQueue.add(barometer);
        }
        return barometerValueQueue;
    }

    public Queue<OrientationValueModel> getOrientationDatas() {
        Queue<OrientationValueModel> orientationValueQueue = new LinkedList<>();
        String[] allData = FileSensorLog.readFileContent(context, AbsValues.fileOrientationName).split("\\$");
        firstTimeStamp = Long.valueOf(allData[0]);
        for (int i = 1; i < allData.length; i++) {
            String values[] = allData[i].split("\\|");
            OrientationValueModel orientationValueModel;
            orientationValueModel = new OrientationValueModel();
            orientationValueModel.setTimeStamp(values[0].trim());
            orientationValueModel.setOrientationValue(Double.valueOf(values[1]).floatValue());
            orientationValueQueue.add(orientationValueModel);
        }
        return orientationValueQueue;
    }

    public Queue<GpsValueModel> getGPSDatas() {
        Queue<GpsValueModel> gpsValueQueue = new LinkedList<>();
        String[] allData = FileSensorLog.readFileContent(context, AbsValues.fileGPSName).split("\\$");
        firstTimeStamp = Long.valueOf(allData[0]);
        for (int i = 1; i < allData.length; i++) {
            String values[] = allData[i].split("\\|");
            GpsValueModel gpsValueModel = new GpsValueModel();
            gpsValueModel.setTimeStamp(values[0].trim());
            gpsValueModel.getLocation().setLatitude(Double.valueOf(values[1]));
            gpsValueModel.getLocation().setLongitude(Double.valueOf(values[2]));
            gpsValueModel.getLocation().setAltitude(Double.valueOf(values[3]));
            gpsValueModel.getLocation().setAccuracy(Float.valueOf(values[4]));
            gpsValueQueue.add(gpsValueModel);
        }
        return gpsValueQueue;
    }

    public Queue<PDRValueModel> getPDRDatas() {
        Queue<PDRValueModel> pdrValueQueue = new LinkedList<>();
        String[] allData = FileSensorLog.readFileContent(context, AbsValues.fileStepName).split("\\$");
        firstTimeStamp = Long.valueOf(allData[0]);
        for (int i = 1; i < allData.length; i++) {
            String values[] = allData[i].split("\\|");
            PDRValueModel stepValueModel = new PDRValueModel();
            stepValueModel.setTimeStamp(values[0].trim());
            stepValueModel.setStep(Integer.parseInt(values[1].trim()));
            pdrValueQueue.add(stepValueModel);
        }
        return pdrValueQueue;
    }


    public Long getFirstTimeStamp() {
        return firstTimeStamp;
    }
}
