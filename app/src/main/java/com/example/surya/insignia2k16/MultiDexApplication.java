package com.example.surya.insignia2k16;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by surya on 23-08-2016.
 */
public class MultiDexApplication extends Application {

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

    }
}
