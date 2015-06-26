package com.voidgreen.voltagewidget;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Void on 26-Jun-15.
 */
public class UpdateService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        // REGISTER RECEIVER THAT HANDLES SCREEN ON AND SCREEN OFF LOGIC
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        BroadcastReceiver mReceiver = new WakeupReceiver();
        registerReceiver(mReceiver, filter);

        Utility.showToast(getApplicationContext(), "UpdateService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
