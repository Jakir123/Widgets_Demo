package com.hrfsoftlab.jakir.widgets_demo;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.text.format.DateFormat;
import android.widget.RemoteViews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Jakir Hossain
 * @version 1.3.0
 * @desc description of the class
 * @link n/a
 * @created on 8/28/2015
 * @updated on
 * @modified by
 * @updated on
 * @since 1.0
 */
public class MyService extends Service
{
    public static int clickCount = 0;
    String[] quotes={"abc","xyz","mno","kdjfldj"};
    ArrayList<String> arrayList=new ArrayList<>();
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        List<String> list= Arrays.asList(quotes);
        arrayList.addAll(list);
        buildUpdate();

        return super.onStartCommand(intent, flags, startId);
    }

    private void buildUpdate()
    {
        String lastUpdated = DateFormat.format("MMMM dd, yyyy h:mmaa", new Date()).toString();

        RemoteViews view = new RemoteViews(getPackageName(), R.layout.widgets);

        view.setTextViewText(R.id.lastUpdated, getString());

        // Push update for this widget to the home screen
        ComponentName thisWidget = new ComponentName(this, MyWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(this);
        manager.updateAppWidget(thisWidget, view);
    }

    private String getString() {
        if(clickCount>=arrayList.size()){
            clickCount=0;
        }
        String desc=arrayList.get(clickCount);
        ++clickCount;
        return desc;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}
