package com.example.du_an_1.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.R;
import com.example.du_an_1.adapters.Adapter_user;
import com.example.du_an_1.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class Fragment_quanLy_DHD extends Fragment {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private UserDao userDao ;
    private Adapter_user adapter_user ;
    private ArrayList<User> list_user = new ArrayList<>() ;
    public Fragment_quanLy_DHD() {
        // Required empty public constructor
    }


    public static Fragment_quanLy_DHD newInstance() {
        Fragment_quanLy_DHD fragment = new Fragment_quanLy_DHD();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly__d_h_d, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        floatingActionButton = view.findViewById(R.id.fab_users);
        recyclerView = view.findViewById(R.id.id_recycler_users_1);
        userDao = new UserDao(getActivity());

        list_user = (ArrayList<User>) userDao.getAllA(0);
        Log.d("a",""+list_user.size());
        adapter_user = new Adapter_user(getActivity(),list_user,1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter_user);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity());
            }
        });

    }

    private void openDialog(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_nd);

        EditText ed_ten = dialog.findViewById(R.id.txt_ten_ND);
        EditText ed_sdt = dialog.findViewById(R.id.txt_SDT);
        EditText ed_maDN = dialog.findViewById(R.id.txt_MaDN);
        EditText ed_mk = dialog.findViewById(R.id.txt_pass);
        EditText ed_rmk = dialog.findViewById(R.id.txt_cf_pass);

        Button btn_them = dialog.findViewById(R.id.btnSaveThem);
        Button btn_huy = dialog.findViewById(R.id.btnCancelThem);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed_ten.getText().toString().isEmpty()
                && !ed_sdt.getText().toString().isEmpty()
                && !ed_maDN.getText().toString().isEmpty()
                && !ed_mk.getText().toString().isEmpty()
                && !ed_rmk.getText().toString().isEmpty()) {

                    String ten = ed_ten.getText().toString().trim();
                    String sdt = ed_sdt.getText().toString().trim();
                    String ma = ed_maDN.getText().toString().trim();
                    String pass = ed_mk.getText().toString().trim();
                    String repass = ed_rmk.getText().toString().trim();

                    if (repass.equals(pass)){
                        if (userDao.checkMaDN(ma)>0){
                            User user = new User();
                            user.setHoTen(ten);
                            user.setsDt(sdt);
                            user.setMaDN(ma);
                            user.setMatKhau(pass);
                            user.setVaiTro(0);
                            if (userDao.insert(user)>0){
                                list_user = (ArrayList<User>) userDao.getAllA(0);
                                adapter_user = new Adapter_user(getActivity(),list_user,1);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                recyclerView.setAdapter(adapter_user);
                                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getActivity(), "Lỗi thêm mới", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            Toast.makeText(getActivity(), "Mã đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(context, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin và kiểm tra lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}