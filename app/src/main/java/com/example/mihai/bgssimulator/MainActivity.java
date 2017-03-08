package com.example.mihai.bgssimulator;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mihai.bgssimulator.RealmClasses.RealmConfigurations.RealmUtils;
import com.example.mihai.bgssimulator.Simultor.FeedData.SensorManagement;
import com.example.mihai.bgssimulator.Simultor.FileSensorLog;
import com.example.mihai.bgssimulator.Simultor.GatherData.GPSLocation;
import com.example.mihai.bgssimulator.Simultor.GatherData.SensorHub;
import com.example.mihai.bgssimulator.Utils.AbsValues;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements SensorManagement.SensorResult {


    private Button startDataTrack, startFeedData, clearDbBtn;
    private GPSLocation gpsLocation;
    private SensorHub sensorHub;

    private String gatherState = AbsValues.STOP_COLLECT;
    private String feedState = AbsValues.STOP_FEED;
    private SensorManagement sensorManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileSensorLog.createFiles(this);
        sensorManagement = new SensorManagement(this, this);
        startDataTrack = (Button) findViewById(R.id.startGatherBtn);
        startFeedData = (Button) findViewById(R.id.startFeedDataBtn);
        clearDbBtn = (Button) findViewById(R.id.clearDbBtn);

        gpsLocation = new GPSLocation();
        sensorHub = new SensorHub();

        sensorHub.initializeSensorTrack(this);
        gpsLocation.connectGoogleClient(this);

        startDataTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTrack();

            }
        });

        startFeedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startFeed();
            }
        });


        clearDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealmUtils.deleteAllSensorData(Realm.getDefaultInstance());
            }
        });
    }

    private void startFeed() {
        if (feedState == AbsValues.STOP_FEED) {
            sensorManagement.registerFakeSensor();
            startFeedData.setText("Stop Feeding");
            start();
            feedState = AbsValues.FEED_DATA;
        } else if (feedState == AbsValues.FEED_DATA) {
            startFeedData.setText("Start Feeding");
            sensorManagement.stop();
        }
    }

    private void startTrack() {
        if (gatherState == AbsValues.STOP_COLLECT) {
            FileSensorLog.writeFistTimeStamp();
            sensorHub.registerListeners();
            gpsLocation.startLocationUpdates();

            gatherState = AbsValues.COLLECT_DATA;
            startDataTrack.setText("Stop gather Data");
        } else {
            gatherState = AbsValues.STOP_COLLECT;
            startDataTrack.setText("Start gather Data");

            sensorHub.unregisterListeners();
            gpsLocation.stopLocationUpdate();

            FileSensorLog.closeFiles();
        }
    }


    public void start() {
        sensorManagement.startOrientation();
        sensorManagement.startBarometer();
    }


    @Override
    public void gotBarometer(Double barometerValue) {
        Log.i("Barometer:", barometerValue + "");
    }

    @Override
    public void gotLocation(Location location) {
        Log.i("GPS:", location + "");
    }

    @Override
    public void gotStepResult(Integer stepResult) {
        Log.i("Step:", stepResult + "");
    }

    @Override
    public void gotOrientation(Float orientationValue) {
        Log.i("Orientation:", orientationValue + "");
    }

}
