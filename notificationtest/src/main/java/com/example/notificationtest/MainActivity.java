package com.example.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendNotice = findViewById(R.id.send_notice);
        sendNotice.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("001", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }

        if (view.getId() == R.id.send_notice) {
            Intent intent = new Intent(this,NotificationActivity.class);
            PendingIntent pi = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);

            Notification notification = new NotificationCompat.Builder(this,"001")
                    .setContentTitle("This is content title")
                    .setContentText("This is content text")
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(pi)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                    .setVibrate(new long[]{0,1000,1000,1000})
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("Learn12333333333333333333333333333333333333333333333333333333Learn1233333333333333333333333333333333333333333333333333333333333333Learn1233333333333333333333333333333333333333333333333333333333333333Learn123333333333333333333333333333333333333333333333333333333333333333333333"))
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .build();
            manager.notify(1, notification);
        }
    }
}