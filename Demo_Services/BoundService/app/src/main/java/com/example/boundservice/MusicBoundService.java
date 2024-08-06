package com.example.boundservice;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicBoundService extends Service {
    private MyBinder myBinder = new MyBinder();
    private MediaPlayer myMediaPlayer;
    public class MyBinder extends Binder{
        MusicBoundService getMusicBoundService(){
            return MusicBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("nhom10", "onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("nhom10", "onBind");
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("nhom10", "unBind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("nhom10", "onDestroy");
        if(myMediaPlayer != null) {
            myMediaPlayer.release();
            myMediaPlayer = null;
        }
    }
    public void startMusic(){
        if(myMediaPlayer == null)
            myMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.file_music);
        myMediaPlayer.start();
    }
}
