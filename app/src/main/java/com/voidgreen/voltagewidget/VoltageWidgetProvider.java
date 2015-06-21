package com.voidgreen.voltagewidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.TypedValue;
import android.widget.RemoteViews;

/**
 * Created by y.shlapak on Jun 10, 2015.
 */
public class VoltageWidgetProvider extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews views = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);
        VoltageWidgetData voltageWidgetData = new VoltageWidgetData(context);
        views.setTextColor(R.id.batteryInfoTextViewWidget, voltageWidgetData.getTextColor());
        views.setFloat(R.id.batteryInfoTextViewWidget, "setTextSize", voltageWidgetData.getTextSize());

        for (int widgetId : appWidgetIds) {
            Utility.updateWidget(context, appWidgetManager, views, widgetId);
        }
    }
}
