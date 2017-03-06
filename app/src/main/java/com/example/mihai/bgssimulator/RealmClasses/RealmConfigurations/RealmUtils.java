package com.example.mihai.bgssimulator.RealmClasses.RealmConfigurations;

import com.example.mihai.bgssimulator.RealmClasses.ActivityRecognitionItem;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.BarometerValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.GpsValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.OrientationValueModel;
import com.example.mihai.bgssimulator.Simultor.FeedData.DataModels.PDRValueModel;

import io.realm.Realm;

import static java.lang.StrictMath.E;

/**
 * Created by silviu on 06.03.2017.
 */

public class RealmUtils {

    public static void deleteBarometerValues(Realm realm) {
        realm.where(BarometerValueModel.class).findAll().deleteAllFromRealm();
    }

    public static void deleteGPSValues(Realm realm) {
        realm.where(GpsValueModel.class).findAll().deleteAllFromRealm();
    }

    public static void deleteOrientationValues(Realm realm) {
        realm.where(OrientationValueModel.class).findAll().deleteAllFromRealm();
    }

    public static void deletePDRValues(Realm realm) {
        realm.where(PDRValueModel.class).findAll().deleteAllFromRealm();
    }

    public static void deleteAllSensorData(Realm realm) {
        deleteBarometerValues(realm);
        deleteGPSValues(realm);
        deleteOrientationValues(realm);
        deletePDRValues(realm);
    }

    /**
     * delete test objects
     */
    public static void deleteTestItems(Realm realm) {
        realm.where(TestItem.class).findAll().deleteAllFromRealm();
    }
}
