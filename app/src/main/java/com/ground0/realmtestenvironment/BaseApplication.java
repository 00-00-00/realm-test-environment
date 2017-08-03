package com.ground0.realmtestenvironment;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;

/**
 * Created by flohDev on 28/07/17.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(this.getPackageName(), "onCreate: Initialising Realm");
        Realm.init(this);
    }
}
