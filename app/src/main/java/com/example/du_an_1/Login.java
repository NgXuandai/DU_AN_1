package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.model.User;

public class Login extends AppCompatActivity {
    EditText edt_user_login,edt_pass_login;
    CheckBox chkRememberPass;
    Button btn_login;
    TextView txt_dangki,txt_forgot;
    UserDao userDao;

    String user, pass;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_pass_login = findViewById(R.id.edt_pass_login);
        edt_user_login = findViewById(R.id.edt_user_login);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        txt_dangki = findViewById(R.id.txt_dangki);
        txt_forgot = findViewById(R.id.txt_forgot);
        btn_login = findViewById(R.id.btn_login);
        userDao = new UserDao(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = pref.getString("USERNAME","");
        String pass = pref.getString("PASSWORD","");
        Boolean rem = pref.getBoolean("REMEMBER",false);

        edt_user_login.setText(user);
        edt_pass_login.setText(pass);
        chkRememberPass.setChecked(rem);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();

            }
        });
        txt_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Login.this, RegisterActivity2.class);
                        startActivity(intent);
                    }
                },1200);
            }
        });

        txt_dangki.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    txt_dangki.setTextColor(Color.RED);
                }
                if (event.getAction()== MotionEvent.ACTION_UP){
                    txt_dangki.setTextColor(Color.YELLOW);
                }
                return false;
            }
        });
        txt_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Forgot.class);
                startActivity(intent);
            }
        });

    }

    public void checkLogin(){
        user = edt_user_login.getText().toString();
        pass = edt_pass_login.getText().toString();

        if (user.trim().isEmpty() || pass.trim().isEmpty()){
            Toast.makeText(this, "Tài khoản hoặc mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }
        else {
            if (userDao.checkLogin(user,pass) > 0) {
                rememberUser(user,pass, chkRememberPass.isChecked());
                for (User obj : userDao.getAll()) {
                    if (obj.getMaDN().equalsIgnoreCase(user) && obj.getMatKhau().equalsIgnoreCase(pass)) {
                        if ((obj.getVaiTro() == 1)) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                    Intent i3 = new Intent(Login.this, NavigationQuanLy.class);
                                    startActivity(i3);
                                }
                            }, 1200);
                            return;
                        }
                        if (obj.getVaiTro() == 0) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    Intent ic = new Intent(Login.this, MainActivity.class);
                                    ic.putExtra("user", user);
                                    startActivity(ic);
                                    finish();
                                }
                            }, 1200);
                            return;
                        }
                        if (obj.getVaiTro() == 3) {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Tài khoản của bạn đã b dừng hoạt động. Liên hệ Admin để được hỗ trợ!", Toast.LENGTH_SHORT).show();
                                }
                            }, 1200);
                            return;
                        }
                    }
                }


            } else {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }

        }


    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {

            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }

        edit.commit();

    }
}