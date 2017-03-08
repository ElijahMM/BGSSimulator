package com.example.mihai.bgssimulator.Simultor.GatherData;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.example.mihai.bgssimulator.RealmClasses.RealmModels.BarometerValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.OrientationValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels._BarometerValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels._OrientationValueModel;
import com.example.mihai.bgssimulator.Simultor.Timer.PreciseCountdown;
import com.hoan.dsensor_master.DProcessedSensor;
import com.hoan.dsensor_master.DSensorEvent;
import com.hoan.dsensor_master.DSensorManager;
import com.hoan.dsensor_master.interfaces.DProcessedEventListener;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by mihai on 02.03.2017.
 */

public class SensorHub implements SensorEventListener {

    private Context context;
    private Realm realm;


    //region Sensor declarations
    private SensorManager mSensorManager;
    private Sensor mStepCounterSensor;
    private Sensor mOrientationSensor;
    private Sensor resultSensor;
    private Sensor barometer;
    //endregion

    private PreciseCountdown orientationSensorTimer;


    List<_OrientationValueModel> auxOrientationList = new ArrayList<>();
    private float dSensorValue;

    List<_BarometerValueModel> auxBarometerList = new ArrayList<>();
    private Double barometerValue;

    /**
     * pdr variables
     */
    private int stepCounterSensorInitialValue = 0;


    public void initializeSensorTrack(Context context) {
        this.context = context;
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mStepCounterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        mOrientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        barometer = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

    }

    public void registerListeners() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mOrientationSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, barometer, SensorManager.SENSOR_DELAY_NORMAL);
        DSensorManager.startDProcessedSensor(context, DProcessedSensor.TYPE_3D_COMPASS,
                new DProcessedEventListener() {
                    @Override
                    public void onProcessedValueChanged(DSensorEvent dSensorEvent) {
                        dSensorValue = dSensorEvent.values[0];
                    }
                });
        writeOrientationToDB();
    }

    private void writeOrientationToDB() {
        orientationSensorTimer = new PreciseCountdown(60000, 100, 0) {
            @Override
            public void onTick(long timeLeft) {
                auxOrientationList.add(new _OrientationValueModel(System.currentTimeMillis(), dSensorValue));
                auxBarometerList.add(new _BarometerValueModel(System.currentTimeMillis(), barometerValue));
                Log.i("Time", String.valueOf(dSensorValue));
            }

            @Override
            public void onFinished() {
            }
        };
        orientationSensorTimer.start();
    }

    public void unregisterListeners() {
        try {
            mSensorManager.unregisterListener(this, mStepCounterSensor);
            mSensorManager.unregisterListener(this, mOrientationSensor);
            mSensorManager.unregisterListener(this, barometer);
            DSensorManager.stopDSensor();
            orientationSensorTimer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (_OrientationValueModel model : auxOrientationList) {
            realm.copyToRealm(new OrientationValueModel(model.getTimeStamp(), model.getOrientationValue()));
        }

        for (_BarometerValueModel model : auxBarometerList) {
            realm.copyToRealm(new BarometerValueModel(model.getTimeStamp(), model.getBarometerValue()));
        }

        Log.w("SensorHub", realm.where(OrientationValueModel.class).count() + " orientation values");
        realm.commitTransaction();
        realm = null;
    }


    private void processNumberOfSteps(int value) {
        stepCounterSensorInitialValue = value - stepCounterSensorInitialValue;
//        FileSensorLog.writeToStepCounterFile(stepCounterSensorInitialValue);
        stepCounterSensorInitialValue = value;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        resultSensor = event.sensor;
        float[] values = event.values;
        int value = -1;

        if (values.length > 0) {
            value = (int) values[0];
        }

        if (resultSensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            if (stepCounterSensorInitialValue == 0) {
                stepCounterSensorInitialValue = value;
            } else {
                processNumberOfSteps(value);
            }
        }

        if (resultSensor.getType() == Sensor.TYPE_PRESSURE) {
            // FileSensorLog.writeToAltitudeFile(value);
            barometerValue = Double.valueOf(values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
