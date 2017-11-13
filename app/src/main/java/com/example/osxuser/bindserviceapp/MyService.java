package com.example.osxuser.bindserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

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

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        return START_REDELIVER_INTENT;
    }

    public int getNumber() {
        return number++;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "MyService onDestroy().", Toast.LENGTH_SHORT).show();
    }
}
