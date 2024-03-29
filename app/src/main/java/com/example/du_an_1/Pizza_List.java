package com.example.du_an_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.DAO.UserDao;

import com.example.du_an_1.adapter.Type_Of_Food_ListCategory_Adapter;
import com.example.du_an_1.model.Food;
import com.example.du_an_1.model.Type_Of_Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Pizza_List extends AppCompatActivity {
    private RecyclerView recyclerViewCategoryList;
    private RecyclerView.Adapter adapter1, adapter_2;
    TextView tv_title_food, tv_listPizza;
    RecyclerView rcv_pizza;

    FloatingActionButton floatingCart;

    UserDao user_dao;
     Food_DAO food_dao;
     Type_Of_Food_DAO type_of_food_dao;
    FoodAdapter adapter_food;
    Context context;
    List<Type_Of_Food> list_2 = new ArrayList<>();
    List<Food> list = new ArrayList<>();
    String title;
    ScrollView scr_list_food;
    int theLoai_1, theLoai_2,tt;
    FragmentContainerView fragmentContainerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_list);
//        floatingAdd = findViewById(R.id.float_add_btn);
        tv_title_food = findViewById(R.id.tv_title_food);
        tv_listPizza = findViewById(R.id.tv_listPizza);
        Intent i = getIntent();
        title = i.getStringExtra("title");
        tv_title_food.setText(title);
        tv_listPizza.setText("Danh Sách "+title);
        //
        list = new ArrayList<>();
        food_dao = new Food_DAO(this);

        list_2 = new ArrayList<>();
        type_of_food_dao = new Type_Of_Food_DAO(this);

        theLoai_1 = Integer.parseInt(type_of_food_dao.getTheLoai(title));
        theLoai_2 = Integer.parseInt(food_dao.getLoai(theLoai_1));
        tt = Integer.parseInt(food_dao.getTT(theLoai_1));


        Intent g = getIntent();
        String user = g.getStringExtra("user");
        user_dao = new UserDao(this);
        String username = user_dao.getTenTV(user);

        recyclerViewPizza();
        recyclerViewCategory();
        bottomNavigation();

    }
    private void bottomNavigation(){
        floatingCart = findViewById(R.id.float_cart_btn);
        LinearLayout homeBtn = findViewById(R.id.home_btn);
        LinearLayout settingBtn = findViewById(R.id.setting_btn);
        LinearLayout profile = findViewById(R.id.profile_btn);
        LinearLayout support = findViewById(R.id.support_btn);

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        user_dao = new UserDao(this);
        String username = user_dao.getTenTV(user);


        floatingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Pizza_List.this, Cart_activity.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Pizza_List.this, MainActivity.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Pizza_List.this, Support.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Pizza_List.this, Profile.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ic = new Intent(Pizza_List.this, Setting.class);
                ic.putExtra("user", user);
                startActivity(ic);
            }
        });
    }
    private void recyclerViewCategory() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recycler_categories);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        list_2 = type_of_food_dao.getAllTY(0);
        adapter1 = new Type_Of_Food_ListCategory_Adapter(this,list_2);
        recyclerViewCategoryList.setAdapter(adapter1);
        context = (Pizza_List) this;
        type_of_food_dao = new Type_Of_Food_DAO(this);
        adapter1.notifyDataSetChanged();

    }

    private void recyclerViewPizza(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        scr_list_food = findViewById(R.id.scr_list_food);
        rcv_pizza = findViewById(R.id.rcv_pizza);
        rcv_pizza.setLayoutManager(linearLayoutManager);
            if(theLoai_1==theLoai_2){
                list = food_dao.getAllB(theLoai_1);
                adapter_2 = new FoodAdapter(this,list);
                rcv_pizza.setAdapter(adapter_2);
                context = (Pizza_List) this;
                food_dao = new Food_DAO(this);
                adapter_2.notifyDataSetChanged();
            }
    }
}