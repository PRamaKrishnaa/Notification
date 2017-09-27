package com.insolapps.notification.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.insolapps.notification.MainActivity;
import com.insolapps.notification.R;

/**
 * Created by User2 on 6/27/2017.
 */

public class NotificationReceiver1 extends BroadcastReceiver {

    private int MID = 0;
 
    private String message = "Notification Message";

    @Override
    public void onReceive(Context context, Intent intent) {

        /*
        try {
            if (intent.getStringExtra("id").equals("0")) {*/

        Log.e("First Notification", "true");

        ComponentName receiver = new ComponentName(context, NotificationReceiver1.class);
        PackageManager pm = context.getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        //notificationIntent.putExtra("message", message);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder mNotifyBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setTicker("App Name")
                .setSmallIcon(getNotificationIcon())
                .setContentTitle("Content Title")
                .setContentText(message)
                .setSound(alarmSound)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setAutoCancel(true).setWhen(when)
                .setContentIntent(pendingIntent);

        mNotifyBuilder.build().flags |= Notification.FLAG_AUTO_CANCEL | Notification.FLAG_SHOW_LIGHTS;
        mNotifyBuilder.build().defaults |= Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
        //.setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        notificationManager.notify(MID, mNotifyBuilder.build());
        MID++;
            /*}
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    private int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.notification_icon : R.mipmap.ic_launcher;
    }
}
