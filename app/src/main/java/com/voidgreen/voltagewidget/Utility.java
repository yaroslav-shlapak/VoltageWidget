package com.voidgreen.voltagewidget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by y.shlapak on Jun 15, 2015.
 */
public class Utility {
    private final static String[] defaultStrings = {};
    private final static Set<String> defaultSet = new HashSet<>(Arrays.asList(defaultStrings));
    private static Set<String> batteryInfoSet = new HashSet<>();
    private Queue<String> queue;

    public static String getSavedBatteryInfo(Context context, int stringsNumberToReturn) {
        SharedPreferences batteryInfoSharedPref = context.getSharedPreferences(context.getString(R.string.voltage_widget_shared_pref),
                Context.MODE_PRIVATE);
        String result = "";
        Set<String> savedSet = batteryInfoSharedPref.getStringSet
                (context.getString(R.string.battery_info_shared_pref_key), defaultSet);
        int i = 0;
        for (Iterator<String> it = savedSet.iterator(); it.hasNext();) {
            if(i >= 0) {
                result += "\n";
            }
            result += it.next();
            if(i >= stringsNumberToReturn) {
                break;
            }
            i++;
        }
        Log.d("Utility", "getSavedBatteryInfo");
        return result;
    }

    public static void saveBatteryInfo(Context context, String batteryInfo) {
        SharedPreferences batteryInfoSharedPref = context.getSharedPreferences(context.getString(R.string.voltage_widget_shared_pref),
                Context.MODE_PRIVATE);
        Log.d("Utility", "saveBatteryInfo before set created");
        Set<String> savedSet = batteryInfoSharedPref.getStringSet
                (context.getString(R.string.battery_info_shared_pref_key), defaultSet);
        Log.d("Utility", "saveBatteryInfo set created");
        LinkedList<String> queue = new LinkedList<>(savedSet);
        queue.peekLast();
        Calendar c = Calendar.getInstance();
        String time = "" + c.get(Calendar.HOUR) + " : " + c.get(Calendar.MINUTE) + " : " + c.get(Calendar.SECOND);

        queue.addFirst(time + " " + batteryInfo);
        Log.d("Utility", "saveBatteryInfo queue created");
        savedSet = new HashSet<>(queue);
        Log.d("Utility", "saveBatteryInfo queue handed on to set");
        SharedPreferences.Editor editor = batteryInfoSharedPref.edit();
        editor.putStringSet(context.getString(R.string.battery_info_shared_pref_key), savedSet);
        Log.d("Utility", "saveBatteryInfo after editor");
        editor.apply();
        Log.d("Utility", "saveBatteryInfo");
    }

    public static void updateAllWidgets(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        ComponentName thisWidget = new ComponentName(context,
                VoltageWidgetProvider.class);

        for (int widgetId : appWidgetManager.getAppWidgetIds(thisWidget)) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setTextViewText(R.id.batteryInfoTextViewWidget, Utility.getSavedBatteryInfo(context, 10));
            appWidgetManager.updateAppWidget(widgetId, views);
        }
        Log.d("Utility", "updateAllWidgets");
    }

}
