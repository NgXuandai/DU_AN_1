package com.example.du_an_1.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.Fragment.Fragment_quanLy_DHD;
import com.example.du_an_1.Fragment.Fragment_quanLy_NDH;
import com.example.du_an_1.Fragment.fragment_quanLyNguoiDung;
import com.example.du_an_1.NavigationQuanLy;
import com.example.du_an_1.R;
import com.example.du_an_1.model.User;

import java.util.ArrayList;

public class Adapter_user extends RecyclerView.Adapter<Adapter_user.myViewHolder>{

    private Context context ;
    private ArrayList<User> arrayList ;
    private int check ;
    private UserDao userDao;
    Fragment_quanLy_DHD quanLy_dhd ;
    Fragment_quanLy_NDH quanLy_ndh ;
    NavigationQuanLy navigationQuanLy ;
    public Adapter_user(Context context, ArrayList<User> arrayList,int check) {
        this.context = context;
        this.arrayList = arrayList;
        this.check = check ;
        userDao = new UserDao(context);
        navigationQuanLy = (NavigationQuanLy) context;
    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,null,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        User user = arrayList.get(position);
        holder.tvTen.setText("Tên : "+user.getHoTen());
        holder.tvMa.setText("Mã : "+user.getMaDN());
        holder.tvSdt.setText("SĐT : "+user.getsDt());

        if (check == 1){
            if (arrayList.get(position).getVaiTro() == 0){
                holder.btn_doi.setText("Dừng");
            }
            holder.btn_doi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doiTrangthai(user.getMaUser(),1);
                }
            });
        }else if (check == 2) {
            if(arrayList.get(position).getVaiTro() == 3) {
                holder.btn_doi.setText("Khôi phục");
            }
            holder.btn_doi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    doiTrangthai(user.getMaUser(),2);
                }
            });
        }

    }
    private void doiTrangthai(int id_user,int check_1) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Bạn có muốn thay đổi trạng thái của người dùng ?");
        builder.setTitle("Thông báo");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (check_1 == 1){
                    if (userDao.updateTrangThai(id_user,3) > 0){
                        arrayList.clear();
                        arrayList.addAll(userDao.getAllA(0));

                        FragmentManager manager = navigationQuanLy.getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.framelayout, new fragment_quanLyNguoiDung()).commit();

                        notifyDataSetChanged();
                        Toast.makeText(context, "Đã hoàn thành", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Lỗi thay đổi", Toast.LENGTH_SHORT).show();
                    }
                } else if (check_1 == 2) {
                    if (userDao.updateTrangThai(id_user,0) > 0){
                        arrayList.clear();
                        arrayList.addAll(userDao.getAllA(3));
                        notifyDataSetChanged();
                        FragmentManager manager = navigationQuanLy.getSupportFragmentManager();
                        manager.beginTransaction().replace(R.id.framelayout, new fragment_quanLyNguoiDung()).commit();

                        Toast.makeText(context, "Đã hoàn thành", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "Lỗi thay đổi", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        Dialog dialog = builder.create();
        dialog.show();
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    public class myViewHolder extends RecyclerView.ViewHolder {
        ImageView img ;
        TextView tvTen,tvMa,tvSdt ;
        Button btn_doi ;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.img_user);

            tvTen = itemView.findViewById(R.id.tv_name_user);
            tvMa  = itemView.findViewById(R.id.tv_ma_user);
            tvSdt = itemView.findViewById(R.id.tv_sdt_user);

            btn_doi = itemView.findViewById(R.id.btn_dung);
        }
    }

}
