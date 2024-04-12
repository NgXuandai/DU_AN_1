package com.example.du_an_1.Domain;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.du_an_1.DAO.DAO_GioHang;

import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.DonHang;
import com.example.du_an_1.R;
import com.example.du_an_1.model.GioHang;
import com.example.du_an_1.model.User;

public class OrderCard extends LinearLayout {
    private GioHang order;
    Button btnConfirm;

    DAO_GioHang dao_gioHang;
    DonHang donHang;
    User user;
    UserDao userDao;
    String us, pass;

    public OrderCard(Context context){
        super(context);
    }

    public OrderCard(Context context, GioHang order) {
        super(context);
        this.order = order;
        initControl(context);
    }

    private void initControl(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.order_card, this);
        dao_gioHang = new DAO_GioHang(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String usernameLogged = sharedPreferences.getString("USERNAME", "");
        userDao = new UserDao(context);
        user = userDao.getUserByUsername(usernameLogged);

        TextView tvDate = findViewById(R.id.tvDateMakeOrder);
        TextView tvPrice = findViewById(R.id.tvOrderPrice);
        TextView tvAddress = findViewById(R.id.tvOrderAddress);
        TextView tvStatus = findViewById(R.id.tvOrderStatus);

        btnConfirm = findViewById(R.id.btnConfirmOrder);
        btnConfirm.setOnClickListener(view -> {


            if ((user.getVaiTro() == 1)) {
                btnConfirm.setEnabled(false);
            } else {
                if (order.getStatus().equals("Coming")){
                    order.setStatus("Delivered");
                    dao_gioHang.updateOrder(order);
                    Toast.makeText(context, "Đã cập nhật lại trạng thái!", Toast.LENGTH_SHORT).show();
                }
            }


                        if ((user.getVaiTro() == 1)) {
                            if (order.getStatus().equals("succes")) {
                                order.setStatus("Coming");
                                btnConfirm.setEnabled(true);
                                dao_gioHang.updateOrder(order);
                                Toast.makeText(context, "Đã xác nhận!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            btnConfirm.setText("chờ xác nhận");
                            btnConfirm.setEnabled(false);
                        }



        });
        if(order.getStatus().equals("Delivered")){
            btnConfirm.setEnabled(false);
        }
        if(order.getStatus().equals("Coming")){
            btnConfirm.setEnabled(true);
        }
        if(order.getStatus().equals("succes")){
            btnConfirm.setEnabled(true);
            btnConfirm.setText("Xác nhận");

        }


        if (order.getStatus().equals("succes") == true){
            if(order.getStatus().equals("Coming")){
                btnConfirm.setEnabled(true);
            }
        }
        if(order.getStatus().equals("huy")){
            btnConfirm.setText("ĐÃ HỦY ĐƠN");
            btnConfirm.setEnabled(false);
        }


        tvDate.setText(order.getDateOfOrder());
        tvAddress.setText(order.getAddress());
        tvPrice.setText(order.getTotalValue()+"");
        tvStatus.setText(order.getStatus());
    }


}
