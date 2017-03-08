package com.example.mihai.bgssimulator.Simultor.FeedData;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.example.mihai.bgssimulator.RealmClasses.RealmModels.BarometerValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.GpsValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.OrientationValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.PDRValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels._BarometerValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels._OrientationValueModel;
import com.example.mihai.bgssimulator.Simultor.Timer.PreciseCountdown;
import com.example.mihai.bgssimulator.Utils.Settings;
import com.example.mihai.bgssimulator.Utils.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mihai on 03.03.2017.
 */

public class SensorManagement {

    Context context;
    SensorResult sensorResult;
    PreciseCountdown countdown;
    /**
     * retrieve data from files
     */
    private DownloadData downloadData;

    /**
     * each queue will store the recorded data from server, and as we proceed with data precessing, elements  will be removed.
     * In the end we will have an empty queue, which means the simulation has stopped
     */
    private List<BarometerValueModel> barometerValueQueue;
    private List<OrientationValueModel> orientationValueQueue;
    private List<PDRValueModel> pdrValueQueue;
    private List<GpsValueModel> gpsValueQueue;


    public SensorManagement(Context context, SensorResult sensorResult) {
        this.context = context;
        this.sensorResult = sensorResult;

        if (Settings.siDBSource) {
            downloadData = new DownloadDBData();
        } else {
            downloadData = new DownloadFileData(this.context);
        }
    }

    //region get data

    private void feedBarometer() {
        barometerValueQueue = downloadData.getBarometerDatas();
    }

    private void feedGps() {
        gpsValueQueue = downloadData.getGPSDatas();
    }

    private void feedPdr() {
        pdrValueQueue = downloadData.getPDRDatas();
    }

    private void feedOrientation() {
        orientationValueQueue = downloadData.getOrientationDatas();
    }
    //endregion

    public void registerFakeSensor() {
        feedPdr();
        feedGps();
        feedBarometer();
        feedOrientation();

    }

    public void stop() {
//        gpsValueQueue.clear();
//        barometerValueQueue.clear();
//        pdrValueQueue.clear();
//        orientationValueQueue.clear();
        countdown.stop();
    }


    //region orientation process
    public void startOrientation() {
        final List<_OrientationValueModel> aux = preProcessOrientationData();
        final long totalTime = Math.abs(orientationValueQueue.get(0).getTimeStamp() - Util.getLastElement(orientationValueQueue).getTimeStamp());
        Log.w("Time", String.valueOf(totalTime));
        this.countdown = new PreciseCountdown(totalTime, 100, 0) {
            @Override
            public void onTick(long timeLeft) {
                if (aux.get(0).getTimeStamp() == 0) {
                    sensorResult.gotOrientation(aux.get(0).getOrientationValue());
                    aux.remove(0);
                } else if (totalTime - timeLeft >= aux.get(0).getTimeStamp()) {
                    sensorResult.gotOrientation(aux.get(0).getOrientationValue());
                    aux.remove(0);
                }
            }

            @Override
            public void onFinished() {
                Log.w("Time", "Done");
            }
        };
        countdown.start();

    }

    private List<_OrientationValueModel> preProcessOrientationData() {
        List<_OrientationValueModel> aux = new ArrayList<>();
        for (int i = 0; i < orientationValueQueue.size(); i++) {
            _OrientationValueModel a = new _OrientationValueModel();
            a.setTimeStamp(String.valueOf(orientationValueQueue.get(i).getTimeStamp()));
            a.setOrientationValue(orientationValueQueue.get(i).getOrientationValue());
            aux.add(a);
        }
        for (int i = 1; i < aux.size(); i++) {
            aux.get(i).setTimeStamp(String.valueOf(Math.abs(orientationValueQueue.get(i - 1).getTimeStamp() - orientationValueQueue.get(i).getTimeStamp())));
        }
        aux.get(0).setTimeStamp("0");
        return aux;
    }
    //endregion


    // region barometer process
    public void startBarometer() {
        final List<_BarometerValueModel> aux = preProcessBarometerData();
        final long totalTime = Math.abs(barometerValueQueue.get(0).getTimeStamp() - Util.getLastElement(barometerValueQueue).getTimeStamp());
        Log.w("Time", String.valueOf(totalTime));
        this.countdown = new PreciseCountdown(totalTime, 100, 0) {
            @Override
            public void onTick(long timeLeft) {
                if (aux.get(0).getBarometerValue() != null) {
                    if (aux.get(0).getTimeStamp() == 0) {
                        sensorResult.gotBarometer(aux.get(0).getBarometerValue());
                        aux.remove(0);
                    } else if (totalTime - timeLeft >= aux.get(0).getTimeStamp()) {
                        sensorResult.gotBarometer(aux.get(0).getBarometerValue());
                        aux.remove(0);
                    }
                } else {
                    aux.remove(0);
                }
            }

            @Override
            public void onFinished() {
                Log.w("Time", "Done");
            }
        };
        countdown.start();

    }

    private List<_BarometerValueModel> preProcessBarometerData() {
        List<_BarometerValueModel> aux = new ArrayList<>();
        for (int i = 0; i < orientationValueQueue.size(); i++) {
            _BarometerValueModel a = new _BarometerValueModel();
            a.setTimeStamp(String.valueOf(barometerValueQueue.get(i).getTimeStamp()));
            a.setBarometerValue(barometerValueQueue.get(i).getBarometerValue());
            aux.add(a);
        }
        for (int i = 1; i < aux.size(); i++) {
            aux.get(i).setTimeStamp(String.valueOf(Math.abs(barometerValueQueue.get(i - 1).getTimeStamp() - barometerValueQueue.get(i).getTimeStamp())));
        }
        aux.get(0).setTimeStamp("0");
        return aux;
    }
    //endregion


    public interface SensorResult {
        void gotBarometer(Double barometerValue);

        void gotLocation(Location location);

        void gotStepResult(Integer stepResult);

        void gotOrientation(Float orientationValue);
    }

}
