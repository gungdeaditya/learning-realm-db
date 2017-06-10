package com.projects.gungde.learningrealm.app;

import android.app.Application;

import com.projects.gungde.learningrealm.realm.module.SimpleRealmModule;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by gungdeaditya on 10/06/17.
 */

public class RealmApplication extends Application {

    private static RealmApplication instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).setModules(new SimpleRealmModule()).build();
        Realm.setDefaultConfiguration(config);
    }

    public static RealmApplication getInstance() {
        return instance;
    }
}

