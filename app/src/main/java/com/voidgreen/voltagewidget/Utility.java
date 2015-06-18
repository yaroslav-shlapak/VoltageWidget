package com.voidgreen.voltagewidget;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by y.shlapak on Jun 15, 2015.
 */
public class Utility {
    private final static String defaultString = "Battery info";

    public static String getSavedBatteryInfo(Context context) {
        SharedPreferences batteryInfoSharedPref = context.getSharedPreferences(context.getString(R.string.voltage_widget_shared_pref),
                Context.MODE_PRIVATE);
        return batteryInfoSharedPref.getString(context.getString(R.string.battery_info_shared_pref_key), defaultString);
    }

    public static void saveBatteryInfo(Context context, String batteryInfo) {
        SharedPreferences batteryInfoSharedPref = context.getSharedPreferences(context.getString(R.string.voltage_widget_shared_pref),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = batteryInfoSharedPref.edit();
        editor.putString(context.getString(R.string.battery_info_shared_pref_key), batteryInfo);
        editor.apply();
    }
}
