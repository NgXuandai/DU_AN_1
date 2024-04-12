package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.model.User;

public class Change_pass extends AppCompatActivity {
    EditText edt_oldpass,edt_newpass,edt_confirm;
    Button btn_doimatkhau,btn_huy;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        edt_oldpass = findViewById(R.id.edt_oldpass);
        edt_newpass = findViewById(R.id.edt_newpass);
        edt_confirm = findViewById(R.id.edt_confirm);
        btn_doimatkhau = findViewById(R.id.btn_doimatkhau);
        btn_huy = findViewById(R.id.btn_huy);
        userDao = new UserDao(Change_pass.this);


        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_oldpass.setText("");
                edt_newpass.setText("");
                edt_confirm.setText("");
            }
        });
        btn_doimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();

            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Change_pass.this,Setting.class));
            }
        });



    }
    private void changePassword() {
        String oldPassword = edt_oldpass.getText().toString();
        String newPassword = edt_newpass.getText().toString();
        String confirmPassword = edt_confirm.getText().toString();

        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Mật khẩu mới không trùng khớp", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String maDN = pref.getString("USERNAME", "");

        User user = new User();
        user.setMaDN(maDN);
        user.setMatKhau(newPassword);

        long result = userDao.changePass(user);

        if (result > 0) {
            Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
        }
    }}