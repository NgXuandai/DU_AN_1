package com.example.du_an_1.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.du_an_1.DAO.Food_DAO;
import com.example.du_an_1.R;
import com.example.du_an_1.adapter.FoodAdapter_ql;
import com.example.du_an_1.model.Food;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLySanPham_NKD_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLySanPham_NKD_Fragment extends Fragment implements FoodAdapter_ql.OnclickItem{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FoodAdapter_ql adapter_sp;
    RecyclerView rc_list;
    List<Food> list;
    Food_DAO dao_sp;

    public QuanLySanPham_NKD_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuanLySanPham_NKD_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuanLySanPham_NKD_Fragment newInstance(String param1, String param2) {
        QuanLySanPham_NKD_Fragment fragment = new QuanLySanPham_NKD_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_san_pham__n_k_d_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rc_list = view.findViewById(R.id.rcv_sanpham_ngung);
        dao_sp = new Food_DAO(getContext());
        list = dao_sp.getAll(1);
        adapter_sp = new FoodAdapter_ql(view.getContext(), list,1, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_list.setAdapter(adapter_sp);
        rc_list.setLayoutManager(linearLayoutManager);
        super.onViewCreated(view, savedInstanceState);
    }

    public void loadData() {
        adapter_sp.notifyDataSetChanged();
    }

    @Override
    public void UpdateSP(int position) {

    }
}