package com.voidgreen.voltagewidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by y.shlapak on Jun 10, 2015.
 */
public class VoltageWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        ComponentName thisWidget = new ComponentName(context,
                VoltageWidgetProvider.class);

        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget)) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setTextViewText(R.id.batteryInfoTextViewWidget, Utility.getSavedBatteryInfo(context));

            Intent intent = new Intent(context, SettingsActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            views.setOnClickPendingIntent(R.id.batteryInfoTextViewWidget, pendingIntent);

            appWidgetManager.updateAppWidget(widgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);

        am.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis() + 1000 * 3, 1000, pi);
    }

    @Override
    public void onDisabled(Context context) {

        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
        super.onDisabled(context);
    }


}
