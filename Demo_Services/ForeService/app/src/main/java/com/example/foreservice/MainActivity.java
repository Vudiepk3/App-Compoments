package com.example.foreservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
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
        Song song = new Song("Bai hat", "Ca si", R.drawable.img_music, R.raw.file_music);

        Intent intent = new Intent(this, MyService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object song", song);
        intent.putExtras(bundle);

        startService(intent);
    }

    private void clickStopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}