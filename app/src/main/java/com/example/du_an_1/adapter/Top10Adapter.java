package com.example.du_an_1.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.du_an_1.R;
import com.example.du_an_1.model.Food;

import java.util.ArrayList;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.ViewHolder> {

    private Context context ;
    ArrayList<Food> list;

    public Top10Adapter(Context context, ArrayList<Food> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.txt_maFood.setText("Mã sản phẩm: "+list.get(position).getMaFood());
       holder.txt_tenFood.setText("Tên sản phẩm: "+list.get(position).getTenFood());
       holder.txt_soLuongMua.setText("Số lượng bán ra: "+String.valueOf(list.get(position).getSoluongdamua()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  ViewHolder extends RecyclerView.ViewHolder{
        TextView txt_maFood,txt_tenFood,txt_soLuongMua;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_maFood=itemView.findViewById(R.id.txt_maFood);
            txt_tenFood=itemView.findViewById(R.id.txt_tenFood);
            txt_soLuongMua=itemView.findViewById(R.id.txt_soLuongMua);
        }
    }
}
