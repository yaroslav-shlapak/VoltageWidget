package com.voidgreen.voltagewidget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    private TextView batteryInfoTextView;
    private SharedPreferences batteryInfoSharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //batteryInfoTextView = (TextView) findViewById(R.id.batteryInfoTextView);
        batteryInfoSharedPref = getSharedPreferences(getString(R.string.battery_info_shared_pref) ,
                Context.MODE_PRIVATE);

        this.registerReceiver(batteryInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver batteryInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 3000);

            String textViewString = "health: " + health + "\n" +
                    "level: " + level + "\n" +
                    "voltage: " + voltage + "\n" +
                    "temperature: " + temperature + "\n";
            SharedPreferences.Editor editor = batteryInfoSharedPref.edit();
            editor.putString(getString(R.string.battery_info_shared_pref), textViewString);
            editor.commit();

        }
    };

}
