package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.du_an_1.DAO.DAO_GioHang;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.Domain.CartCard;
import com.example.du_an_1.Domain.OrderCard;
import com.example.du_an_1.model.Food;
import com.example.du_an_1.model.GioHang;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cart_activity extends AppCompatActivity {
    public static LinearLayout cartContainer;
    private static String status;
    private LinearLayout btnDangDen, btnLichSu, btnGioHang;
    private TextView tvGioHang, tvDangDen, tvLichSu;
    UserDao user_dao ;
    DAO_GioHang dao_gioHang;
    DAO_chitietDonHang dao_chitietDonHang;
    Food_DAO food_dao;
    List<Splashscreenactivity.chitietDonHang> list = new ArrayList<>();
    String mafoood;
    int ma_ctd, mangdung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String usernameLogged = sharedPreferences.getString("USERNAME", "");

        cartContainer = findViewById(R.id.cartContainer);
        user_dao = new UserDao(this);
        dao_chitietDonHang = new DAO_chitietDonHang(this);
        dao_gioHang = new DAO_GioHang(this);
        food_dao = new Food_DAO(this);
        list = new ArrayList<>();

        Intent i = getIntent();
        mangdung = Integer.valueOf(user_dao.getMaND(usernameLogged));
        ma_ctd = i.getIntExtra("ma",0);
        mafoood = dao_chitietDonHang.getIdmF(ma_ctd);

        referencesComponent();
        LoadOrder("craft");
        status = "craft";
        Log.i("SQLite", mangdung+"");
    }

    private void referencesComponent(){
        user_dao = new UserDao(this);
        dao_chitietDonHang = new DAO_chitietDonHang(this);

//        btnGioHang = findViewById(R.id.btnGioHang);
//        btnGioHang.setOnClickListener(view ->{
//            resetAttribute();
//            btnGioHang.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(this),R.color.menuDriver));
//            tvGioHang.setTextColor(Color.WHITE);
//
//            LoadOrder("craft");
//        });
//
//        btnDangDen = findViewById(R.id.btnDangDen);
//        btnDangDen.setOnClickListener(view->{
//            resetAttribute();
//            btnDangDen.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(this),R.color.menuDriver));
//            tvDangDen.setTextColor(Color.WHITE);
//
//            LoadOrder("coming");
//        });
//
//        btnLichSu = findViewById(R.id.btnLichSu);
//        btnLichSu.setOnClickListener(view -> {
//            resetAttribute();
//            btnLichSu.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(this),R.color.menuDriver));
//            tvLichSu.setTextColor(Color.WHITE);
//
//            LoadOrder("history");
//        });

//        tvGioHang = findViewById(R.id.tvGioHang);
        tvDangDen = findViewById(R.id.tvDangDen);
        tvLichSu = findViewById(R.id.tvLichSu);


        Button btnThanhToan = findViewById(R.id.btnThanhToan);
        btnThanhToan.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction()== MotionEvent.ACTION_DOWN){
                    btnThanhToan.setTextColor(Color.RED);
                }
                if (motionEvent.getAction()== MotionEvent.ACTION_UP){
                    btnThanhToan.setTextColor(Color.WHITE);
                }
                return false;
            }
        });
        btnThanhToan.setOnClickListener(view ->{
            if(!status.equals("craft"))
                return;

            dao_gioHang = new DAO_GioHang(this);
            Cursor cursor = dao_gioHang.getCart(mangdung);
            rememberIDUser(mangdung);
            if(!cursor.moveToFirst())
                return;

//            PaymentActivity.user = mangdung;
            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra("orderId", cursor.getInt(0));
            startActivity(intent);
        });
        Button btnTroVe = findViewById(R.id.btnTrolai);
        btnTroVe.setOnClickListener(view ->{
            finish();
        });
    }
    public void rememberIDUser(int u) {
        SharedPreferences pref = getSharedPreferences("ID_USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("USER_ID", u);
        // luu lai toan bo du lieu
        edit.commit();
    }

    private void LoadOrder(String type){
        user_dao = new UserDao(this);
        food_dao = new Food_DAO(this);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String usernameLogged = sharedPreferences.getString("USERNAME", "");
        mangdung = Integer.valueOf(user_dao.getMaND(usernameLogged));


        status = type;
        cartContainer.removeAllViews();
        switch (type) {
            case "craft":{
                Cursor cursor = dao_gioHang.getCart(mangdung);
                Log.i("SQLite", String.valueOf(cursor));
                if (!cursor.moveToFirst())
                    return;
                cursor.moveToFirst();
                dao_chitietDonHang = new DAO_chitietDonHang(this);
                ArrayList<Splashscreenactivity.chitietDonHang> orderDetailArrayList = dao_chitietDonHang.getCartDetailList(cursor.getInt(0));
//                list = dao_chitietDonHang.getCartDetailList(cursor.getInt(0));
                if (orderDetailArrayList.size() > 0) {
                    Food food;
                    for (Splashscreenactivity.chitietDonHang orderDetail : orderDetailArrayList) {
                        food = food_dao.getFoodById(mafoood);
                        CartCard card = new CartCard(this, food, orderDetail);
                        cartContainer.addView(card);
                    }
                }
                break;}
            case "coming": {
                ArrayList<GioHang> orderArrayList = dao_gioHang.getOrderOfUser(Integer.valueOf(user_dao.getMaND(usernameLogged)), "Coming");
                if (orderArrayList.size() > 0) {
                    for (GioHang order : orderArrayList) {
                        OrderCard card = new OrderCard(this, order);
                        card.setOnClickListener(view -> {
                            Intent intent = new Intent(this, ViewOrderActivity.class);
                            intent.putExtra("order", order);
                            startActivity(intent);
                        });
                        cartContainer.addView(card);
                    }
                }
                break;
            }
            case "history": {
                ArrayList<GioHang> orderArrayList = dao_gioHang.getOrderOfUser(Integer.valueOf(user_dao.getMaND(usernameLogged)), "Delivered");
                if (orderArrayList.size() > 0) {
                    for (GioHang order : orderArrayList) {
                        OrderCard card = new OrderCard(this, order);
                        card.setOnClickListener(view -> {
                            Intent intent = new Intent(this, ViewOrderActivity.class);
                            intent.putExtra("order", order);
                            startActivity(intent);
                        });
                        cartContainer.addView(card);
                    }
                }
                break;
            }
        }
    }
    private void resetAttribute(){
        btnGioHang.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(this),R.drawable.bg_white));
        btnDangDen.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_white));
        btnLichSu.setBackground(ContextCompat.getDrawable(this,R.drawable.bg_white));

        tvGioHang.setTextColor(Color.BLACK);
        tvLichSu.setTextColor(Color.BLACK);
        tvDangDen.setTextColor(Color.BLACK);
    }
}