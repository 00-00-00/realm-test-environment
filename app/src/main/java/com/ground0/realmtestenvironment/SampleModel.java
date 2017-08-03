package com.ground0.realmtestenvironment;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by flohDev on 28/07/17.
 */

public class SampleModel extends RealmObject {

    @PrimaryKey
    int id;

    String message;

    long timeStamp;
}
