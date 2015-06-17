package com.voidgreen.voltagewidget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by y.shlapak on Jun 17, 2015.
 */
public class SettingsActivity extends PreferenceActivity {
    int mAppWidgetId;
    SharedPreferences sharedPreferences;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }



        final Context context = getApplicationContext();
        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);
        views.setTextColor(R.id.batteryInfoTextViewWidget, );

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(mAppWidgetId, views);

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

    class VoltageVidgetData {
        SharedPreferences sharedPreferences;
        public String getTextSize() {
            return textSize;
        }

        public void setTextSize(String textSize) {
            this.textSize = textSize;
        }

        public String getTextColor() {
            return textColor;
        }

        public void setTextColor(String textColor) {
            this.textColor = textColor;
        }

        public String getUpdateInterval() {
            return updateInterval;
        }

        public void setUpdateInterval(String updateInterval) {
            this.updateInterval = updateInterval;
        }

        public VoltageVidgetData(String textSize, String textColor, String updateInterval) {
            this.textSize = textSize;
            this.textColor = textColor;
            this.updateInterval = updateInterval;

        }

        private String textSize;
        private String textColor;
        private String updateInterval;

        VoltageVidgetData(Context context) {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            this.textSize = sharedPreferences.getString(R.string.pref_apply_button_key);
            this.textColor = textColor;
            this.updateInterval = updateInterval;
            this("", "", "");
        }


    }
}
