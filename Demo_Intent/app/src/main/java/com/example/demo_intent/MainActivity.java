package com.example.demo_intent;

import android.content.ActivityNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String link = "https://play.google.com/store/apps/details?id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Kích hoạt chế độ Edge-to-Edge
        setContentView(R.layout.activity_main); // Thiết lập giao diện chính
        getID(); // Gọi phương thức getID() để gán sự kiện cho các button
    }

    private void getID(){
        // Bắt đầu Activity khác
        Button btnStartActivity = findViewById(R.id.btnStartActivity);
        btnStartActivity.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity1.class);
            startActivity(intent); // Khởi động Activity1
        });

        // Chia sẻ link ứng dụng
        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(v -> {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, "This is an awesome app for your android mobile, Check it ..." + 
                        link + getPackageName()); // Thêm nội dung chia sẻ
                startActivity(Intent.createChooser(shareIntent, "Share Link")); // Mở giao diện chia sẻ
            } catch (Exception e) {
                Log.e("ShareIntent", "Error while sharing content: " + e.getMessage()); // Ghi log khi xảy ra lỗi
            }
        });

        // Đánh giá ứng dụng trên CHPlay
        Button btnRateApp = findViewById(R.id.btnRateApp);
        btnRateApp.setOnClickListener(v -> {
            try {
                Intent rateIntent = new Intent(Intent.ACTION_VIEW);
                rateIntent.setData(Uri.parse(link + getPackageName())); // Mở liên kết đến trang ứng dụng trên Google Play
                rateIntent.setPackage("com.android.vending"); // Xác định ứng dụng để mở liên kết (CHPlay)
                startActivity(rateIntent); // Mở CHPlay để đánh giá ứng dụng
            } catch (ActivityNotFoundException e) {
                Intent rateIntent = new Intent(Intent.ACTION_VIEW);
                rateIntent.setData(Uri.parse("market://details?id=" + getPackageName())); // Sử dụng liên kết thay thế khi không tìm thấy CHPlay
                startActivity(rateIntent); // Mở CHPlay để đánh giá ứng dụng
            }
        });
    }
}
