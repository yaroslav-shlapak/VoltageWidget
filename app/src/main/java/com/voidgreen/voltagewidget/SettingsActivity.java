package com.voidgreen.voltagewidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RemoteViews;

/**
 * Created by y.shlapak on Jun 17, 2015.
 */
public class SettingsActivity extends PreferenceActivity {
    int mAppWidgetId;
    public static final String EXTRA_STRING = "isFirstTime";
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null && extras.getBoolean(EXTRA_STRING)) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }


        final Context context = getApplicationContext();


        Intent i = new Intent(context, BatteryInfoService.class);
        context.startService(i);

        Log.d("SettingsActivity", "startService");

        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);

        Intent intentSettings = new Intent(context, SettingsActivity.class);
        intentSettings.putExtra(EXTRA_STRING, false);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intentSettings, 0);
        views.setOnClickPendingIntent(R.id.batteryInfoTextViewWidget, pendingIntent);

        Log.d("SettingsActivity", "start activity by tap");

        VoltageWidgetData voltageWidgetData = new VoltageWidgetData(context);
        views.setTextColor(R.id.batteryInfoTextViewWidget, voltageWidgetData.getTextColor());
        views.setTextViewTextSize(R.id.batteryInfoTextViewWidget, TypedValue.COMPLEX_UNIT_SP, voltageWidgetData.getTextSize());

        Log.d("SettingsActivity", "changing text color and size");

        Intent alarmManagerIntent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pendingIntentAlarmManager = PendingIntent.getBroadcast(context, 0, alarmManagerIntent, 0);

        Log.d("SettingsActivity", "creating alarm");
        Log.d("SettingsActivity", voltageWidgetData.getUpdateInterval());
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis() + 1000 * 3,
                Long.parseLong(voltageWidgetData.getUpdateInterval()), pendingIntentAlarmManager);

        Log.d("SettingsActivity", "starting alarm manager");

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        if (extras != null && extras.getBoolean(EXTRA_STRING)) {
            appWidgetManager.updateAppWidget(mAppWidgetId, views);
        } else {
            ComponentName batteryInfoWidget = new ComponentName(context, VoltageWidgetProvider.class);
            appWidgetManager.updateAppWidget(batteryInfoWidget, views);
        }


    }

    public void onApplyButtonClick(View v) {
        saveWidgetModification();
    }

    private void saveWidgetModification() {
        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }



    @Override
    protected void onPause() {
        super.onPause();


    }

}
