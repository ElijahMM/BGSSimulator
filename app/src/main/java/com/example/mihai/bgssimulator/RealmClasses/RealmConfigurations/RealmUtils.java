package com.example.mihai.bgssimulator.RealmClasses.RealmConfigurations;

import com.example.mihai.bgssimulator.RealmClasses.ActivityRecognitionItem;
import com.example.mihai.bgssimulator.RealmClasses.BarometerItem;
import com.example.mihai.bgssimulator.RealmClasses.LocationItem;
import com.example.mihai.bgssimulator.RealmClasses.OrientationItem;
import com.example.mihai.bgssimulator.RealmClasses.StepItem;
import com.example.mihai.bgssimulator.RealmClasses.TestItem;

import io.realm.Realm;

/**
 * Created by silviu on 06.03.2017.
 */

public class RealmUtils {

    public static void deleteStepItems(Realm realm) {
        realm.where(StepItem.class).findAll().deleteAllFromRealm();
    }

    public static void deleteAcitivtyRecognitionItems(Realm realm) {
        realm.where(ActivityRecognitionItem.class).findAll().deleteAllFromRealm();
    }

    public static void deleteBarometerItems(Realm realm) {
        realm.where(BarometerItem.class).findAll().deleteAllFromRealm();
    }

    public static void deleteLocationItems(Realm realm) {
        realm.where(LocationItem.class).findAll().deleteAllFromRealm();
    }

    public static void deleteOrientationItems(Realm realm) {
        realm.where(OrientationItem.class).findAll().deleteAllFromRealm();
    }

    public static void deleteAllSensorData(Realm realm) {
        deleteAcitivtyRecognitionItems(realm);
        deleteBarometerItems(realm);
        deleteLocationItems(realm);
        deleteOrientationItems(realm);
        deleteStepItems(realm);
    }

    /**
     * delete test objects
     */
    public static void deleteTestItems(Realm realm) {
        realm.where(TestItem.class).findAll().deleteAllFromRealm();
    }
}
