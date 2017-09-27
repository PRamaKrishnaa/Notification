package com.insolapps.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.insolapps.notification.receiver.NotificationReceiver1;
import com.insolapps.notification.receiver.NotificationReceiver2;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private PendingIntent pendingIntent1, pendingIntent2;
    private AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // for First notification

        Date date = new Date();//initializes to now

        Calendar calendar_one = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(date);
        calendar_one.setTime(date);
        // we can set time by open date and time picker dialog
        calendar_one.setTimeInMillis(System.currentTimeMillis());
        //calendar.clear();
        calendar_one.set(Calendar.HOUR_OF_DAY, 13);
        calendar_one.set(Calendar.MINUTE, 27);
        calendar_one.set(Calendar.SECOND, 00);

       /* if (Calendar.getInstance().after(calendar)) {
            // Move to tomorrow
            calendar.add(Calendar.DATE, 1);
        }*/

        if (calendar_one.before(cal_now)) {//if its in the past increment
            calendar_one.add(Calendar.DATE, 1);

            Log.e("Next day morning", "true");
        } else {
            Log.e("Today morning", "true");
        }

        Intent myIntent = new Intent(MainActivity.this, NotificationReceiver1.class);
        //myIntent.putExtra("id", "0");
        pendingIntent1 = PendingIntent.getBroadcast(MainActivity.this, 0,
                myIntent, 0);


        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar_one.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
                pendingIntent1);


        //for second notification

        Intent myIntentnew = new Intent(MainActivity.this, NotificationReceiver2.class);
        myIntentnew.putExtra("id", "1");

        Calendar calendar_two = Calendar.getInstance();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        calendar_two.setTime(date);

        calendar_two.setTimeInMillis(System.currentTimeMillis());
        //calendar_new.clear();
        calendar_two.set(Calendar.HOUR_OF_DAY, 13);
        calendar_two.set(Calendar.MINUTE, 30);
        calendar_two.set(Calendar.SECOND, 0);

        /*if (Calendar.getInstance().after(calendar_new)) {
            // Move to tomorrow
            calendar_new.add(Calendar.DATE, 1);
        }*/

        if (calendar_two.before(cal)) {//if its in the past increment
            calendar_two.add(Calendar.DATE, 1);

            Log.e("Next day evening", "true");
        } else {
            Log.e("Today evening", "true");
        }

        pendingIntent2 = PendingIntent.getBroadcast(MainActivity.this, 1,
                myIntentnew, 0);

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar_two.getTimeInMillis(), AlarmManager.INTERVAL_DAY,
                pendingIntent2);
    }
}
