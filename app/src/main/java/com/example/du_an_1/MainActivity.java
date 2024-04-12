package com.example.du_an_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.du_an_1.DAO.DAO_GioHang;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.adapter.FoodAdapter_Home;
import com.example.du_an_1.adapter.FoodAdapter_home2;
import com.example.du_an_1.adapter.Type_Of_Food_Adapter;
import com.example.du_an_1.model.Food;
import com.example.du_an_1.model.Type_Of_Food;
import com.example.du_an_1.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    UserDao user_dao;
    TextView tv_name;
    public static DAO_GioHang dao_gioHang;
    public static DAO_chitietDonHang dao_chitietDonHang;
    public static Food_DAO food_dao;
    public static User user;
    Login login;
    private RecyclerView.Adapter adapter1, adapter2, adapter3;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList, recyclerViewListFood;
    List<User> listUser = new ArrayList<>();
    List<Type_Of_Food> list = new ArrayList<>();
    List<Food> listFood = new ArrayList<>();
    Type_Of_Food_DAO type_of_food_dao;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String usernameLogged = sharedPreferences.getString("USERNAME", "");

        imageView = findViewById(R.id.img_avatar);
        tv_name = findViewById(R.id.tv_title_food);
        user_dao = new UserDao(this);

        String username = user_dao.getTenTV(usernameLogged);
        int userID = Integer.parseInt(user_dao.getMaND(usernameLogged));
        ShowDetailActivity_2.userID = userID;
        FoodAdapter.userID = userID;

        Log.i("SQLite", "Mã: " + username);
        if (usernameLogged == "Admin") {
            tv_name.setText("Hi Admin");
        } else {
            tv_name.setText("Hi " + username);
        }
        list = new ArrayList<>();
        type_of_food_dao = new Type_Of_Food_DAO(this);
        food_dao = new Food_DAO(this);
        listFood = new ArrayList<>();

        bottomNavigation();
        recyclerViewListFood();
        recyclerViewCategory();
        recyclerViewPopular();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Profile.class));
            }
        });

    }



    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout settingBtn = findViewById(R.id.setting_btn);
        LinearLayout profile = findViewById(R.id.profile_btn);
        LinearLayout support = findViewById(R.id.support_btn);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(MainActivity.this, Cart_activity.class);
                startActivity(ic);
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(MainActivity.this, Support.class);

                startActivity(ic);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(MainActivity.this, Profile.class);

                startActivity(ic);

//                startActivity(new Intent(MainActivity.this, Profile.class));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(MainActivity.this, MainActivity.class);

                startActivity(ic);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(MainActivity.this, Setting.class);

                startActivity(ic);
            }
        });
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recycler_categories);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        list = type_of_food_dao.getAllTY(0);
        adapter1 = new Type_Of_Food_Adapter(this, list);
        recyclerViewCategoryList.setAdapter(adapter1);
        context = (MainActivity) this;
        type_of_food_dao = new Type_Of_Food_DAO(this);
        adapter1.notifyDataSetChanged();


    }
    private void recyclerViewPopular(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recycler_popular);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);


        listFood = food_dao.getAll(0);
        adapter2 = new FoodAdapter_Home(this, listFood);
        recyclerViewPopularList.setAdapter(adapter2);
        context = (MainActivity) this;
        food_dao = new Food_DAO(this);
        adapter2.notifyDataSetChanged();


    }

    private void recyclerViewListFood() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewListFood = findViewById(R.id.recycler_list_fodd);
        recyclerViewListFood.setLayoutManager(linearLayoutManager);

        listFood = food_dao.getAll(0);
        adapter3 = new FoodAdapter_home2(this, listFood);
        recyclerViewListFood.setAdapter(adapter3);
        context = (MainActivity) this;
        food_dao = new Food_DAO(this);
        adapter3.notifyDataSetChanged();


    }
}
