package com.example.osxuser.bindserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    public int number = 0;

    private final IBinder mBinder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    public class LocalBinder extends Binder {

        MyService getService() {

            return MyService.this;
        }

    }

    public int getNumber() {
        return number++;
    }

}
