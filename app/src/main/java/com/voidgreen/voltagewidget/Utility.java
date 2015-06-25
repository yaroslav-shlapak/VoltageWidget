package com.voidgreen.voltagewidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

/**
 * Created by y.shlapak on Jun 15, 2015.
 */
public class Utility {
    public final static String DEFAULT_STRING = "WAIT";

    public static String getSavedBatteryInfo(Context context) {
        SharedPreferences batteryInfoSharedPref = context.getSharedPreferences(context.getString(R.string.voltage_widget_shared_pref),
                Context.MODE_PRIVATE);
        return batteryInfoSharedPref.getString
                (context.getString(R.string.battery_info_shared_pref_key), DEFAULT_STRING);
    }

    public static void saveBatteryInfo(Context context, String batteryInfo) {
        SharedPreferences batteryInfoSharedPref = context.getSharedPreferences(context.getString(R.string.voltage_widget_shared_pref),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = batteryInfoSharedPref.edit();
        editor.putString(context.getString(R.string.battery_info_shared_pref_key), batteryInfo);
        editor.apply();
    }

    public static void updateAllWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context,
                VoltageWidgetProvider.class);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget)) {
            updateWidget(context, appWidgetManager, views, widgetId);
        }
    }

    public static void updateWidget(Context context, AppWidgetManager appWidgetManager, RemoteViews views, int widgetId) {
        VoltageWidgetData voltageWidgetData = new VoltageWidgetData(context);
        views.setTextColor(R.id.batteryInfoTextViewWidget, voltageWidgetData.getTextColor());
        views.setFloat(R.id.batteryInfoTextViewWidget, "setTextSize", voltageWidgetData.getTextSize());

        views.setTextViewText(R.id.batteryInfoTextViewWidget, Utility.getSavedBatteryInfo(context));
        appWidgetManager.updateAppWidget(widgetId, views);
    }
}
