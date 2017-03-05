package com.example.mihai.bgssimulator;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.mihai.bgssimulator.RealmClasses.TestItem;
import com.example.mihai.bgssimulator.Simultor.FeedData.DownloadData;
import com.example.mihai.bgssimulator.Simultor.FeedData.SensorManagement;
import com.example.mihai.bgssimulator.Simultor.FileSensorLog;
import com.example.mihai.bgssimulator.Simultor.GatherData.GPSLocation;
import com.example.mihai.bgssimulator.Simultor.GatherData.SensorHub;
import com.example.mihai.bgssimulator.Utils.AbsValues;
import com.example.mihai.bgssimulator.RealmClasses.RealmConfigurations.RealmConfig;
import com.example.mihai.bgssimulator.RealmClasses.TestItem;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements SensorManagement.SensorResult {


    private Button startDataTrack, startFeedData;
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

        gpsLocation = new GPSLocation();
        sensorHub = new SensorHub();

        sensorHub.initializeSensorTrack(this);
        gpsLocation.connectGoogleClient(this);

        startDataTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });

        startFeedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
    }


    public void start() {
//        sensorManagement.startBarometer();
        sensorManagement.startGps();
//        sensorManagement.startOrientation();
//        sensorManagement.startPDR();
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
        //testDB();
    }

    public void testDB() {
        Realm realm = Realm.getDefaultInstance();
        final TestItem testItem1 = new TestItem("a value", 444, true);
        final TestItem testItem2 = new TestItem("another string", 445, false);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(testItem1);
                realm.copyToRealm(testItem2);
            }
        });
        realm.close();
    }
}
