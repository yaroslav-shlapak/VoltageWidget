package com.voidgreen.voltagewidget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.IBinder;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by y.shlapak on Jun 11, 2015.
 */
public class UpdateWidgetService extends Service {

    @Override
    public void onStart(Intent intent, int startId) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this
                .getApplicationContext());

        int[] allWidgetIds = intent
                .getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
        int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 3000);

        String textViewString = "health: " + health + "\n" +
                "level: " + level + "\n" +
                "voltage: " + voltage + "\n" +
                "temperature: " + temperature + "\n";

//    ComponentName thisWidget = new ComponentName(getApplicationContext(),
//        MyWidgetProvider.class);
//    int[] allWidgetIds2 = appWidgetManager.getAppWidgetIds(thisWidget);

        for (int widgetId : allWidgetIds) {
            // create some random data
            int number = (new Random().nextInt(100));

            RemoteViews remoteViews = new RemoteViews(this
                    .getApplicationContext().getPackageName(),
                    R.layout.widget_layout);

            remoteViews.setTextViewText(R.id.batteryInfoTextView, textViewString);

            // Register an onClickListener
            Intent clickIntent = new Intent(this.getApplicationContext(),
                    VoltageWidgetProvider.class);

            clickIntent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            clickIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,
                    allWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, clickIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.batteryInfoTextView, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
        stopSelf();

        super.onStart(intent, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}