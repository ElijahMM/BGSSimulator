package com.example.mihai.bgssimulator.Simultor.FeedData;

import android.content.Context;
import android.location.Location;

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
public class DownloadFileData implements DownloadData {
    private Context context;

    /**
     * the timestamp when the reading stated (the first line of every file will contain the same timestamp)
     */
    private Long firstTimeStamp;

    public DownloadFileData(Context context) {
        this.context = context;
    }

    @Override
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

    @Override
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

    @Override
    public Queue<GpsValueModel> getGPSDatas() {
        Queue<GpsValueModel> gpsValueQueue = new LinkedList<>();
        String[] allData = FileSensorLog.readFileContent(context, AbsValues.fileGPSName).split("\\$");
        firstTimeStamp = Long.valueOf(allData[0]);
        for (int i = 1; i < allData.length; i++) {
            String values[] = allData[i].split("\\|");
            GpsValueModel gpsValueModel = new GpsValueModel();
            Location location = new Location("");
            location.setLatitude(Double.valueOf(values[1]));
            location.setLongitude(Double.valueOf(values[2]));
            location.setAltitude(Double.valueOf(values[3]));
            location.setAccuracy(Float.valueOf(values[4]));

            gpsValueModel.setLocation(location);
            gpsValueModel.setTimeStamp(values[0].trim());
            gpsValueQueue.add(gpsValueModel);
        }
        return gpsValueQueue;
    }

    @Override
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

    @Override
    public Long getFirstTimeStamp() {
        return firstTimeStamp;
    }
}
