package com.example.foregroundandboundservice;
import static com.example.foregroundandboundservice.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;

public class MyService extends Service{
    private MyBinder myBinder = new MyBinder();
    public class MyBinder extends Binder {
        MyService getMyService(){
            return MyService.this;
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("nhom10", "onBind");
        return myBinder;
    }

    @Override
    public void onCreate() {
        Log.e("nhom10", "onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("nhom10", "onStartCommand");

        sendNotification();
        return START_NOT_STICKY;
    }

    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText("Nhom 10");
        builder.setSmallIcon(R.drawable.ic_android_black_24dp);
        Notification notification = builder.build();
        startForeground(1, notification);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("nhom10", "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("nhom10", "onDestroy");
        super.onDestroy();
    }
}
