package com.ground0.realmtestenvironment;

import android.provider.Settings;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log("Getting realm instance");
        Realm realm = Realm.getDefaultInstance();
        log("Got realm instance count 1");

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SampleModel managedCopy = realm.createObject(SampleModel.class, 1);
                managedCopy.message = "Copy 1";
                managedCopy.timeStamp = System.currentTimeMillis();
            }
        });

        log("Getting second realm instance on same thread");
        Realm secondInstance = Realm.getDefaultInstance();
        log("Got another realm instance count 2");
        secondInstance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SampleModel managedCopy = realm.createObject(SampleModel.class, 2);
                managedCopy.message = "Copy 2";
                managedCopy.timeStamp = System.currentTimeMillis();
            }
        });
        log("Closing second realm instance");
        secondInstance.close();
        log("Closed count 1");

        log("Getting third realm instance on same thread");
        Realm thirdInstance = Realm.getDefaultInstance();
        log("Got another realm instance count 2");
        thirdInstance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SampleModel managedCopy = realm.createObject(SampleModel.class, 3);
                managedCopy.message = "Copy 3";
                managedCopy.timeStamp = System.currentTimeMillis();
            }
        });
        log("Closing third realm instance");
        thirdInstance.close();
        log("Closed count 1");

        log("Closing first instance");
        realm.close();
        log("Closed first instance count 0");

        log("Getting fourth realm instance on same thread");
        Realm fourth = Realm.getDefaultInstance();
        log("Got another realm instance count 1");
        fourth.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SampleModel managedCopy = realm.createObject(SampleModel.class, 4);
                managedCopy.message = "Copy 4";
                managedCopy.timeStamp = System.currentTimeMillis();
            }
        });
        log("Closing fourth realm instance");
        fourth.close();
        log("Closed count 0");


    }


    private void log(String message) {
        Log.d(getClass().getName(), System.currentTimeMillis() + " " + message);
    }


}
