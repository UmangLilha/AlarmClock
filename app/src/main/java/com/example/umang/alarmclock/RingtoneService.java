package com.example.umang.alarmclock;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


public class RingtoneService extends Service {
    private NotificationHelper notificationHelper;


    MediaPlayer media_song;
    boolean isplaying;
    NotificationManager notificationManager;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //isplaying=false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        String ringtone_state=intent.getExtras().getString("extra");
        Log.i("Start command",ringtone_state);


        if(ringtone_state.equals("alarm on"))
            startId=1;
        else
            startId=0;
        if(!isplaying && startId==1)
        {
            media_song=MediaPlayer.create(this,R.raw.nightcore);
            media_song.start();
            isplaying=true;
            Intent intent_mainActivity=new Intent(this.getApplicationContext(),MainActivity.class);



            PendingIntent pendingIntent=PendingIntent.getActivity(RingtoneService.this,0,intent_mainActivity,0);
            notificationHelper=new NotificationHelper(this);
            NotificationCompat.Builder nb=notificationHelper.getChannelNotification().setContentIntent(pendingIntent);
            notificationHelper.getNotificationManager().notify(1,nb.build());



        }
        else if(isplaying && startId==0)
        {
            media_song.stop();
            media_song.reset();
            isplaying=false;
        }
        else
        {

        }




        return START_NOT_STICKY;

    }
}
