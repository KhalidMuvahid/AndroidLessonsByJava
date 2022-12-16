package com.example.widgets;

import static android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE;
import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_ID;
import static android.appwidget.AppWidgetManager.EXTRA_APPWIDGET_IDS;

import android.app.PendingIntent;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Arrays;
import java.util.Random;


public class DemoWidgetProvider extends AppWidgetProvider {
    final static  String TAG = DemoWidgetProvider.class.getSimpleName();
    final static  String BUTTON_ACTION = "button_action";

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.d(TAG,"onDeleted(), ids:"+ Arrays.toString(appWidgetIds));
    }

    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        Log.d(TAG,"onAppWidgetOptionsChanged(), ids:"+appWidgetId);
    }

    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
        Log.d(TAG,"onDisabled()");
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        Log.d(TAG,"onEnabled()");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(TAG,"onReceive()"+intent.getAction());

        int appWidgetId = intent.getIntExtra(EXTRA_APPWIDGET_ID,0);

        Log.d(TAG,"onReceive()"+"appWidgetId = "+ appWidgetId);

        if (BUTTON_ACTION.equals(intent.getAction())){
            Intent intentSending = new Intent(context,DemoWidgetProvider.class);

            intentSending.setAction(ACTION_APPWIDGET_UPDATE);
            intentSending.putExtra(EXTRA_APPWIDGET_IDS,intent.getIntArrayExtra(EXTRA_APPWIDGET_IDS));
            intentSending.putExtra(EXTRA_APPWIDGET_ID,appWidgetId);

            context.sendBroadcast(intentSending);
        }
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        super.onRestored(context, oldWidgetIds, newWidgetIds);
        Log.d(TAG,"onRestored() newIds: "+Arrays.toString(newWidgetIds)+", oldIds: "+Arrays.toString(oldWidgetIds));
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        Log.d(TAG,"onUpdate(), ids:"+Arrays.toString(appWidgetIds));

        ComponentName thisWidget = new ComponentName(context,DemoWidgetProvider.class);
        int [] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds){
            int number = (new Random().nextInt(100));

            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),R.layout.demo_widget);
            Log.d(TAG,String.valueOf(number));

            remoteViews.setTextViewText(R.id.title_widget_text,"Title");
            remoteViews.setTextViewText(R.id.value_widget_text,String.valueOf(number));

            Intent intent = new Intent(context,DemoWidgetProvider.class);

            intent.setAction(BUTTON_ACTION);
            intent.putExtra(EXTRA_APPWIDGET_ID,widgetId);
            intent.putExtra(EXTRA_APPWIDGET_IDS,appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.widget_button,pendingIntent);
            appWidgetManager.updateAppWidget(widgetId,remoteViews);


        }
    }
}
