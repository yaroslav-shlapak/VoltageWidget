package com.voidgreen.voltagewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

/**+
 * Created by y.shlapak on Jun 15, 2015.
 */
public class BatteryInfoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 3000);
        if (voltage < 3000) {
            voltage = voltage * 1000;
        }

        String string = Integer.toString(voltage);

        Utility.saveBatteryInfo(context, string);
        Utility.updateAllWidgets(context);

        Utility.showToast(context, "BatteryInfoReceiver");
    }
}