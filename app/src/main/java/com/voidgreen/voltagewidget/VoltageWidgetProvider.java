package com.voidgreen.voltagewidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

/**
 * Created by y.shlapak on Jun 10, 2015.
 */
public class VoltageWidgetProvider extends AppWidgetProvider {

/*    @Override
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
    }*/

/*    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);


        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis() + 1000 * 3, 1000, pendingIntent);
    }*/

    @Override
    public void onDisabled(Context context) {

        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        super.onDisabled(context);
    }


}
