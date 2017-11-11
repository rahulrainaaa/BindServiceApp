package com.example.osxuser.bindserviceapp;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.widget.Toast;

public class MessengerService extends Service {


    @Override
    public IBinder onBind(Intent intent) {

        Toast.makeText(this, "Binding Service.", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }

    public Messenger mMessenger = new Messenger(new IncommingHandler());

    public class IncommingHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case 1:

                    Toast.makeText(MessengerService.this, "hello...!", Toast.LENGTH_SHORT).show();
                    break;
                default:

                    super.handleMessage(msg);
                    break;
            }

        }

    }

}
