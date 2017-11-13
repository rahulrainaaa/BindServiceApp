package com.example.osxuser.bindserviceapp;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;


public class MyIntentService extends IntentService {

//    private final IBinder mBinder = new MyService.LocalBinder();

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


    }

}
