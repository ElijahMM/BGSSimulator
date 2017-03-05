package com.example.mihai.bgssimulator.Simultor.FeedData;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;

import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.BarometerValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.GpsValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.OrientationValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.PDRValueModel;

import java.util.Queue;

/**
 * Created by mihai on 03.03.2017.
 */

public class SensorManagement {

    Context context;
    SensorResult sensorResult;

    /**
     * retrieve data from files
     */
    private DownloadData downloadData;

    /**
     * each queue will store the recorded data from server, and as we proceed with data precessing, elements  will be removed.
     * In the end we will have an empty queue, which means the simulation has stopped
     */
    private Queue<BarometerValueModel> barometerValueQueue;
    private Queue<OrientationValueModel> orientationValueQueue;
    private Queue<PDRValueModel> pdrValueQueue;
    private Queue<GpsValueModel> gpsValueQueue;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private Long startTimeStamp, currentTimeStamp;

    public SensorManagement(Context context, SensorResult sensorResult) {
        this.context = context;
        this.sensorResult = sensorResult;
        downloadData = new DownloadData(this.context);


    }


    //region get data
    private void feedBarometer() {
        barometerValueQueue = downloadData.getBarometerDatas();
        startTimeStamp = downloadData.getFirstTimeStamp();
    }

    private void feedGps() {
        gpsValueQueue = downloadData.getGPSDatas();
        startTimeStamp = downloadData.getFirstTimeStamp();
    }

    private void feedPdr() {
        pdrValueQueue = downloadData.getPDRDatas();
        startTimeStamp = downloadData.getFirstTimeStamp();
    }

    private void feedOrientation() {
        orientationValueQueue = downloadData.getOrientationDatas();
        startTimeStamp = downloadData.getFirstTimeStamp();
    }
    //endregion


    public void registerFakeSensor() {
        feedPdr();
        feedGps();
        feedBarometer();
        feedOrientation();
    }


    public void stop() {
        gpsValueQueue.clear();
        barometerValueQueue.clear();
        pdrValueQueue.clear();
        orientationValueQueue.clear();
    }


    //region start feed data
    public void startBarometer() {
        new Runnable() {
            @Override
            public void run() {
                if (barometerValueQueue.isEmpty()) {
                    mHandler.removeCallbacksAndMessages(null); // stop runnable
                } else {
                    currentTimeStamp = barometerValueQueue.peek().getTimeStamp();
                    mHandler.postDelayed(this, Math.abs(currentTimeStamp - startTimeStamp)); // delay with first timeStamp - second Timestamp
                    startTimeStamp = currentTimeStamp;
                    sensorResult.gotBarometer(barometerValueQueue.poll().getBarometerValue());
                }
            }
        }.run();


    }


    public void startGps() {
        new Runnable() {
            @Override
            public void run() {
                if (gpsValueQueue.isEmpty()) {
                    mHandler.removeCallbacksAndMessages(null); // stop runnable
                } else {
                    currentTimeStamp = gpsValueQueue.peek().getTimeStamp();
                    mHandler.postDelayed(this, Math.abs(currentTimeStamp - startTimeStamp)); // delay with first timeStamp - second Timestamp
                    startTimeStamp = currentTimeStamp;
                    sensorResult.gotLocation(gpsValueQueue.poll().getLocation());
                }
            }
        }.run();


    }

    public void startOrientation() {
        new Runnable() {
            @Override
            public void run() {
                if (orientationValueQueue.isEmpty()) {
                    mHandler.removeCallbacksAndMessages(null); // stop runnable
                } else {
                    currentTimeStamp = orientationValueQueue.peek().getTimeStamp();
                    mHandler.postDelayed(this, Math.abs(currentTimeStamp - startTimeStamp)); // delay with first timeStamp - second Timestamp
                    startTimeStamp = currentTimeStamp;
                    sensorResult.gotOrientation(orientationValueQueue.poll().getOrientationValue());
                }
            }
        }.run();


    }

    public void startPDR() {
        new Runnable() {
            @Override
            public void run() {
                if (pdrValueQueue.isEmpty()) {
                    mHandler.removeCallbacksAndMessages(null); // stop runnable
                } else {
                    currentTimeStamp = pdrValueQueue.peek().getTimeStamp();
                    mHandler.postDelayed(this, Math.abs(currentTimeStamp - startTimeStamp)); // delay with first timeStamp - second Timestamp
                    startTimeStamp = currentTimeStamp;
                    sensorResult.gotStepResult(pdrValueQueue.poll().getStep());
                }
            }
        }.run();
    }
    //endregion


    public interface SensorResult {
        void gotBarometer(Double barometerValue);

        void gotLocation(Location location);

        void gotStepResult(Integer stepResult);

        void gotOrientation(Float orientationValue);
    }

}
