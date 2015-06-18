package com.voidgreen.voltagewidget;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by VOID on 13-06-15.
 */
public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);

        remoteViews.setTextViewText(R.id.batteryInfoTextViewWidget, Utility.getSavedBatteryInfo(context));
        ComponentName batteryInfoWidget = new ComponentName(context, VoltageWidgetProvider.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(batteryInfoWidget, remoteViews);

        Log.d("AlarmManager", "starting alarm manager");

    }

}

