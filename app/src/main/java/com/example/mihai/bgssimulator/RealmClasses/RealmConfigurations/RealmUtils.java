package com.example.mihai.bgssimulator.RealmClasses.RealmConfigurations;

import com.example.mihai.bgssimulator.RealmClasses.RealmModels.BarometerValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.GpsValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.OrientationValueModel;
import com.example.mihai.bgssimulator.RealmClasses.RealmModels.PDRValueModel;

import io.realm.Realm;

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
        realm.beginTransaction();
        deleteBarometerValues(realm);
        deleteGPSValues(realm);
        deleteOrientationValues(realm);
        deletePDRValues(realm);


        realm.commitTransaction();

    }
}
