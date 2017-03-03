package com.example.mihai.bgssimulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mihai.bgssimulator.RealmClasses.RealmConfigurations.RealmConfig;
import com.example.mihai.bgssimulator.RealmClasses.TestItem;

import io.realm.Realm;
import io.realm.RealmObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
