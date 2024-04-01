package com.example.du_an_1.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.du_an_1.DAO.DoanhThuDao;
import com.example.du_an_1.R;

import java.util.Calendar;


public class Fragment_DoanhThu extends Fragment {
    EditText edt_ngaybatdau,edt_ngaykethuc;
    Button btn_doanhthu;
    TextView txtKetQua;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__doanh_thu,container,false);

        edt_ngaybatdau = view.findViewById(R.id.edt_ngaybatdau);
        edt_ngaykethuc = view.findViewById(R.id.edt_ngaykethuc);
        btn_doanhthu = view.findViewById(R.id.btn_doanhthu);
        txtKetQua = view.findViewById(R.id.txtKetQua);

        Calendar calendar = Calendar.getInstance();


        edt_ngaybatdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay ="";
                                String thang = "";

                                if (dayOfMonth<10){
                                    ngay =  "0" + dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }


                                if ((month + 1) <10){
                                    thang = "0" + (month+1); // thang + 1 thay cho thang
                                }else {
                                    thang = String.valueOf(month+1); // thang + 1 thay cho thang
                                }
                                edt_ngaybatdau.setText(ngay + "/" + thang + "/" + year); // swap vị trí của ngày và tháng
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        edt_ngaykethuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay ="";
                                String thang = "";

                                if (dayOfMonth<10){
                                    ngay =  "0" + dayOfMonth;
                                }else {
                                    ngay = String.valueOf(dayOfMonth);
                                }


                                if ((month + 1) <10){
                                    thang = "0" + (month+1); // thang + 1 thay cho thang
                                }else {
                                    thang = String.valueOf(month+1); // thang + 1 thay cho thang
                                }
                                edt_ngaykethuc.setText(ngay + "/" + thang + "/" + year); // swap vị trí của ngày và tháng
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });


        btn_doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoanhThuDao doanhThuDao = new DoanhThuDao(getContext());
                String ngaybatdau = edt_ngaybatdau.getText().toString();
                String ngayketthuc = edt_ngaykethuc.getText().toString();
                int doanhthu =doanhThuDao.getDoanhThu(ngaybatdau,ngayketthuc);
                txtKetQua.setText(doanhthu + "VND");
            }
        });

        return view;
    }

}