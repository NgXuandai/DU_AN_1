package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Support extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);


        Button buttonSendEmail = findViewById(R.id.buttonSendEmail);
        Button buttonCallHotline = findViewById(R.id.buttonCallHotline);


        buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        buttonCallHotline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callHotline();
            }
        });


    }
    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:duongquocchung210@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Yêu cầu hỗ trợ");
        intent.putExtra(Intent.EXTRA_TEXT, "Xin vui lòng nhập ý kiến hoặc yêu cầu hỗ trợ của bạn ở đây...");
        startActivity(Intent.createChooser(intent, "Gửi Email"));
    }

    private void callHotline() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:0345132209"));
        startActivity(intent);
    }


}