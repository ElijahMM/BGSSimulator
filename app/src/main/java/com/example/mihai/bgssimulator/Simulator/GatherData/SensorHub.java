package com.example.mihai.bgssimulator.Simulator.GatherData;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.hoan.dsensor_master.DProcessedSensor;
import com.hoan.dsensor_master.DSensorEvent;
import com.hoan.dsensor_master.DSensorManager;
import com.hoan.dsensor_master.interfaces.DProcessedEventListener;

/**
 * Created by mihai on 02.03.2017.
 */

public class SensorHub implements SensorEventListener {

    private Context context;


    private SensorManager mSensorManager;

    private Sensor mStepCounterSensor;

    private Sensor mOrientationSensor;

    private Sensor resultSensor;

    private Sensor barometer;


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
        mSensorManager.registerListener(this, mStepCounterSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, mOrientationSensor, SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, barometer, SensorManager.SENSOR_DELAY_NORMAL);
        DSensorManager.startDProcessedSensor(context, DProcessedSensor.TYPE_3D_COMPASS,
                new DProcessedEventListener() {
                    @Override
                    public void onProcessedValueChanged(DSensorEvent dSensorEvent) {
                        FileSensorLog.writeToOrientationFile(dSensorEvent.values[0]);
                    }
                });
    }

    public void unregisterListeners() {
        try {
            mSensorManager.unregisterListener(this, mStepCounterSensor);
            mSensorManager.unregisterListener(this, mOrientationSensor);
            mSensorManager.unregisterListener(this, barometer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void processNumberOfSteps(int value) {
        stepCounterSensorInitialValue = value - stepCounterSensorInitialValue;
        FileSensorLog.writeToStepCounterFile(stepCounterSensorInitialValue);
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
            FileSensorLog.writeToAltitudeFile(value);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public static abstract class SensorResults {
        public abstract void gotSensorsResults(int stepsResult);

        public abstract void gotBarometerResults(double altitudeInMeters);
    }
}
