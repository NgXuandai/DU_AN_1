package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Profile extends AppCompatActivity {
    private TextView txtTenND,tenDN,maDN,name,sdt;
    private TextView txtDoiPass , txtDonHang ;
    private Button btnSua ;
    private UserDao user_dao ;
    User user ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        bottomNavigation();
        txtTenND = findViewById(R.id.tennguoidung);
        tenDN = findViewById(R.id._tenDN);
        maDN = findViewById(R.id._maDN);
        name = findViewById(R.id._ten);
        txtDoiPass = findViewById(R.id.btn_change_pass_1);
        txtDonHang = findViewById(R.id.btn_donHang);
        sdt = findViewById(R.id._sdt_1);
        btnSua = findViewById(R.id.btn_Doi);

        // lấy ra tên đăng nhập
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String usernameLogged = sharedPreferences.getString("USERNAME", "");
        tenDN.setText(":  "+usernameLogged);
        user_dao = new UserDao(this);

        // lấy ra mã
        int userID = Integer.parseInt(user_dao.getMaND(usernameLogged));
        Log.d("aaa",""+userID);
        maDN.setText(":  "+userID);
        // lấy ra tên
        name.setText(":  "+user_dao.getTenTV(usernameLogged));
        txtTenND.setText(user_dao.getTenTV(usernameLogged));

        // lấy ra số điện thoại
        sdt.setText(":  "+user_dao.getsdtTV(usernameLogged));

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(usernameLogged);
            }
        });
        txtDoiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Setting.class);
                startActivity(i);
            }
        });
        txtDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Cart_activity.class);
                startActivity(i);
            }
        });

    }

    public void showDialog(String u){
         Dialog dialog = new Dialog(Profile.this);
         dialog.setContentView(R.layout.dialog_sua_nd);

         EditText edTen = dialog.findViewById(R.id.ed_ten_ND_sua);
         EditText edSDT = dialog.findViewById(R.id.ed_SDT_sua);
         EditText edmaDN = dialog.findViewById(R.id.ed_MaDN_sua);
         Button cancel = dialog.findViewById(R.id.btnCancelSua);
         Button save = dialog.findViewById(R.id.btnSaveSua);

         edTen.setText(user_dao.getTenTV(u));
         edSDT.setText(user_dao.getsdtTV(u));
         edmaDN.setText(u);

         cancel.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 dialog.dismiss();
             }
         });
         save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String name = edTen.getText().toString().trim();
                 String sdt = edSDT.getText().toString().trim();
                 String ma = edmaDN.getText().toString().trim();
                 if(name.equals(user_dao.getTenTV(u)) && sdt.equals(user_dao.getsdtTV(u)) && ma.equals(u)){
                     Toast.makeText(Profile.this, "Không có thay đổi nào", Toast.LENGTH_SHORT).show();
                 }else {
                     Log.d("aaa",name+sdt+ma);
                     user = new User();
                     user.setHoTen(name);
                     user.setsDt(sdt);
                     user.setMaDN(ma);
                     if (user_dao.Update(user)>0){
                         Toast.makeText(Profile.this, "Thành công", Toast.LENGTH_SHORT).show();
                     }else {
                         Toast.makeText(Profile.this, "Thất bại", Toast.LENGTH_SHORT).show();
                     }
                 }
                 dialog.dismiss();
             }
         });



         dialog.show();
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
                Intent ic = new Intent(Profile.this, Support.class);
//                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Profile.this, Profile.class);
//                ic.putExtra("user", user);
                startActivity(ic);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Profile.this, MainActivity.class);
//                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Profile.this, Setting.class);
//                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
    }
}