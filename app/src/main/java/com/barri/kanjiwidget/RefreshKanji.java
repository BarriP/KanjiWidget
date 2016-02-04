package com.barri.kanjiwidget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

/**
 * Created by Barri on 04/02/2016.
 */
public class RefreshKanji {

    /**
     * Sets the refreshing for the widget
     *
     * @param context the widget context
     */
    public static void setRefresh(Context context){
        //First, get the alarm service
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        //Repetir a las 0000 de cada dia
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 0);

        //TODO: ajustar time exacto

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        am.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, getAlarmIntent(context));
    }

    private static PendingIntent getAlarmIntent(Context context) {
        Intent intent = new Intent(context, KanjiWidgetImpl.class);
        intent.setAction(KanjiWidgetImpl.ACTION_UPDATE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        return pi;
    }
}
