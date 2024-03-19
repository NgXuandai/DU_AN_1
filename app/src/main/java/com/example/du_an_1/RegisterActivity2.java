package com.example.du_an_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.model.User;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity2 extends AppCompatActivity {

    EditText ed_fullname_register, ed_sdt_register,ed_username_register,ed_pass_register,ed_repass_register;
    Button btn_dangki_register,btn_login_acc;
    Context context;
    String fullName, phoneNumber, userName, passWord, conFirm;
    User user;
    UserDao userDao;
    List<User> list;
   Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ed_fullname_register = findViewById(R.id.ed_fullname_register);
        ed_sdt_register = findViewById(R.id.ed_sdt_register);
        ed_username_register = findViewById(R.id.ed_username_register);
        ed_pass_register = findViewById(R.id.ed_pass_register);
        ed_repass_register = findViewById(R.id.ed_repass_register);
        btn_dangki_register = findViewById(R.id.btn_dangki_register);
        btn_login_acc = findViewById(R.id.btn_login_acc);

        bien();


        btn_login_acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(RegisterActivity2.this, Login.class);
                        startActivity(intent);
                    }
                },1200);
            }
        });

        btn_dangki_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTaoTaiKhoan();

            }
        });


    }


    public  void dialogTaoTaiKhoan(){
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity2.this);
        builder.setTitle("Có");
        builder.setMessage("Bạn có đồng ý tạo tài khoản không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                fullName = ed_fullname_register.getText().toString().trim();
                phoneNumber = ed_sdt_register.getText().toString().trim();
                userName = ed_username_register.getText().toString().trim();
                passWord = ed_pass_register.getText().toString().trim();
                conFirm = ed_repass_register.getText().toString();
                user = new User();
                user.setHoTen(fullName);
                user.setsDt(phoneNumber);
                user.setMaDN(userName);
                user.setMatKhau(passWord);
                user.setVaiTro(0);




                validate();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void bien(){
        dialog = new Dialog(RegisterActivity2.this);
        userDao = new UserDao(dialog.getContext());
        list = new ArrayList<>();

    }

    public  int validate(){
        int check = 1;
        if (ed_fullname_register.getText().length() == 0|| ed_sdt_register.getText().length()==0|| ed_username_register.getText().length()==0|| ed_pass_register.getText().length()==0||ed_repass_register.getText().length()==0){
            Toast.makeText(RegisterActivity2.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        } else if (!conFirm.equals(passWord)) {
            Toast.makeText(RegisterActivity2.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            check=-1;
        }else {
            userDao.insert(user);
            dialog.dismiss();
            Intent intent = new Intent(RegisterActivity2.this, Login.class);
            startActivity(intent);;
            Toast.makeText(RegisterActivity2.this, "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
        }
        return check;
    }
}