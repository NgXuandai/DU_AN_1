package com.example.du_an_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.Fragment.Fragment_quanLyLoaiSp;
import com.example.du_an_1.Fragment.Fragmnet_quanLyHoaDon;
import com.example.du_an_1.Fragment.Frament_quanLySP;
import com.example.du_an_1.Fragment.fragment_quanLyNguoiDung;
import com.example.du_an_1.model.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class NavigationQuanLy extends AppCompatActivity {
    DrawerLayout drawerLayoutQuanLy;
    NavigationView navigationView;
    List<User> list;
    View header;
    UserDao user_dao;
    User dto_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_quan_ly);
        drawerLayoutQuanLy = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        FrameLayout frameLayout = findViewById(R.id.framelayout);
        list = new ArrayList<>();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản Lý Hóa Đơn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.menunavbar);
        //xet icon ve mau ban goc
        navigationView.setItemIconTintList(null);
        // Gọi hiển thị tên với avatar người dùng // nhận dữ liệu từ login
        header = navigationView.getHeaderView(0);
        user_dao = new UserDao(this);
        Intent intent = getIntent();
        dto_user = (User) intent.getSerializableExtra("user");
        list.add(dto_user);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayoutQuanLy.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                int check = 0;
                if (item.getItemId() == R.id.QLHome) {
                    toolbar.setTitle("Quản lý hóa đơn");
                    fragment = new Fragmnet_quanLyHoaDon();
                } else if (item.getItemId() == R.id.QLLSP) {
                    toolbar.setTitle("Quản lý loại sản phẩm");
                    fragment = new Fragment_quanLyLoaiSp()  ;
                }else if (item.getItemId() == R.id.QLSP) {
                    toolbar.setTitle("Quản lý sản phẩm");
                    fragment = new Frament_quanLySP();
                }
                else if (item.getItemId() == R.id.QLND) {
                    toolbar.setTitle("Quản lý người dùng");
                    fragment = new fragment_quanLyNguoiDung();
                }else if (item.getItemId() == R.id.QLDT) {
                    toolbar.setTitle("Quản lý doanh thu");
                    fragment = new fragment_quanLyNguoiDung();
                }else if (item.getItemId() == R.id.QLThoat) {
                    openDialog_DangXuat();
                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.framelayout, fragment).commit();
                    toolbar.setTitle(item.getTitle());
                }
                if (check == 0) {
                    drawerLayoutQuanLy.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framelayout, new Fragmnet_quanLyHoaDon()).commit();
    }
    public void openDialog_DangXuat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(NavigationQuanLy.this);
        builder.setTitle("ĐĂNG XUẤT");
        builder.setMessage("Bạn có chắc chắn muốn thoát không?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}