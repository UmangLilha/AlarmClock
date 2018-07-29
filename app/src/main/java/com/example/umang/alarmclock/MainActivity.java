package com.example.umang.alarmclock;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    TimePicker timePicker;
    Button set,off;
    TextView textView;
    PendingIntent pendingIntent;
     //NotificationHelper notificationHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker=(TimePicker)findViewById(R.id.time);
        set=(Button)findViewById(R.id.set_alarm);
        off=(Button)findViewById(R.id.off_alarm);
        textView=(TextView)findViewById(R.id.textView);
            alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
            final Calendar calendar= Calendar.getInstance();


        final Intent intent=new Intent(MainActivity.this,AlarmReceiver.class);
            set.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View view) {
                    calendar.set(calendar.HOUR_OF_DAY,timePicker.getHour());
                    calendar.set(calendar.MINUTE,timePicker.getMinute());
                    int hour=timePicker.getHour();
                    int minutes=timePicker.getMinute();
                    String Hour=String.valueOf(hour);
                    String Minutes=String.valueOf(minutes);
                    if (minutes<10)
                        Minutes="0"+Minutes;
                    textView.setText("Alarm set for "+Hour+":"+Minutes);
                    intent.putExtra("extra","alarm on");
                    pendingIntent=PendingIntent.getBroadcast(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);


                }
            });
            off.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView.setText("alarm off");


                   //alarmManager.cancel(pendingIntent);
                    intent.putExtra("extra","alarm_off");
                    sendBroadcast(intent);
                }
            });
    }
}
