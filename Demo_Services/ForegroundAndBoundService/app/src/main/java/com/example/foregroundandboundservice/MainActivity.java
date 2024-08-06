package com.example.foregroundandboundservice;

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
    private MyService myService;
    private boolean isServiceConnected= false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder myBinder = (MyService.MyBinder) iBinder;
            myService = myBinder.getMyService();
            isServiceConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            myService = null;
            isServiceConnected = false;
        }
    };
    Button btnStartService, btnStopForegroundService, btnStopBoundService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFromWidget();
        AddEventButton();
    }
    public void getFromWidget(){
        btnStartService = findViewById(R.id.btn_start_service);
        btnStopForegroundService = findViewById(R.id.stop_foreground_service);
        btnStopBoundService = findViewById(R.id.stop_bound_service);
    }

    public void AddEventButton(){
        btnStartService.setOnClickListener(new MyButtonEvent());
        btnStopForegroundService.setOnClickListener(new MyButtonEvent());
        btnStopBoundService.setOnClickListener(new MyButtonEvent());
    }
    protected class MyButtonEvent implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_start_service)
                clickStartService();
            if (view.getId() == R.id.stop_foreground_service)
                clickStopForegroundService();
            if (view.getId() == R.id.stop_bound_service)
                clickStopBoundService();
        }
    }

    private void clickStartService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void clickStopForegroundService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
    private void clickStopBoundService() {
        if (isServiceConnected){
            unbindService(serviceConnection);
            isServiceConnected = false;
        }
    }
}