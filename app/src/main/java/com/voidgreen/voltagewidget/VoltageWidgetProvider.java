package com.voidgreen.voltagewidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by y.shlapak on Jun 10, 2015.
 */
public class VoltageWidgetProvider extends AppWidgetProvider {
    int mAppWidgetId;
    RemoteViews views;
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        Utility.updateAllWidgets(context);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Intent i = new Intent(context, BatteryInfoService.class);
        context.stopService(i);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Intent i = new Intent(context, BatteryInfoService.class);
        context.startService(i);
    }


}
