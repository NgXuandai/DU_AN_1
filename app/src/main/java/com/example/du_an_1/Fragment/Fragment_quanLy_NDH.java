package com.example.du_an_1.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.du_an_1.DAO.UserDao;
import com.example.du_an_1.R;
import com.example.du_an_1.adapters.Adapter_user;
import com.example.du_an_1.model.User;

import java.util.ArrayList;


public class Fragment_quanLy_NDH extends Fragment {

    private RecyclerView recyclerView;
    private UserDao userDao ;
    private Adapter_user adapter_user ;
    private ArrayList<User> list_user = new ArrayList<>() ;

    public Fragment_quanLy_NDH() {
        // Required empty public constructor
    }


    public static Fragment_quanLy_NDH newInstance() {
        Fragment_quanLy_NDH fragment = new Fragment_quanLy_NDH();

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
        return inflater.inflate(R.layout.fragment_quan_ly__n_d_h, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.id_recycler_users_2);

        userDao = new UserDao(getActivity());
        list_user = (ArrayList<User>) userDao.getAllA(3);
        adapter_user = new Adapter_user(getActivity(),list_user,2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter_user);
    }


}