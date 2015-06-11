package com.voidgreen.voltagewidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;

/**
 * Created by y.shlapak on Jun 10, 2015.
 */
public class VoltageWidgetProvider extends AppWidgetProvider {
    private String textViewString;

    private static final String ACTION_CLICK = "ACTION_CLICK";

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
        int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 3000);

        textViewString = "health: " + health + "\n" +
                "level: " + level + "\n" +
                "voltage: " + voltage + "\n" +
                "temperature: " + temperature + "\n";

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        ComponentName thisWidget = new ComponentName(context,
                VoltageWidgetProvider.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        // Build the intent to call the service
        Intent intent = new Intent(context.getApplicationContext(),
                UpdateWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);

        // Update the widgets via the service
        context.startService(intent);
    }
}
