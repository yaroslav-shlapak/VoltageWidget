package com.voidgreen.voltagewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.util.Log;

/**+
 * Created by y.shlapak on Jun 15, 2015.
 */
public class BatteryInfoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 3000);

        String textViewString = Integer.toString(voltage);
        Log.d("BatteryInfoReceiver", "before saveBatteryInfo");
        Utility.saveBatteryInfo(context, textViewString);

        Utility.updateAllWidgets(context);

        Log.d("BatteryInfoReceiver", "BatteryInfoReceiver.onReceive");
    }
}