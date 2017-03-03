package com.example.mihai.bgssimulator.RealmClasses.RealmConfigurations;

import io.realm.RealmConfiguration;

/**
 * Created by silviu on 03.03.2017.
 */

public class RealmConfig {
    RealmConfiguration mainConfig;

    public RealmConfiguration getMainConfig() {
        return mainConfig;
    }

    public void setMainConfig(RealmConfiguration mainConfig) {
        this.mainConfig = mainConfig;
    }

    public RealmConfig() {
        mainConfig = new RealmConfiguration.Builder()
                .name("main_realm.realm")
                .schemaVersion(1)
                .build();
    }

}
