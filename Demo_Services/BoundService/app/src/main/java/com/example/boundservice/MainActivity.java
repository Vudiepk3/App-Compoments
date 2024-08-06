package com.example.boundservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private boolean isServiceConnection = false; // Biến cờ để kiểm tra trạng thái kết nối với Service
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            // Khi kết nối với Service thành công
            MusicBoundService.MyBinder myBinder = (MusicBoundService.MyBinder) iBinder;
            MusicBoundService myMusicBoundService = myBinder.getMusicBoundService(); // Lấy đối tượng Service
            myMusicBoundService.startMusic(); // Gọi phương thức startMusic() của Service
            isServiceConnection = true; // Đánh dấu đã kết nối với Service
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            // Khi kết nối với Service bị ngắt
            isServiceConnection = false; // Đánh dấu chưa kết nối với Service
        }
    };
    private Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Thiết lập giao diện chính
        getFromWidget(); // Lấy các thành phần từ giao diện
        AddEventButton(); // Gán sự kiện cho các nút bấm
    }

    // Phương thức để lấy các thành phần từ giao diện
    public void getFromWidget(){
        btnStart = findViewById(R.id.start_service);
        btnStop = findViewById(R.id.stop_service);
    }

    // Phương thức để gán sự kiện cho các nút bấm
    public void AddEventButton(){
        btnStart.setOnClickListener(new MyButtonEvent());
        btnStop.setOnClickListener(new MyButtonEvent());
    }

    // Lớp xử lý sự kiện cho các nút bấm
    protected class MyButtonEvent implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.start_service)
                clickStartService(); // Gọi phương thức để bắt đầu Service khi nhấn nút Start
            if (view.getId() == R.id.stop_service)
                clickStopService(); // Gọi phương thức để dừng Service khi nhấn nút Stop
        }
    }

    // Phương thức để bắt đầu Service và kết nối với nó
    private void clickStartService() {
        Intent intent = new Intent(this, MusicBoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE); // Kết nối với Service
    }

    // Phương thức để dừng Service và ngắt kết nối
    private void clickStopService() {
        if(isServiceConnection) {
            unbindService(serviceConnection); // Ngắt kết nối với Service
            isServiceConnection = false; // Cập nhật trạng thái kết nối
        }
    }
}
