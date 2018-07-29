package com.example.umang.alarmclock;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class NotificationHelper extends ContextWrapper{
    private NotificationManager notificationManager;


    public final static String channel_id=" Channel_id";
    public final static String channel_name="Channel_1";


    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
            createChannel();
    }
    @TargetApi(Build.VERSION_CODES.O)
    public void createChannel()
    {
        NotificationChannel channel1=new NotificationChannel(channel_id,channel_name,NotificationManager.IMPORTANCE_HIGH);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(R.color.colorPrimary);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        getNotificationManager().createNotificationChannel(channel1);


    }

    public NotificationManager getNotificationManager() {
        if(notificationManager==null)
            notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        return notificationManager;
    }
    public NotificationCompat.Builder getChannelNotification()
    {
        return new NotificationCompat.Builder(getApplicationContext(),channel_id)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("ALARM")
                .setContentText("Tap to switch off the alarm");

    }
}