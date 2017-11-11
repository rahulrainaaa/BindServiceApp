package com.example.osxuser.bindserviceapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    private Messenger mService = null;
    private boolean mBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        startService(new Intent(this, MessengerService.class));
        startService(new Intent(this, MyService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        bindService(new Intent(this, MessengerService.class), mConnection, Context.BIND_AUTO_CREATE);

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        }

    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Toast.makeText(Activity2.this, "Messenger Service Connected.", Toast.LENGTH_SHORT).show();
            mService = new Messenger(iBinder);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            Log.d("aaaaaaaaa", "Messenger Service Disconnected.");
            Toast.makeText(Activity2.this, "Messenger Service Disconnected.", Toast.LENGTH_SHORT).show();
            mBound = true;
            mService = null;
        }
    };

    @Override
    public void onBackPressed() {

        if (!mBound) return;

        Message msg = Message.obtain(null, 1, 0, 0);
        try {
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
