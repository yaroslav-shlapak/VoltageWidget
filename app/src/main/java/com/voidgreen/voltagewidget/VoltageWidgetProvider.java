package com.voidgreen.voltagewidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

/**
 * Created by y.shlapak on Jun 10, 2015.
 */
public class VoltageWidgetProvider extends AppWidgetProvider {
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Utility.updateAllWidgets(context);
        Utility.showToast(context, "VoltageWidgetProvider:onUpdate");
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);

        Utility.stopUpdateService(context);
        Utility.showToast(context, "VoltageWidgetProvider:onDeleted");

        if (alarmMgr!= null) {
            alarmMgr.cancel(alarmIntent);
        }
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);

        Utility.startUpdateService(context);

        alarmMgr = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmMgr.set(AlarmManager.RTC, SystemClock.elapsedRealtime() + 60 * 1000, alarmIntent);
        Utility.showToast(context, "VoltageWidgetProvider:onEnabled");

    }


}
