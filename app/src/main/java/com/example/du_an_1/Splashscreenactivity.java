package com.example.du_an_1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;


public class Splashscreenactivity extends AppCompatActivity {
    LottieAnimationView lottie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreenactivity);

        lottie = findViewById(R.id.lottie);
        lottie.animate().translationY(2000).setDuration(2700).setStartDelay(2900);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splashscreenactivity.this, Login.class);
                startActivity(intent);
            }
        }, 3000);
    }

    public static class chitietDonHang {
        private int orderId;
        private String foodId;
        private int price;
        private int quantity;



        public chitietDonHang(int orderId, String foodId, int price, int quantity) {
            this.orderId = orderId;
            this.foodId = foodId;
            this.price = price;
            this.quantity = quantity;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getFoodId() {
            return foodId;
        }

        public void setFoodId(String foodId) {
            this.foodId = foodId;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}