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

public class NotificationReceiver2 extends BroadcastReceiver {
    private int MID = 0;
    private String message = "Message";

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getStringExtra("id").equals("1")) {
                Log.e("Second Broadcast", "true");
                ComponentName receiver = new ComponentName(context, NotificationReceiver2.class);
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
                        .setSmallIcon(R.drawable.notification_icon)
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        /*Intent notificationIntent = new Intent(context, ResultActivity.class);
        notificationIntent.putExtra("message", "Message");
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.build().flags |= Notification.FLAG_AUTO_CANCEL;
        Notification notification = builder.setContentTitle("Content Heading")
                .setContentText("Content Message")
                .setTicker("App Name")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent).build();


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MID, notification);
        MID++;*/
    }
}
