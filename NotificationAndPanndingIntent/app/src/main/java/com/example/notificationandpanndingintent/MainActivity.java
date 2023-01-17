package com.example.notificationandpanndingintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    final  static String CHANEL_ID = "channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        Button restartBt = findViewById(R.id.restartBt);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    CharSequence name = getString(R.string.chanel_name);
                    String description = getString(R.string.chanel_description);

                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel channel = new NotificationChannel(CHANEL_ID,name,importance);
                    channel.setDescription(description);

                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                    notificationManager.createNotificationChannel(channel);
                }

                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,CHANEL_ID)
                        .setSmallIcon(R.drawable.ic_baseline_back_hand_24)
                        .setContentTitle("My Notification")
                        .setContentText("Hello World")
                        .setContentIntent(pendingIntent)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(getResources().getString(com.google.android.material.R.string.fab_transformation_scrim_behavior)))
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

                notificationManager.notify(2,builder.build());
            }
        });

        restartBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                int requestCode = 123456;
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,requestCode,intent,PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager mgr = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                mgr.set(AlarmManager.RTC,System.currentTimeMillis()+500,pendingIntent);
                //android.os.Process.killProcess(android.os.Process.myPid());
                //System.exit(0);
                finish();
            }
        });



    }
}