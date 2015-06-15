package com.voidgreen.voltagewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.view.View;
import android.widget.TextView;

/**
 * Created by y.shlapak on Jun 15, 2015.
 */
public class BatteryInfoReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 3000);

        String textViewString = Integer.toString(voltage);
        Utility.saveBatteryInfo(context, textViewString);

        View view = View.inflate(context, R.layout.activity_main, null);
        TextView batteryInfoTextView = (TextView) view.findViewById(R.id.batteryInfoTextView);
        batteryInfoTextView.setText(textViewString);
    }
}