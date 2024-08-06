package com.example.foreservice;

import static com.example.foreservice.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class MyService extends Service{
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Nhom10", "MyService oncreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            Song song = (Song) bundle.get("object song");
            if (song != null) {
                startMusic(song);
                sendNotification(song);
            }
        }
        return START_NOT_STICKY;
        //không cần khởi động lại.
    }

    private void startMusic(Song song) {
        if (mediaPlayer == null)
            mediaPlayer = MediaPlayer.create(getApplicationContext(), song.getResource());
        mediaPlayer.start();
    }

    private void sendNotification(Song song) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentIntent(pendingIntent)
                .setContentTitle(song.getTitle())
                .setContentText(song.getSinger())
                .setSound(null)
                .build();
        startForeground(1, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Nhom10", "MyService onDestroy");
        if (mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
