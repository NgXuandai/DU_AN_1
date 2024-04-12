package com.example.du_an_1.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.du_an_1.DAO.Type_Of_Food_DAO;
import com.example.du_an_1.NavigationQuanLy;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.Type_Of_Food_QL_adapter;
import com.example.du_an_1.model.Food;
import com.example.du_an_1.model.Type_Of_Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLySanPham_KD_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyLoaiSanPhamKD extends Fragment implements Type_Of_Food_QL_adapter.OnclickItem{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuanLyLoaiSanPhamKD() {
        // Required empty public constructor
    }

    FloatingActionButton fab;
    NavigationQuanLy navigationQuanLy;
    List<Type_Of_Food> list;
    Dialog dialog;

    Type_Of_Food type_of_food;
    RecyclerView recyclerView;

    Uri selectedImage;

    Type_Of_Food_DAO type_of_food_dao;
    Type_Of_Food_QL_adapter type_of_food_ql_adapter;

    RecyclerView.LayoutManager layoutManager;
    Calendar lich = Calendar.getInstance();
    int maLoai;
    int a;
    EditText ed_idSp,ed_tenSp;
    Button btn_themsp,btn_themAnh;
    public static QuanLyLoaiSanPhamKD newInstance(String param1, String param2) {
        QuanLyLoaiSanPhamKD fragment = new QuanLyLoaiSanPhamKD();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void taoDoiTuong() {
        dialog = new Dialog(getContext());
        type_of_food_dao = new Type_Of_Food_DAO(dialog.getContext());
        list = new ArrayList<>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taoDoiTuong();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_loai_san_pham, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        FloatingActionButton floatThem = view.findViewById(R.id.fab);
        recyclerView = view.findViewById(R.id.rcv_LSP);
        navigationQuanLy = (NavigationQuanLy) getContext();
        getData();
        floatThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=-1;
                showDialogAdd();
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }

    private void getData() {
        list = type_of_food_dao.getAllTY(0);
        type_of_food_ql_adapter = new Type_Of_Food_QL_adapter(getActivity(), list,0, QuanLyLoaiSanPhamKD.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(type_of_food_ql_adapter);
    }


    int id_lsp;
    String tenlsp;
    byte[] anh;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == navigationQuanLy.RESULT_OK && data != null) {
            selectedImage = data.getData();
            Toast.makeText(navigationQuanLy, "Chọn ảnh thành công", Toast.LENGTH_SHORT).show();
        }
    }
    private void showDialogAdd() {
        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_them_loai_sp);
        //ánh xạ
         ed_idSp = dialog.findViewById(R.id.txtIdSanPhamThem);
         ed_tenSp = dialog.findViewById(R.id.txtTenSanPhamThem);
         btn_themsp = dialog.findViewById(R.id.btnSaveThem);
         btn_themAnh = dialog.findViewById(R.id.btnlayanh);
          if(!dialog.isShowing()){
              ed_idSp.setText("");
              ed_tenSp.setText("");
              selectedImage = Uri.parse("");
          }
//        int ngay = lich.get(Calendar.DAY_OF_MONTH);
//        int thang = lich.get(Calendar.MONTH);
//        int nam = lich.get(Calendar.YEAR);
        btn_themAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    openAnh();
            }
        });

//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        if(a==-1){
            btn_themsp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!ed_idSp.getText().toString().isEmpty()&&!ed_tenSp.getText().toString().isEmpty() && !selectedImage.toString().isEmpty()) {
                        id_lsp = Integer.parseInt(ed_idSp.getText().toString());
                        tenlsp = ed_tenSp.getText().toString();
                        openDialog_tb(dialog);
                    } else {
                        Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            Type_Of_Food tof = list.get(a);
            Log.d("Huy", "showDialogAdd: "+a);
            ed_idSp.setText(""+tof.getMaLoai());
            ed_tenSp.setText(""+tof.getTenLoai());
            selectedImage = Uri.parse(tof.getHinhAnh());
            btn_themsp.setOnClickListener(v -> {
                if (!ed_idSp.getText().toString().isEmpty()&&!ed_tenSp.getText().toString().isEmpty() && !selectedImage.toString().isEmpty()) {
                    Type_Of_Food type = new Type_Of_Food();
                    id_lsp = Integer.parseInt(ed_idSp.getText().toString());
                    tenlsp = ed_tenSp.getText().toString();
                    type.setMaLoai(id_lsp);
                    type.setTenLoai(tenlsp);
                    type.setHinhAnh(String.valueOf(selectedImage));
                    if(type_of_food_dao.Update(type)>0){
                        Toast.makeText(getActivity(), "Update thành công", Toast.LENGTH_SHORT).show();
                        getData();
                        dialog.dismiss();
                    }else {
                        Toast.makeText(getActivity(), "update thất bại", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            });
        }

        dialog.findViewById(R.id.btnCancelThem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    private void openAnh(){
        Intent intent = new Intent(Intent.ACTION_PICK,  MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    public void openDialog_tb(Dialog dialog1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Save");
            builder.setMessage("Bạn có chắc chắn muốn Save không?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (checkten() == 0) {
                        if (id_lsp >= 0) {
                            if (themSP() > 0) {
                                dialog.dismiss();
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                dialog1.dismiss();
                            } else {
                                Toast.makeText(getContext(), "Mã sản phẩm đã tồn tại vui lòng kiểm tra lại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getContext(), "Không để trống mã sản phẩm", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getContext(), "Tên sản phẩm đã tồn tại", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

    }
    public void loadData() {
        list.clear();
        list.addAll(type_of_food_dao.getAllTY(0));
        type_of_food_ql_adapter.notifyDataSetChanged();
    }
    public long themSP() {
        long a = 0;
        type_of_food = new Type_Of_Food();
        type_of_food.setMaLoai(id_lsp);
        type_of_food.setTenLoai(tenlsp);
        type_of_food.setHinhAnh(String.valueOf(selectedImage));
        int maND = 1;
        checkten();
        a = type_of_food_dao.ADDSanPham(type_of_food);
        list.clear();
        list.addAll(type_of_food_dao.getAllTY(0));
        type_of_food_ql_adapter.notifyDataSetChanged();
        return a;
    }
    private int checkten() {
        int a = 0;
        for (Type_Of_Food sp : list) {
            if (tenlsp.equals(sp.getTenLoai())) {
                return 2;
            }
        }
        return a;
    }
    public List<Food> loc(List<Food> list) {
        List<Food> listCheck = new ArrayList<>();
        for (Food sp : list) {
            if (sp.getTrangthai() == 0) {
                listCheck.add(sp);
            }
        }
        return listCheck;
    }

    @Override
    public void updateCategory(int position) {
        a=position;
        Log.d("Huy", "updateCategory: "+a);
        showDialogAdd();
    }
}