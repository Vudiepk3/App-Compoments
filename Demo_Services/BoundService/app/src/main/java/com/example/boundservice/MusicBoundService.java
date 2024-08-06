package com.example.boundservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicBoundService extends Service {
    private MyBinder myBinder = new MyBinder(); // Đối tượng Binder để kết nối với Activity
    private MediaPlayer myMediaPlayer; // Đối tượng MediaPlayer để phát nhạc

    // Lớp MyBinder để trả về đối tượng MusicBoundService cho Activity
    public class MyBinder extends Binder {
        MusicBoundService getMusicBoundService() {
            return MusicBoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("nhom10", "onCreate"); // Ghi log khi Service được tạo
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("nhom10", "onBind"); // Ghi log khi Service được bind
        return myBinder; // Trả về đối tượng Binder
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("nhom10", "unBind"); // Ghi log khi Service bị unbind
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("nhom10", "onDestroy"); // Ghi log khi Service bị hủy
        if (myMediaPlayer != null) {
            myMediaPlayer.release(); // Giải phóng tài nguyên MediaPlayer
            myMediaPlayer = null;
        }
    }

    // Phương thức để bắt đầu phát nhạc
    public void startMusic() {
        if (myMediaPlayer == null)
            myMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.file_music); // Khởi tạo MediaPlayer với file nhạc
        myMediaPlayer.start(); // Bắt đầu phát nhạc
    }
}
