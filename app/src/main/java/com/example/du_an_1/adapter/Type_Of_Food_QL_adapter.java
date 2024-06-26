package com.example.du_an_1.adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.Fragment.Frag_load_2;
import com.example.du_an_1.Fragment.QuanLyLoaiSanPhamKD;
import com.example.du_an_1.Fragment.QuanLySanLoaiPham_NKD_Fragment;
import com.example.du_an_1.NavigationQuanLy;
import com.example.du_an_1.R;
import com.example.du_an_1.model.Type_Of_Food;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.List;

public class Type_Of_Food_QL_adapter extends RecyclerView.Adapter<Type_Of_Food_QL_adapter.ViewHolder>{
    Context context;
    int maLoai;
    List<Type_Of_Food> list;
    NavigationQuanLy navigationQuanLy;
    Type_Of_Food_DAO type_of_food_dao;
    QuanLySanLoaiPham_NKD_Fragment quanLyLoaiSanPham_nkd_fragment;
    QuanLyLoaiSanPhamKD quanLyLoaiSanPham_kd_fragment;
    public OnclickItem onclickItem;
    int trangthai = 0;
    public Type_Of_Food_QL_adapter(Context context, List<Type_Of_Food> list, int trangthai, OnclickItem onclickItem) {
        this.context = context;
        this.list = list;
        navigationQuanLy = (NavigationQuanLy) context;
        this.trangthai = trangthai;
        type_of_food_dao = new Type_Of_Food_DAO(context);
        quanLyLoaiSanPham_nkd_fragment = new QuanLySanLoaiPham_NKD_Fragment();
        quanLyLoaiSanPham_kd_fragment = new QuanLyLoaiSanPhamKD();
        this.onclickItem = onclickItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loai_food, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
           Type_Of_Food tof = list.get(position);
        holder.tv_ma_sp.setText("Mã: "+tof.getMaLoai());
        holder.tv_title.setText(tof.getTenLoai());
        Picasso.get().load(Uri.parse(tof.getHinhAnh())).into(holder.img_pic);
        if (trangthai ==1){
            holder.tv_btn_delete.setText("????");
            holder.tv_btn_update.setText("Restore");
        }else {
            holder.tv_btn_delete.setText("Hide");
            holder.tv_btn_update.setText("Update");
        }

        holder.tv_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trangthai == 1) {
                    xoa();
                } else {
                    ngungKD();
                }
            }
            private void xoa() {
                Type_Of_Food TOF = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("Không thể xóa Loại sản phẩm");

                builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context, "Đã hủy thao tác", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }

            private void ngungKD() {
                Type_Of_Food TOF = list.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setIcon(R.drawable.baseline_warning_amber_24);
                builder.setMessage("Xác nhận muốn ngừng kinh doanh sản phẩm này");

                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (type_of_food_dao.DeleteRow(TOF) > 0) {
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            FragmentManager manager = navigationQuanLy.getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.framelayout, new Frag_load_2()).commit();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(context, "Đã hủy thao tác", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        holder.tv_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trangthai == 1) {
                    khoiphuc();
                } else {
                    onclickItem.updateCategory(position);
                }
            }

            private void khoiphuc() {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có muốn khôi phục sản phẩm này ?");
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
                        Type_Of_Food lsp = list.get(position);
                        if (type_of_food_dao.KhoiphucRow(lsp) > 0) {
                            Toast.makeText(context, "Khôi phục thành công", Toast.LENGTH_SHORT).show();
                            locLSP(trangthai);
                            FragmentManager manager = navigationQuanLy.getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.framelayout, new Frag_load_2()).commit();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(context, "Khôi phục thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title, tv_btn_delete, tv_ma_sp, tv_btn_update;
        ImageView img_pic;
        private byte[] currentImage;
        ConstraintLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ma_sp = itemView.findViewById(R.id.tv_ma_sp);
            tv_title = itemView.findViewById(R.id.tv_title_food);
            tv_btn_delete = itemView.findViewById(R.id.tv_btn_delete);
            tv_btn_update = itemView.findViewById(R.id.tv_btn_update);
            img_pic = itemView.findViewById(R.id.img_pic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
        public void setCurrentImage(byte[] currentImage) {
            this.currentImage = currentImage;
        }

        public byte[] getCurrentImage() {
            return currentImage;
        }


    }
    public void locLSP(int check) {
        if (check == 0) {
            list.clear();
            list.addAll(type_of_food_dao.getAllTY(trangthai));
            notifyDataSetChanged();
        } else if (check == 1) {
            list.clear();
            list.addAll(type_of_food_dao.getAllTY(trangthai));
            notifyDataSetChanged();
        }
    }
    public interface OnclickItem{
        void updateCategory(int position);
    }
}
