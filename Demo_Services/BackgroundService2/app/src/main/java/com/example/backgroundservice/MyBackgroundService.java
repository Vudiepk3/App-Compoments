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
        return null; // Phương thức này trả về null vì Service không hỗ trợ binding
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate"); // Ghi log khi Service được tạo
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand"); // Ghi log khi Service bắt đầu
        // todo
        startMusic(); // Bắt đầu phát nhạc
        return START_NOT_STICKY; // Service sẽ không được khởi động lại nếu bị hệ thống tắt
    }

    // Phương thức để bắt đầu phát nhạc
    private void startMusic() {
        if (mediaPLayer == null) {
            mediaPLayer = MediaPlayer.create(getApplicationContext(), R.raw.music); // Khởi tạo MediaPlayer với file nhạc
        }
        mediaPLayer.start(); // Bắt đầu phát nhạc
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy"); // Ghi log khi Service bị hủy
        if (mediaPLayer != null) {
            mediaPLayer.release(); // Giải phóng tài nguyên MediaPlayer
            mediaPLayer = null; // Gán MediaPlayer về null
        }
    }
}
