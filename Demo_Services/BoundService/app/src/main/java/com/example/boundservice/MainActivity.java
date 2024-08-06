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
    private boolean isServiceConnection = false;
    private final ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicBoundService.MyBinder myBinder = (MusicBoundService.MyBinder) iBinder;
            MusicBoundService myMusicBoundService = myBinder.getMusicBoundService();
            myMusicBoundService.startMusic();
            isServiceConnection = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isServiceConnection = false;
        }
    };
    private Button btnStart, btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFromWidget();
        AddEventButton();
    }
    public void getFromWidget(){
        btnStart = findViewById(R.id.start_service);
        btnStop = findViewById(R.id.stop_service);
    }

    public void AddEventButton(){
        btnStart.setOnClickListener(new MyButtonEvent());
        btnStop.setOnClickListener(new MyButtonEvent());
    }
    protected class MyButtonEvent implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.start_service)
                clickStartService();
            if (view.getId() == R.id.stop_service)
                clickStopService();
        }
    }

    private void clickStartService() {
        Intent intent = new Intent(this, MusicBoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void clickStopService() {
        if(isServiceConnection) {
            unbindService(serviceConnection);
            isServiceConnection = false;
        }
    }
}
