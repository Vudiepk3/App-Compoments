package com.example.backgroundservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyBackgroundService extends Service {
    private static final String TAG = MyBackgroundService.class.getName();
    private MediaPlayer mediaPLayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");
        // todo
        startMusic();
        return START_NOT_STICKY;
    }

    private void startMusic() {
        if (mediaPLayer == null){
            mediaPLayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        }
        mediaPLayer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
        if (mediaPLayer!=null){
            mediaPLayer.release();
            mediaPLayer = null;
        }
    }
}
