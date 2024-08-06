package com.example.foregroundandboundservice;

import static com.example.foregroundandboundservice.MyApplication.CHANNEL_ID;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import androidx.core.app.NotificationCompat;

public class MyService extends Service {
    private MyBinder myBinder = new MyBinder(); // Đối tượng Binder để kết nối với Activity

    // Lớp MyBinder để trả về đối tượng MyService cho Activity
    public class MyBinder extends Binder {
        MyService getMyService() {
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e("nhom10", "onBind"); // Ghi log khi Service được bind
        return myBinder; // Trả về đối tượng Binder
    }

    @Override
    public void onCreate() {
        Log.e("nhom10", "onCreate"); // Ghi log khi Service được tạo
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("nhom10", "onStartCommand"); // Ghi log khi Service bắt đầu
        sendNotification(); // Gửi thông báo Foreground
        return START_NOT_STICKY; // Chỉ khởi động lại Service nếu có Intent mới
    }

    // Phương thức để gửi thông báo Foreground
    private void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setContentTitle(getString(R.string.app_name)); // Tiêu đề của thông báo
        builder.setContentText("Nhom 10"); // Nội dung của thông báo
        builder.setSmallIcon(R.drawable.ic_android_black_24dp); // Biểu tượng của thông báo
        Notification notification = builder.build(); // Tạo thông báo
        startForeground(1, notification); // Đặt Service ở chế độ Foreground với thông báo
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("nhom10", "onUnbind"); // Ghi log khi Service bị unbind
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.e("nhom10", "onDestroy"); // Ghi log khi Service bị hủy
        super.onDestroy();
    }
}
