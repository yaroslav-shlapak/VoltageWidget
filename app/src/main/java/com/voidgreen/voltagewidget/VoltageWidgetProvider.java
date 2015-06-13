package com.voidgreen.voltagewidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by y.shlapak on Jun 10, 2015.
 */
public class VoltageWidgetProvider extends AppWidgetProvider {
    private String textViewString = "Battery info";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {

        final int N = appWidgetIds.length;
        // Perform this loop procedure for each App Widget that belongs to this provider

        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            SharedPreferences sharedPreferences = context.getSharedPreferences(context.getString(R.string.battery_info_shared_pref), Context.MODE_PRIVATE);
            views.setTextViewText(R.id.batteryInfoTextViewWidget, sharedPreferences.getString(context.getString(R.string.battery_info_shared_pref), textViewString));

            // Tell the AppWidgetManager to perform an update on the current app widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

}
