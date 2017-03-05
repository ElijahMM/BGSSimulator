package com.example.mihai.bgssimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mihai.bgssimulator.Simulator.GatherData.FileSensorLog;
import com.example.mihai.bgssimulator.Simulator.GatherData.GPSLocation;
import com.example.mihai.bgssimulator.Simulator.GatherData.SensorHub;
import com.example.mihai.bgssimulator.Utils.AbsValues;

import static android.R.attr.start;
import static android.R.attr.theme;

import com.example.mihai.bgssimulator.RealmClasses.RealmConfigurations.RealmConfig;
import com.example.mihai.bgssimulator.RealmClasses.TestItem;

import io.realm.Realm;
import io.realm.RealmObject;

public class MainActivity extends AppCompatActivity {

    private Button startDataTrack;
    private GPSLocation gpsLocation;
    private SensorHub sensorHub;

    private String gatherState = AbsValues.STOP_COLLECT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FileSensorLog.createFiles(this);

        startDataTrack = (Button) findViewById(R.id.startGatherBtn);

        gpsLocation = new GPSLocation();
        sensorHub = new SensorHub();

        sensorHub.initializeSensorTrack(this);
        gpsLocation.connectGoogleClient(this);

        startDataTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gatherState == AbsValues.STOP_COLLECT) {

                    sensorHub.registerListeners();
                    gpsLocation.startLocationUpdates();

                    gatherState = AbsValues.COLLECT_DATA;
                    startDataTrack.setText("Stop gather Data");
                } else {
                    gatherState = AbsValues.STOP_COLLECT;
                    startDataTrack.setText("Start gather Data");

                    sensorHub.unregisterListeners();
                    gpsLocation.stopLocationUpdate();
                }
            }
        });
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
