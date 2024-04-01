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



        bottomNavigation();


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


    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout settingBtn = findViewById(R.id.setting_btn);
        LinearLayout profile = findViewById(R.id.profile_btn);
        LinearLayout support = findViewById(R.id.support_btn);

//        Intent i = getIntent();
//        String user = i.getStringExtra("user");
//        user_dao = new User_DAO(this);
//        String username = user_dao.getTenTV(user);


        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Support.this, Support.class);
//                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Support.this, Profile.class);
//                ic.putExtra("user", user);
                startActivity(ic);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Support.this, MainActivity.class);
//                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Support.this, Setting.class);
//                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
    }


}