package com.example.osxuser.bindserviceapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean mBound = false;
    private MyService mService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        startService(new Intent(getApplicationContext(), MyService.class));
//        Intent intent = new Intent(this, MyService.class);
//        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mBound) {

            unbindService(mConnection);
            mBound = false;
        }
    }

    @Override
    public void onBackPressed() {

        if (mBound) {
            unbindService(mConnection);
            mBound = false;
        } else {
            Intent intent = new Intent(this, MyService.class);
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        }

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.button:
                if (mBound) {
                    Toast.makeText(getApplicationContext(), "Number: " + mService.getNumber(), Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            MyService.LocalBinder binder = (MyService.LocalBinder) iBinder;
            mService = binder.getService();
            mBound = true;
            Toast.makeText(mService, "onServiceConnected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

            mBound = false;

        }
    };
}
