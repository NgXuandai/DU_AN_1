package com.example.du_an_1.Domain;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.du_an_1.Cart_activity;
import com.example.du_an_1.DAO.DAO_chitietDonHang;
import com.example.du_an_1.DAO.Food_DAO;

import com.example.du_an_1.R;
import com.example.du_an_1.Splashscreenactivity;
import com.example.du_an_1.model.Food;

import java.util.List;

public class CartCard extends LinearLayout{
    private Food food;
    private String restaurantName;
    private Splashscreenactivity.chitietDonHang card;
    private boolean activateControl;
    private int quantity;
    List<Food> list;

    String tenfood;
    Food_DAO food_dao ;
    DAO_chitietDonHang dao_chitietDonHang;
    private byte[] currentImage;
    public CartCard(Context context) {
        super(context);
        initControl(context);
    }

    public CartCard(Context context, Food food, Splashscreenactivity.chitietDonHang card) {
        super(context);
        this.food = food;
        this.card = card;
        this.activateControl = true;
        initControl(context);
    }

    public CartCard(Context context, Food food, Splashscreenactivity.chitietDonHang card, boolean activateControl) {
        super(context);
        this.food = food;
        this.card = card;
        this.activateControl = activateControl;
        initControl(context);
    }

    @SuppressLint("SetTextI18n")
    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.cart_card, this);
        int ma = card.getOrderId();
        quantity = card.getQuantity();
        dao_chitietDonHang = new DAO_chitietDonHang(context);
        String mafod = dao_chitietDonHang.getIdmF(ma);
        food_dao = new Food_DAO(context);
        Log.i("SQLite", "Ma: "+ma);

        ImageView image = findViewById(R.id.imageCartFood);
        TextView tvName = findViewById(R.id.tvFoodNameCart);
        TextView tvPrice = findViewById(R.id.tvFoodPriceCart);
        tenfood = String.valueOf(tvName);

                // For quantity
        TextView tvQuantity = findViewById(R.id.tvFoodQuantity_Cart);
        Button btnSub = findViewById(R.id.btnSubQuantity_Cart);
        btnSub.setOnClickListener(view -> {
            if(quantity > 1){
                dao_chitietDonHang = new DAO_chitietDonHang(context);
                quantity--;
                tvQuantity.setText("" + quantity);
                card.setQuantity(quantity);
                dao_chitietDonHang.updateQuantity(card);
            }
        });

        Button btnAdd = findViewById(R.id.btnAddQuantity_Cart);
        btnAdd.setOnClickListener(view -> {
            dao_chitietDonHang = new DAO_chitietDonHang(context);
            quantity++;
            tvQuantity.setText("" + quantity);
            card.setQuantity(quantity);
            dao_chitietDonHang.updateQuantity(card);

        });

        Button btnDelete = findViewById(R.id.btnDeleteCartItem);
        btnDelete.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setMessage("Bạn có muốn xóa món " + tenfood + " không?");
            dialog.setPositiveButton("Có", (dialogInterface, i) -> {
                dao_chitietDonHang = new DAO_chitietDonHang(getContext());
                if(dao_chitietDonHang.deleteOrderDetailByOrderIdAndFoodId(ma)){
                    Cart_activity.cartContainer.removeView(this);
                    Toast.makeText(context, "Đã xóa thông tin!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Gặp lỗi khi xóa thông tin!", Toast.LENGTH_SHORT).show();
                }
            });
            dialog.setNegativeButton("Không", (dialogInterface, i) -> {});
            dialog.show();
        });

        LinearLayout layout = findViewById(R.id.layout_btn_delete);

        if(!activateControl) {
            btnDelete.setVisibility(GONE);
            layout.setBackgroundColor(Color.rgb(255, 70, 70));
            btnAdd.setEnabled(activateControl);
            btnSub.setEnabled(activateControl);
        }

        // Set information for cart card
        food_dao = new Food_DAO(context);
        image.setImageBitmap(food.convertByteArrayToBitmap(food_dao.getAnh2(card.getFoodId())));


//        byte [] imgdata = food.convertByteArrayToBitmap(food_dao.getAnh2(card.getFoodId())).getNinePatchChunk();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(imgdata, 0, imgdata.length);
//        image.setImageBitmap(bitmap);
        tvName.setText(food_dao.getTenFood(card.getFoodId()));
        tvPrice.setText(getRoundPrice(card.getPrice()));
        tvQuantity.setText("" + quantity);
    }

    private String getRoundPrice(int price){
        return Math.round(price) + " VNĐ";
    }
}
