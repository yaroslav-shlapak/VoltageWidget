package com.voidgreen.voltagewidget;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

/**
 * Created by y.shlapak on Jun 11, 2015.
 */
public class BatteryInfoService extends Service {

    private BroadcastReceiver receiver;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        receiver = new BatteryInfoReceiver();
        registerReceiver(receiver, filter);
/*        Intent intent = new Intent(Intent.ACTION_BATTERY_CHANGED);
        sendBroadcast(intent);*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}