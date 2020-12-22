package com.jamespfluger.roboshield.blocking;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.jamespfluger.roboshield.R;

public class NotificationForge {
    private final String CHANNEL_ID = "CHANNEL_4096_1024";
    private final Context context;
    private final NotificationManager notificationManager;
    private final NotificationCompat.Builder notificationBuilder;

    public NotificationForge(Context context) {
        this.context = context;
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationBuilder = new NotificationCompat.Builder(context, CHANNEL_ID);

        notificationBuilder.setChannelId(CHANNEL_ID)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                //.setSmallIcon(R.mipmap.ic_notification)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O && notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
            createNotificationChannel();
        }
    }

    public void issueNotification() {
        notificationBuilder.setContentText(context.getString(R.string.callWasBlocked));
        notificationManager.notify((int) System.currentTimeMillis(), notificationBuilder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.appName),
                NotificationManager.IMPORTANCE_DEFAULT
        );

        notificationBuilder.setChannelId(CHANNEL_ID);
        notificationManager.createNotificationChannel(channel);
    }
}